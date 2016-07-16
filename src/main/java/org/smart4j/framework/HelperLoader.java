package org.smart4j.framework;

import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.ClassUtil;

/**
 * 用于加载ClassHelper,BeanHelper,IocHelper,ControllerHelper，以初始化框架
 * Created by DongChao on 2016/7/7.
 */
public final class HelperLoader {
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class, //AopHelp要放在IocHelper之前，要先代理，再能通过Ioc进行注入
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls :classList){
            ClassUtil.loadClass(cls.getName(),true);
        }
    }
}
