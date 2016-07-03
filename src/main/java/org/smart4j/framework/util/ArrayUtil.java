package org.smart4j.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类
 * Created by DongChao on 2016/7/2.
 */
public final class ArrayUtil {
    public static boolean isNotEmpty(Object[] array){
        return !ArrayUtils.isEmpty(array);
    }
    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }
}
