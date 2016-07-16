package org.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by DongChao on 2016/7/13.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
