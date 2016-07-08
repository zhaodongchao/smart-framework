package org.smart4j.framework.bean;

import java.util.Map;

/**
 * 请求参数对象
 * Created by DongChao on 2016/7/7.
 */
public class Param {
    private Map<String,Object> paramMap ;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
