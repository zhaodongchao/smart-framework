package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 换回的视图对象
 * Created by DongChao on 2016/7/7.
 */
public class View {
    /**
     * 视图路径
     */
    private String path ;
    /**
     * 数据模型
     */
    private Map<String,Object> model ;

    public View(String path) {
        this.path = path;
        model = new HashMap<String, Object>() ;
    }
    public View addModel(String key,Object value){
        model.put(key,value);
        return this ;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
