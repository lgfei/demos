package com.lgfei.deom.springboot.common.util;

/**
 * 字符串处理工具类
 * <功能详细描述>
 * 
 * @author  lgfei
 * @version  [版本号, 2017年6月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class StringUtil
{
    /**
     * 检查是否是整数类型
     * <功能详细描述>
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isInteger(String str)
    {
        try
        {
            Integer.parseInt(str);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    
    /**
     * 检查是否长整数类型
     * <功能详细描述>
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isLong(String str)
    {
        try
        {
            Long.parseLong(str);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }
}
