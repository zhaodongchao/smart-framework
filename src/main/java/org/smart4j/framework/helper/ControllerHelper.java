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
                            //验证URL映射规则
                            if (mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array)&&array.length==2){
                                    //获取请求方法和请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1] ;
                                    Request request =  new Request(requestMethod,requestPath);
                                    Handler handler = new Handler(controllerClass,method);
                                    //初始化Action Map
                                    ACTION_MAP.put(request,handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据请求方法名和请求路径生成一个Request对象
     * 通过这个Request做为key从ACTION_MAP中获取相应的handler
     * @param requestMethod 请求方法
     * @param requestPath 请求路径
     * @return 还回一个handler
     */
   public static Handler getHander(String requestMethod,String requestPath){
       Request request = new Request(requestMethod,requestPath);
       return ACTION_MAP.get(request);
   }
}
