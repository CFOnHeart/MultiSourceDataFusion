package com.iip.textprocess.word2vec;

/**
 * @Author Junnor.G
 * @Date 2018/8/19 下午9:32
 */
//import org.python.core.PyFunction;
//import org.python.core.PyObject;
//import org.python.core.PyString;
//import org.python.util.PythonInterpreter;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;


/**
 * @Author YLX
 * @Date 2018/2/1
 */
public class Doc2vec {

    public static void print(Object str){System.out.println(str);}

    public static float[] doc2vec(String str, String vecsPath) {
        // input: 字符串
        // output: 经过HanLP分词后, 进而计算得到的向量表示
        List<Term> a = HanLP.segment(str);
        List<String> words = new ArrayList<String>();
        for(int i=0; i<a.size(); i++){
            words.add(i, a.get(i).toString().split("/")[0]);
//            print(words.get(i));
        }
        Random random = new Random();
        Map<String, float []> vecs = getvecs(words, vecsPath);
        float results[] = new float[50];
        for(int i=0; i<words.size(); i++){
            String token = words.get(i);
            if(!vecs.containsKey(token)) continue;
            float tvec[] = vecs.get(token);
//            print(token+tvec[0]);
            for(int j=0; j<50; j++){
                results[j] = (results[j]*(i+1)+tvec[j])/(i+2);
            }
        }
        for(int j=0; j<50; j++){
            results[j] = (results[j]*50+random.nextFloat())/51;
        }
        return results;
    }

    private static Map<String, float []> getvecs(List<String> a, String vecsPath) {
        try{
            String encoding = "utf-8";
//            String vecsPath = "src/main/java/textprocess/word2vec/mini_vec.properties";
            File file = new File(vecsPath);
            InputStreamReader inputStreamReader = new InputStreamReader(
                    new FileInputStream(file), encoding);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            String [] ns = line.split(" ");
            int vec_size = new Integer(ns[1]);
            Map<String, float []> vecs = new HashMap<String, float []>();
            float [] vec = null;
            print(ns[0]+ "#" +vec_size + "#loading vocabulary ...");
            while((line = bufferedReader.readLine()) != null){
                String tokens[] = line.split(" ");
                if(!a.contains(tokens[0])) continue;
                vec = new float[vec_size];
                for(int i=1; i<vec_size+1; i++){
                    vec[i-1] = Float.parseFloat(tokens[i]);
                }
                vecs.put(tokens[0], vec);
            }
            print("vocabulary loaded." + vecs.size());
            return vecs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    public static void main(String [] a) {
        String documents = "这里面是原味牛乳制品";
        String vecsPath = "src/main/resources/zhwiki_2017_03.sg_50d.word2vec";
        float[] vec = doc2vec(documents, vecsPath);
        print("$"+vec.length+"#"+vec[49]);
    }



//    public static String getVec(String str){
//        System.out.println("parse");
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.execfile("src/main/java/com/textprocess/word2vec/test.py");
//        PyFunction pyFunction = interpreter.get("parse", PyFunction.class);
//        PyObject pyObject = pyFunction.__call__(new PyString(str));
//        System.out.println("output: " + pyObject);
//        System.out.println("output: " + ((Map<String, String>)pyObject).get(str));
    // return (Map<String, String>)pyObject;
//        return ((Map<String, String>)pyObject).get(str);
//        Random random = new Random();
//        return "0.134,0.234124";
//    }

}
