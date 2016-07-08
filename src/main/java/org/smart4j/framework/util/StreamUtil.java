package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 流操作工具类
 * Created by DongChao on 2016/7/7.
 */
public final class StreamUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);
    public static String getString(InputStream inputStream){
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line ;
            while ((line = bufferedReader.readLine())==null){
                sb.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("get String failure",e);
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
