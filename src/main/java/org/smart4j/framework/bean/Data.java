package org.smart4j.framework.bean;

/**
 * 还回的数据对象
 * Created by DongChao on 2016/7/7.
 */
public class Data {
    /**
     * 模型数据
     */
    private Object model ;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
