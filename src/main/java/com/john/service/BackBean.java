package com.john.service;

import com.john.utils.dynCompilExe.ClassUtil;
import com.john.utils.freemarker.FreeMarkerDemo;
import com.john.utils.readConfig.GetProperties;

/**
 * Created by vincent on 28/05/2018.
 */
public class BackBean {

    public Object getBean(String str) {
        //判断传来的字符串是否之前传过
        //filePropPath在还没编译的工程文件夹里
        String filePropPath = "/Users/vincent/program/java/idea/tool/src/main/resources/config/str-bean.properties";
        //fileClassPath在已经编译的工程文件夹里,既target文件里下
        String fileClassPath = BackBean.class.getClassLoader().getResource("config/str-bean.properties").getPath();

        Boolean isStrAssign = GetProperties.getStrAssign(str, filePropPath);

        if (!isStrAssign) {//之前没传过过该字符,将该字符写到str-bean.propertie文件里,并新建一个对象,和该字符对应
            //将该字符写到str-bean.propertie文件里
            GetProperties.addStr(str, filePropPath);
            GetProperties.addStr(str, fileClassPath);
            //生成新的bean
            FreeMarkerDemo.freemark(str);
            //动态编译新生成的bean对象
            //java源码地址
            String fileInPath = "/Users/vincent/program/java/idea/tool/src/main/java/" +
                    "com/john/utils/freemarker/beanauto/HelloWorldBean.java";
            //编译后的class文件地址
            String fileOutPath = "/Users/vincent/program/java/idea/tool/target/classes/";
            //动态编译bean对象
            ClassUtil.compilDyn(fileInPath, fileOutPath);

        }

        //返回该字符对应的bean对象
        String className = str.substring(0, 1).toUpperCase() + str.substring(1) + "Bean";
        Class<?> clazz = null;
        String packageValue = "com.john.utils.freemarker.beanauto.";
        try {
            clazz = Class.forName(packageValue + className);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Object beanAuto = null;

        try {
            beanAuto = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return beanAuto;
    }
}
