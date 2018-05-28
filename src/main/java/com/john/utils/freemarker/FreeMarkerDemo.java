package com.john.utils.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 最常见的问题：
 * java.io.FileNotFoundException: xxx does not exist. 解决方法：要有耐心
 * FreeMarker jar 最新的版本（2.3.23）提示 Configuration 方法被弃用
 * 代码自动生产基本原理：
 * 数据填充 freeMarker 占位符
 */
public class FreeMarkerDemo {

    private static final String TEMPLATE_PATH = "src/main/java/com/john/utils/freemarker/templates";
    private static final String CLASS_PATH = "src/main/java/com/john/utils/freemarker/beanauto";

    public static void freemark(String strFront) {

        //前端传来的String字符串
        String str = strFront;

        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            String classPath = "com.john.utils.freemarker.beanauto";
            dataMap.put("classPath", classPath);
            String className = str.substring(0,1).toUpperCase() + str.substring(1) + "Bean";
            dataMap.put("className", className);

            //赋予属性值
            dataMap.put("attributeType_a", "String");
            dataMap.put("attribute_a", "attribute_a");
            String upperCase_a = "attribute_a".substring(0, 1).toUpperCase() + "attribute_a".substring(1);
            dataMap.put("upperCase_a", upperCase_a);

            dataMap.put("attributeType_b", "Integer");
            dataMap.put("attribute_b", "attribute_b");
            String upperCase_b = "attribute_b".substring(0, 1).toUpperCase() + "attribute_b".substring(1);
            dataMap.put("upperCase_b", upperCase_b);

            dataMap.put("attributeType_c", "Object");
            dataMap.put("attribute_c", "attribute_c");
            String upperCase_c = "attribute_c".substring(0, 1).toUpperCase() + "attribute_c".substring(1);
            dataMap.put("upperCase_c", upperCase_c);

            //和前端传来的String字符串产生联系
            dataMap.put("attributeType_x", "String");
            dataMap.put("attribute_x", str);
            String upperCase_x = str.substring(0, 1).toUpperCase() + str.substring(1);
            dataMap.put("upperCase_x", upperCase_x);

            // step4 加载模版文件
            Template template = configuration.getTemplate("bean.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "/" + className + ".java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);

            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^" + className + ".java 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
