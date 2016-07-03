package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * Created by DongChao on 2016/7/1.
 */
public final class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> cls){
        Object instance ;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failure ",e);
            throw new RuntimeException(e);
        }
        return instance ;
    }
    public static Object InvokeMethod(Object object, Method method,Object... args){
        Object result = null;
        /**
         * A value of true indicates that the reflected object should suppress Java language access checking
         * when it is used
         */
        method.setAccessible(true);
        try {
            result = method.invoke(object,args);
        } catch (Exception e) {
            LOGGER.error("invoke method failure",e);
            throw new RuntimeException(e);
        }
        return result ;
    }

    /**
     *
     * @param object bean对象
     * @param field  bean的成员变量
     * @param value  要给field注入的field实例化对象
     */
    public static void setField(Object object, Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(object,value);
        } catch (IllegalAccessException e) {
            LOGGER.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }
}
