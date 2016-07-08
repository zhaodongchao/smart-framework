package org.smart4j.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * json工具类，用于处理JSON与POJO之间的转换
 * Created by DongChao on 2016/7/7.
 */
public final class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将POJO转换为JSON
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJSON(T obj){
        String json = null;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("covert POJO to json failure",e);
            throw new RuntimeException(e);
        }
        return json ;
    }

    /**
     * 将JSON转换为POJO
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJSON(String json,Class<T> type){
        T pojo = null;
        try {
            pojo = OBJECT_MAPPER.readValue(json,type);
        } catch (IOException e) {
            LOGGER.error("covert json to POJO failure",e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
