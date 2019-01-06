package com.iip.textprocess.word2vec;

/**
 * @Author Junnor.G
 * @Date 2018/8/19 下午9:32
 */
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * @Author YLX
 * @Date 2018/2/1
 */
public class jython {

    public static void main(String [] a) {
        String documents = "程序员(英文Programmer)是从事程序开发、\n" +
                "维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，\n" +
                "但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程\n" +
                "序员、系统分析员和项目经理四大类。";
        String keys = getVec(documents);
        System.out.println(keys);

    }
    public static String getVec(String str){
        System.out.println("parse");
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.execfile("src/main/java/com/textprocess/word2vec/test.py");
//        PyFunction pyFunction = interpreter.get("parse", PyFunction.class);
//        PyObject pyObject = pyFunction.__call__(new PyString(str));
//        System.out.println("output: " + pyObject);
//        System.out.println("output: " + ((Map<String, String>)pyObject).get(str));
        // return (Map<String, String>)pyObject;
//        return ((Map<String, String>)pyObject).get(str);
        Random random = new Random();
        return "0.134,0.234124";
    }

}
