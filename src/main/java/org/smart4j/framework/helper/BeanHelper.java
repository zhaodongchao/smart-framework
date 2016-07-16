package org.smart4j.framework.helper;

import org.smart4j.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * smart框架bean的管理类
 * Created by DongChao on 2016/7/1.
 */
public final class BeanHelper {
    /**
     * 定义bean的映射，用于存放Bean类与Bean的实例的映射关系
     */
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>, Object>();
    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet){
            Object object = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass,object);
        }
    }

    /**
     * 获取Bean映射
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     *
     * @param cls class类型
     * @param <T> 泛型
     * @return 还回cls的实例
     */
    public static <T> T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can't get bean by class :"+cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    /**
     * 设置bean的实例
     * @param cls class
     * @param object 实例
     */
    public static void setBean(Class<?> cls , Object object){
        BEAN_MAP.put(cls,object);
    }
}
