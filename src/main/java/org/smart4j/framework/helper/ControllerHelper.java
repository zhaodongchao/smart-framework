package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.ArrayUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by DongChao on 2016/7/2.
 */
public final class ControllerHelper {
    private static final Map<Request,Handler> ACTION_MAP = new HashMap<Request, Handler>();
    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (!controllerClassSet.isEmpty()){
            for (Class<?> controllerClass : controllerClassSet){
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)){
                    for (Method method:methods){
                        /**
                         * 找出带有Action注解的方法
                         */
                        if (method.isAnnotationPresent(Action.class)){
                             Action action = method.getAnnotation(Action.class);
                             String mapping = action.value();
                        }
                    }
                }
            }
        }
    }

}
