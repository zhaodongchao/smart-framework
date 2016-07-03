package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.protocol.jar.JarURLConnection;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by DongChao on 2016/6/30.
 */
public final class ClassUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     * @return 当前线程中的类加载器
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载class
     * @param className 类名
     * @param isInitialized 是否执行类的静态代码块
     * @return
     */
    public static Class<?> loadClass(String className,boolean isInitialized){
        Class<?> cls ;
        try {
            cls = Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("class load failure ",e);
            throw new RuntimeException(e);
        }
        return  cls ;
    }
    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));
            while (urls.hasMoreElements()){//测试此枚举是否包含更多的元素。
                URL url = urls.nextElement();// 如果此枚举对象至少还有一个可提供的元素，则返回此枚举的下一个元素。
                if (url != null){
                    String protocol = url.getProtocol();//  获得此 URL 的协议名称。
                    if (protocol.equals("file")){
                        String packagePath = url.getPath().replace("%20","");
                        addClass(classSet, packagePath, packageName);
                    }else if (protocol.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classSet ;
    }
    public static void addClass(Set<Class<?>> classSet,String packagePath,String packageName){
        /**
         * 获取给定packagePath下的所有文件
         */
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return (pathname.isFile() && pathname.getName().endsWith(".class"))||pathname.isDirectory();
            }
        });

        for (File file : files){
            String fileName = file.getName();
            if (file.isFile()){
                String className = fileName.substring(0,fileName.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)){
                    className = packageName+"."+className;
                }
            }else {
                String subPackagePath = fileName ;
                if(StringUtil.isNotEmpty(packagePath)){
                    subPackagePath = packagePath+"/"+subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtil.isNotEmpty(packageName)){
                    subPackageName = packageName+"."+subPackageName;
                }
                addClass(classSet,subPackagePath,subPackageName);
            }
        }
    }
    public static void doAddClass(Set<Class<?>> classSet,String className){
        Class<?> cls = loadClass(className,false);
        classSet.add(cls);
    }
}
