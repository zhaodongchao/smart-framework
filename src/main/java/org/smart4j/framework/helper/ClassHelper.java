package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by DongChao on 2016/7/1.
 */
public final class ClassHelper {
    /**
     * 定义用于存放所加载类的Set集合
     */
    private static final Set<Class<?>> CLASS_SET;
    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用包名下所有的类
     *
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     *获取应用包下所有的@Service注解的类
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls :CLASS_SET){
            if(cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }
        return classSet ;
    }

    /**
     *获取应用包下所有的@Controller注解的类
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls :CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }
        return classSet ;
    }

    /**
     *获取应用包下所有的Bean类（包括：Service,Controller 等）
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet ;
    }

    /**
     * 获取应用包名下某父类（或接口）的所有子类（或实现类）
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls :CLASS_SET){
            if (superClass.isAssignableFrom(cls)&&!superClass.equals(cls)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下带某个注解的所有类
     */
    public static Set<Class<?>> getClassByAnnotation(Class<? extends Annotation> annotationClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET){
            if (cls.isAnnotationPresent(annotationClass)){
                classSet.add(cls);
            }
        }
        return classSet ;
    }
}
