import com.john.service.BackBean;
import com.john.utils.dynCompilExe.ClassUtilTest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileNotFoundException;

/**
 * Created by vincent on 27/05/2018.
 */
public class CoreApplication {

    private static final Log logger = LogFactory.getLog(ClassUtilTest.class);

    public static void main(String[] args) throws FileNotFoundException {

        //假设前端传来的字符串为helloWorld
        String str = "HelloWorld";
        //FreeMarkerDemo.freemark(str);

        //获得bean对象
        BackBean backBean = new BackBean();
        Object bean = backBean.getBean(str);

        //打印bean对象
        System.out.println(bean);

//        String fileInPath = "/Users/vincent/program/java/idea/tool/src/main/java/" +
//                "com/john/utils/freemarker/beanauto/HelloWorldBean.java";
//        String fileOutPath = "/Users/vincent/program/java/idea/tool/target/classes/";
//
//        ClassUtil.compilDyn(fileInPath, fileOutPath);


    }

}
