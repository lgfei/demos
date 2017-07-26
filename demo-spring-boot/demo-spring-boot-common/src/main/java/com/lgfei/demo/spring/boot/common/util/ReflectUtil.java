package com.lgfei.demo.spring.boot.common.util;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射操作工具类
 * <功能详细描述>
 * 
 * @author  lgfei
 * @version  [版本号, 2017年6月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class ReflectUtil
{
    private static final Logger LOG = LoggerFactory.getLogger(ReflectUtil.class);
    
    /**
     * 利用反射获取指定对象的指定属性
     * <功能详细描述>
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @return 目标属性的值
     * @see [类、类#方法、类#成员]
     */
    public static Object getFieldValue(Object obj, String fieldName)
    {
        Object result = null;
        Field field = getField(obj, fieldName);
        if (field != null)
        {
            field.setAccessible(true);
            try
            {
                result = field.get(obj);
            }
            catch (IllegalArgumentException e)
            {
                LOG.error("根据属性名反射发生异常：{}", e.getMessage());
            }
            catch (IllegalAccessException e)
            {
                LOG.error("根据属性名反射发生异常：{}", e.getMessage());
            }
        }
        return result;
    }
    
    /**
     * 利用反射获取指定对象里面的指定属性
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @return 目标字段
     */
    private static Field getField(Object obj, String fieldName)
    {
        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass())
        {
            try
            {
                field = clazz.getDeclaredField(fieldName);
                break;
            }
            catch (NoSuchFieldException e)
            {
                //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
                LOG.error("根据属性名反射发生异常：{}", e.getMessage());
            }
        }
        return field;
    }
    
    /**
     * 利用反射设置指定对象的指定属性为指定的值
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @param fieldValue 目标值
     */
    public static void setFieldValue(Object obj, String fieldName, String fieldValue)
    {
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null)
        {
            try
            {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            }
            catch (IllegalArgumentException e)
            {
                LOG.error("根据属性名反射发生异常：{}", e.getMessage());
            }
            catch (IllegalAccessException e)
            {
                LOG.error("根据属性名反射发生异常：{}", e.getMessage());
            }
        }
    }
}
