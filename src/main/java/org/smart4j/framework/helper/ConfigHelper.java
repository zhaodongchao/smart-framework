package org.smart4j.framework.helper;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.util.PropertyUtil;

import java.util.Properties;

/**
 * 属性文件助手类
 * Created by DongChao on 2016/6/30.
 */
public final class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropertyUtil.loadProps(ConfigConstant.CONFIG_FILE);

    public static String getJdbcDriver(){
        return PropertyUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
    }
    public static String getJdbcUrl(){
        return PropertyUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_URL);
    }
    public static String getJdbcUsername(){
        return PropertyUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_USERNAME);
    }
    public static String getJdbcPassword(){
        return PropertyUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_PASSWORD);
    }
    public static String getAppBasePackage(){
        return PropertyUtil.getString(CONFIG_PROPS,ConfigConstant.APP_BASE_PACKAGE);
    }
    public static String getAppJspPath(){
        return PropertyUtil.getString(CONFIG_PROPS,ConfigConstant.APP_JSP_PATH,"/WEB-INF/view/");
    }
    public static String getAppAssetPath(){
        return PropertyUtil.getString(CONFIG_PROPS,ConfigConstant.APP_ASSET_PATH,"/asset/");
    }
}
