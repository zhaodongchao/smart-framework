package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 * Created by DongChao on 2016/7/1.
 */
public final class IocHelper {
    static {
        Map<Class<?>, Object> beanMap =  BeanHelper.getBeanMap();
        if (!beanMap.isEmpty()){
            for (Map.Entry<Class<?>, Object> beanEntry :beanMap.entrySet()){
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                /**
                 * 获取bean中的所有成员变量
                 */
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)){
                    for (Field beanField:beanFields){
                        //判断当前成员变量是否带有@Inject注解
                        if (beanField.isAnnotationPresent(Inject.class)){
                            //如果有Inject注解，就从beanMap中获取对应的实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance!=null){
                                /**
                                 * 通过反射初始化BeanField的值
                                 */
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
