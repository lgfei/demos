package com.lgfei.deom.springboot.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lgfei.deom.springboot.common.util.ReflectUtil;
import com.lgfei.deom.springboot.model.PageVO;

/**
 * mybatis分页查询拦截器
 * <p>
 * 分页拦截器，用于拦截需要进行分页查询的操作，然后对其进行分页处理。 <br/>
 * 利用拦截器实现Mybatis分页的原理：  <br/>
 *  要利用JDBC对数据库进行操作就必须要有一个对应的Statement对象，
 *  Mybatis在执行Sql语句前就会产生一个包含Sql语句的Statement对象，而且对应的Sql语句是在Statement之前产生的，
 *  所以我们就可以在它生成Statement之前对用来生成Statement的Sql语句下手。 
 *  <br/>
 *  在Mybatis中Statement语句是通过RoutingStatementHandler对象的prepare方法生成的。
 *  所以利用拦截器实现Mybatis分页的一个思路就是拦截StatementHandler接口的prepare方法，
 *  然后在拦截器方法中把Sql语句改成对应的分页查询Sql语句， 之后再调用StatementHandler对象的prepare方法，即调用invocation.proceed()。
 *  <br/>
 *  对于分页而言，在拦截器里面我们还需要做的一个操作就是统计满足当前条件的记录一共有多少， 
 *  这是通过获取到了原始的Sql语句后，把它改为对应的统计语句再利用Mybatis封装好的参数和设置参数的功能把Sql语句中的参数进行替换，之后再执行查询记录数的Sql语句进行总记录数的统计。 
 * </p>
 * 
 * @author  lgfei
 * @version  [版本号, 2017年6月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor
{
    private static final Logger LOG = LoggerFactory.getLogger(PageInterceptor.class);
    
    /**
     * 数据库类型，不同的数据库有不同的分页方法
     * (目前支持Oracle和Mysql)
     */
    private String dbType;
    
    /**
     * 拦截后要执行的方法
     */
    @Override
    public Object intercept(Invocation invocation)
        throws Throwable
    {
        LOG.info("进入mybatis拦截器");
        RoutingStatementHandler handler = (RoutingStatementHandler)invocation.getTarget();
        StatementHandler delegate = (StatementHandler)ReflectUtil.getFieldValue(handler, "delegate");
        BoundSql boundSql = delegate.getBoundSql();
        
        // 分页参数
        PageVO pageVO = null;
        Object paramObj = boundSql.getParameterObject();
        if (paramObj instanceof ParamMap)
        {
            ParamMap<?> paramMap = (ParamMap<?>)paramObj;
            pageVO = (PageVO)paramMap.get("page");
        }
        
        if (null != pageVO)
        {
            MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate, "mappedStatement");
            Connection connection = (Connection)invocation.getArgs()[0];
            
            // 组装查询总数的sql，获取总条数
            int total = this.getTotal(boundSql, paramObj, mappedStatement, connection);
            pageVO.setTotal(total);
            
            // 组装分页查询的sql
            String sql = boundSql.getSql();
            String pageSql = this.getPageSql(pageVO, sql);
            ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
        }
        
        // 执行原有查询或者拼接了分页的查询
        return invocation.proceed();
    }
    
    /**
     * 拦截器对应的封装原始对象的方法
     */
    @Override
    public Object plugin(Object target)
    {
        return Plugin.wrap(target, this);
    }
    
    /**
     * 设置注册拦截器时设定的属性
     */
    @Override
    public void setProperties(Properties properties)
    {
        this.dbType = properties.getProperty("dbType");
    }
    
    /**
     * 根据page对象获取对应的分页查询Sql语句，这里只做了两种数据库类型，Mysql和Oracle
     * 其它的数据库都 没有进行分页
     *
     * @param pageVO 分页对象
     * @param sql 原sql语句
     * @return 分页的sql语句
     */
    private String getPageSql(PageVO pageVO, String sql)
    {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        
        if ("mysql".equalsIgnoreCase(dbType))
        {
            return getMysqlPageSql(pageVO, sqlBuffer);
        }
        else if ("oracle".equalsIgnoreCase(dbType))
        {
            return getOraclePageSql(pageVO, sqlBuffer);
        }
        else
        {
            LOG.info("dbType:{}", dbType);
        }
        
        return sqlBuffer.toString();
    }
    
    /**
     * 获取Mysql数据库的分页查询语句
     * @param pageVO 分页对象
     * @param sqlBuffer 包含原sql语句的StringBuffer对象
     * @return Mysql数据库分页语句
     */
    private String getMysqlPageSql(PageVO pageVO, StringBuffer sqlBuffer)
    {
        int offset = (pageVO.getPageNum() - 1) * pageVO.getPageSize();
        sqlBuffer.append(" limit ").append(offset).append(",").append(pageVO.getPageSize());
        
        return sqlBuffer.toString();
    }
    
    /**
     * 获取Oracle数据库的分页查询语句
     * @param pageVO 分页对象
     * @param sqlBuffer 包含原sql语句的StringBuffer对象
     * @return Oracle数据库的分页查询语句
     */
    private String getOraclePageSql(PageVO pageVO, StringBuffer sqlBuffer)
    {
        //计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
        int offset = (pageVO.getPageNum() - 1) * pageVO.getPageSize() + 1;
        sqlBuffer.insert(0, "select u.*, rownum r from (")
            .append(") u where rownum < ")
            .append(offset + pageVO.getPageSize());
        sqlBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);
        
        return sqlBuffer.toString();
    }
    
    /**
     * 获取当前的参数对象查询的总记录数
     * <功能详细描述>
     * @param boundSql 原sql语句
     * @param paramObj 查询条件参数
     * @param mappedStatement Mapper映射语句
     * @param connection 当前的数据库连接
     * @return int 当前条件下的总数
     * @see [类、类#方法、类#成员]
     */
    private int getTotal(BoundSql boundSql, Object paramObj, MappedStatement mappedStatement, Connection connection)
    {
        // 原本sql语句
        String sql = boundSql.getSql();
        // 解析后得到查询总数的sql语句
        String countSql = this.getCountSql(sql);
        
        // 获取参数列表
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        BoundSql countBoundSql =
            new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, paramObj);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, paramObj, countBoundSql);
        PreparedStatement pstmt = null;
        // 结果集
        ResultSet rs = null;
        try
        {
            pstmt = connection.prepareStatement(countSql);
            // 赋值参数
            parameterHandler.setParameters(pstmt);
            // 执行查询
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                return rs.getInt(1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     * @param sql
     * @return
     */
    private String getCountSql(String sql)
    {
        int index = sql.toLowerCase().indexOf("from");
        return "select count(1) " + sql.substring(index);
    }
}
