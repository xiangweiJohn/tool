package com.john.utils.dynCompilExe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class ClassUtil {
    private static final Log logger = LogFactory.getLog(ClassUtil.class);
    private static JavaCompiler compiler;

    static {
        compiler = ToolProvider.getSystemJavaCompiler();
    }

    /**
     * 获取java文件路径
     *
     * @param file
     * @return
     */
    private static String getFilePath(String file) {
        int last1 = file.lastIndexOf('/');
        int last2 = file.lastIndexOf('\\');
        return file.substring(0, last1 > last2 ? last1 : last2) + File.separatorChar;
    }

    /**
     * 编译java文件
     *
     * @param ops   编译参数
     * @param files 编译文件
     */
    private static void javac(List<String> ops, String... files) {
        StandardJavaFileManager manager = null;
        try {
            manager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> it = manager.getJavaFileObjects(files);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, ops, null, it);
            task.call();
            if (logger.isDebugEnabled()) {
                for (String file : files)
                    logger.debug("Compile Java File:" + file);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 生成java文件
     *
     * @param file   文件名
     * @param source java代码
     * @throws Exception
     */
    private static void writeJavaFile(String file, String source) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Write Java Source Code to:" + file);
        }
        BufferedWriter bw = null;
        try {
            File dir = new File(getFilePath(file));
            if (!dir.exists())
                dir.mkdirs();
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(source);
            bw.flush();
        } catch (Exception e) {
            throw e;
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    /**
     * 加载类
     *
     * @param name 类名
     * @return
     */
    private static Class<?> load(String name) {
        Class<?> cls = null;
        ClassLoader classLoader = null;
        try {
            classLoader = ClassUtil.class.getClassLoader();
            cls = classLoader.loadClass(name);
            if (logger.isDebugEnabled()) {
                logger.debug("Load Class[" + name + "] by " + classLoader);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return cls;
    }

    /**
     * 编译代码并加载类
     *
     * @param filePath java代码路径
     * @param source   java代码
     * @param clsName  类名
     * @param ops      编译参数
     * @return
     */
    public static Class<?> loadClass(String filePath, String source, String clsName, List<String> ops) {
        try {
//            writeJavaFile(CLASS_PATH + filePath, source);
//            javac(ops, CLASS_PATH + filePath);
            writeJavaFile(filePath, source);
            javac(ops, filePath);
            return load(clsName);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * 调用类方法
     *
     * @param cls        类
     * @param methodName 方法名
     * @param paramsCls  方法参数类型
     * @param params     方法参数
     * @return
     */
    public static Object invoke(Class<?> cls, String methodName, Class<?>[] paramsCls, Object[] params) {
        Object result = null;
        try {
            Method method = cls.getDeclaredMethod(methodName, paramsCls);
            Object obj = cls.newInstance();
            result = method.invoke(obj, params);
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    /**
     * 动态生成java代码,该方法未测试
     */
    public static void classCreate(Map<String, Object> dataMap) {

        String classPath = (String) dataMap.get("classPath");
        String className = (String) dataMap.get("className");

        String attributeType_a = (String) dataMap.get("attributeType_a");
        String attribute_a = (String) dataMap.get("attribute_a");
        String upperCase_a = (String) dataMap.get("upperCase_a");

        String attributeType_b = (String) dataMap.get("attributeType_b");
        String attribute_b = (String) dataMap.get("upperCase_b");
        String upperCase_b = (String) dataMap.get("attribute_b");

        String attributeType_c = (String) dataMap.get("attributeType_c");
        String attribute_c = (String) dataMap.get("attribute_c");
        String upperCase_c = (String) dataMap.get("attribute_c");

        String attributeType_x = (String) dataMap.get("attributeType_x");
        String attribute_x = (String) dataMap.get("attribute_x");
        String upperCase_x = (String) dataMap.get("attribute_x");

        StringBuilder sb = new StringBuilder();

        sb.append("package " + classPath + ";\n");

        sb.append("public class" + className + "{\n");

        sb.append("private" + " " + attributeType_a + " " + attribute_a + "\n");
        sb.append("private" + " " + attributeType_b + " " + attribute_b + "\n");
        sb.append("private" + " " + attributeType_c + " " + attribute_c + "\n");
        sb.append("private" + " " + attributeType_x + " " + attribute_x + "\n");

        sb.append("public void set" + upperCase_a + " " + "(" + attributeType_a + " " + attribute_a + " )" + " " + "{\n");
        sb.append("this." + attribute_a + "=" + attribute_a + ";\n}");

        sb.append("public void set" + upperCase_b + " " + "(" + attributeType_b + " " + attribute_b + " )" + " " + "{\n");
        sb.append("this." + attribute_b + "=" + attribute_b + ";\n}");

        sb.append("public void set" + upperCase_c + " " + "(" + attributeType_c + " " + attribute_c + " )" + " " + "{\n");
        sb.append("this." + attribute_c + "=" + attribute_c + ";\n}");

        sb.append("public void set" + upperCase_x + " " + "(" + attributeType_x + " " + attribute_x + " )" + " " + "{\n");
        sb.append("this." + attribute_x + "=" + attribute_x + ";\n}");

        sb.append("public" + " " + attributeType_a + " " + "get" + upperCase_a + "}(){\n");
        sb.append("return" + " " + attribute_a + "};\n}\n");

        sb.append("public" + " " + attributeType_b + " " + "get" + upperCase_b + "}(){\n");
        sb.append("return" + " " + attribute_b + "};\n}\n");

        sb.append("public" + " " + attributeType_c + " " + "get" + upperCase_c + "}(){\n");
        sb.append("return" + " " + attribute_c + "};\n}\n");


        sb.append("public" + " " + attributeType_x + " " + "get" + upperCase_x + "}(){\n");
        sb.append("return" + " " + attribute_x + "};\n}\n");

        sb.append("@Override\n");
        sb.append("public String toString(){\n");
        sb.append("return" + " " + className + "[" + "\n");
        sb.append(attribute_a + "='" + attribute_a + "\\" + "\n");
        sb.append("," + attribute_b + "='" + attribute_b + "\\'" + "\n");
        sb.append("," + attribute_c + "='" + attribute_c + "\\'" + "\n");
        sb.append("," + attribute_x + "=" + attribute_x + "']';\n}");

    }

    /**
     * 动态编译代码方法
     */
    public static void compilDyn(String fileInPath,String fileOutPath) {
        JavaCompiler javac;
        javac = ToolProvider.getSystemJavaCompiler();
        int compilationResult = javac.run(null,null,null, "-g","-verbose","-d",fileOutPath,fileInPath);
        System.out.println(compilationResult);
    }
}