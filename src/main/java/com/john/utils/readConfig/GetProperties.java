package com.john.utils.readConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Created by vincent on 27/05/2018.
 */
public class GetProperties {

    /**
     * 读取配置文件所有信息
     *
     * @param filePath
     * @return
     * @throws
     * @Title: getProperties
     * @Description: 第三种方式：
     * 相对路径， properties文件需在classpath目录下，
     * 比如：com.john.config.properties在包com.test.config下，
     * 路径就是/com/test/com.john.config/com.john.config.properties
     */
    public static Properties getProperties(String filePath) {
        Properties prop = new Properties();
        try {
            FileInputStream in = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(in);
            //printAllProperty(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    /**
     * @param props
     * @return void
     * @throws
     * @Title: printAllProperty
     * @Description: 输出所有配置信息
     */
    private static void printAllProperty(Properties props) {
        @SuppressWarnings("rawtypes")
        Enumeration en = props.propertyNames();
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            String value = props.getProperty(key);
            System.out.println(key + " : " + value);
        }
    }

    /**
     * 得到指定string
     */
    public static Boolean getStrAssign(String str, String filePath) {

        List strList = new ArrayList();

        Properties prop = getProperties(filePath);

        return prop.containsKey(str);

    }

    public static void addStr(String str, String filePath) {
        Properties prop = getProperties(filePath);
        OutputStream fos = null;
        try {
            prop.load(new FileInputStream(filePath));
            fos = new FileOutputStream(filePath);
            String value = str.substring(0, 1).toUpperCase() + str.substring(1);
            prop.setProperty(str, value);
            prop.store(fos, "Update '" + value + "' value");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

