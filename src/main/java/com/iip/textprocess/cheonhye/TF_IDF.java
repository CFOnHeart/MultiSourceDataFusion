package com.iip.textprocess.cheonhye;

import com.iip.textprocess.util.FileUtil;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TF_IDF {

    private static List<String> FileList = new ArrayList<String>(); // the list of file
    public static int topicNumber = 5;


    //read file
    public static String readFile(String file) throws FileNotFoundException, IOException
    {
        StringBuffer strSb = new StringBuffer(); //String is constant， StringBuffer can be changed.
        InputStreamReader inStrR = new InputStreamReader(new FileInputStream(file), "utf-8"); //byte streams to character streams
        BufferedReader br = new BufferedReader(inStrR);
        String line = br.readLine();
        while(line != null){
            strSb.append(line).append("\r\n");
            line = br.readLine();
        }

        return strSb.toString();
    }
    public static String number_filter(String str){
        Pattern pattern = Pattern.compile("[\\d]");
        Matcher matcher = pattern.matcher(str);
        String newword=matcher.replaceAll("").trim();
        return newword;
    }

    //word segmentation
    public static ArrayList<String> cutWords(String file) throws IOException{

        ArrayList<String> words = new ArrayList<String>();
        String text = file;

        //开始分词
        List<Term> termList = HanLP.segment(text);

        for (int i = 0; i < termList.size() ; i++){

            words.add(termList.get(i).word);
        }

        //用来存放停用词的集合
        // ganjun update load stopwords function

        FileUtil.loadStopWords();

        Set<String> stopWordSet = FileUtil.stopWords;

        ArrayList<String> newwords=new ArrayList<String>();

        Iterator it = words.iterator();

        while(it.hasNext()){
            //把获取到的值强转成字符串类型
            String s = (String) it.next();
            //过滤数字
            String new_s=number_filter(s);
            //删除标点符号
            new_s=new_s.replaceAll("[\\pP\\pS\\pZ]", "");
            //过滤出中文
            new_s=new_s.replaceAll("[^(\\u4e00-\\u9fa5)]", "");
            if(stopWordSet.contains(new_s)||new_s.length()==0) {
                continue;
            }
            // 过滤停用词后
            newwords.add(new_s);
        }
        return newwords;
    }
    public static ArrayList<String> cutWords_new(String text) throws IOException{

        ArrayList<String> words = new ArrayList<String>();
        //String text = TF_IDF.readFile(file);

        //开始分词
        List<Term> termList = HanLP.segment(text);

        for (int i = 0; i < termList.size() ; i++){

            words.add(termList.get(i).word);
        }

        //用来存放停用词的集合
        // ganjun update load stopwords function

        FileUtil.loadStopWords();

        Set<String> stopWordSet = FileUtil.stopWords;

        ArrayList<String> newwords=new ArrayList<String>();

        Iterator it = words.iterator();

        while(it.hasNext()){
            //把获取到的值强转成字符串类型
            String s = (String) it.next();
            //过滤数字
            String new_s=number_filter(s);
            //删除标点符号
            new_s=new_s.replaceAll("[\\pP\\pS\\pZ]", "");
            //过滤出中文
            new_s=new_s.replaceAll("[^(\\u4e00-\\u9fa5)]", "");
            if(stopWordSet.contains(new_s)||new_s.length()==0) {
                continue;
            }
            // 过滤停用词后
            newwords.add(new_s);
        }
        return newwords;
    }

    //term frequency in a file, times for each word
    public static HashMap<String, Integer> normalTF(ArrayList<String> cutwords){
        HashMap<String, Integer> resTF = new HashMap<String, Integer>();

        for(String word : cutwords){
            if(resTF.get(word) == null){
                resTF.put(word, 1);
            }
            else{
                resTF.put(word, resTF.get(word) + 1);
            }
        }
        return resTF;
    }

    //term frequency in a file, frequency of each word
    public static HashMap<String, Float> tf(ArrayList<String> cutwords){
        HashMap<String, Float> resTF = new HashMap<String, Float>();

        int wordLen = cutwords.size();
        HashMap<String, Integer> intTF = TF_IDF.normalTF(cutwords);

        Iterator iter = intTF.entrySet().iterator(); //iterator for that get from TF
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            resTF.put(entry.getKey().toString(), Float.parseFloat(entry.getValue().toString()) / wordLen);
//            System.out.println(entry.getKey().toString() + " = "+  Float.parseFloat(entry.getValue().toString()) / wordLen);
        }
        return resTF;
    }

//    //tf times for file
//    public static HashMap<String, HashMap<String, Integer>> normalTFAllFiles(String dirc) throws IOException{
//        HashMap<String, HashMap<String, Integer>> allNormalTF = new HashMap<String, HashMap<String,Integer>>();
//
//        List<String> filelist = TF_IDF.readDirs(dirc);
//        for(String file : filelist){
//            HashMap<String, Integer> dict = new HashMap<String, Integer>();
//            ArrayList<String> cutwords = TF_IDF.cutWords(file); //get cut word for one file
//
//            dict = TF_IDF.normalTF(cutwords);
//            allNormalTF.put(file, dict);
//        }
//        return allNormalTF;
//    }

    //tf for all file
    public static HashMap<Integer,HashMap<String, Float>> tfAllFiles(List<String> input) throws IOException{
        HashMap<Integer, HashMap<String, Float>> allTF = new HashMap<Integer, HashMap<String, Float>>();
        // ganjun  update read dirs
        //FileList = FileUtil.getAllFilePath(dirc);
        int index =0;
        for(String file : input){
            HashMap<String, Float> dict = new HashMap<String, Float>();
            ArrayList<String> cutwords = TF_IDF.cutWords(file); //get cut words for one file
            //System.out.println("cutwords"+cutwords);
            dict = TF_IDF.tf(cutwords);
            allTF.put(index, dict);
            index++;
        }
        return allTF;
    }
    public static HashMap<String, Float> idf(HashMap<Integer,HashMap<String, Float>> all_tf){
        HashMap<String, Float> resIdf = new HashMap<String, Float>();
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        int docNum = FileList.size();

        for(int i = 0; i < docNum; i++){
            HashMap<String, Float> temp = all_tf.get(i);

            Iterator iter = temp.entrySet().iterator();

            while(iter.hasNext()){
                Map.Entry entry = (Map.Entry)iter.next();
                String word = entry.getKey().toString();
                if(dict.get(word) == null){
                    dict.put(word, 1);
                }else {
                    dict.put(word, dict.get(word) + 1);
                }
            }
        }
//        System.out.println("IDF for every word is:");
        Iterator iter_dict = dict.entrySet().iterator();
        while(iter_dict.hasNext()){
            Map.Entry entry = (Map.Entry)iter_dict.next();
//          System.out.println("包含该词的文档总数："+entry.getKey()+Float.parseFloat(entry.getValue().toString()));
            float value = (float)Math.log10(docNum / Float.parseFloat(entry.getValue().toString()));
            resIdf.put(entry.getKey().toString(), value);
//            System.out.println(entry.getKey().toString() + " = " + value);
        }
        return resIdf;
    }
    public static Map<Integer,List<String>> tf_idf(HashMap<Integer,HashMap<String, Float>> all_tf,HashMap<String, Float> idfs){
        Map<Integer,List<String>> output =new HashMap<Integer,List<String>>();


        HashMap<Integer, HashMap<String, Float>> resTfIdf = new HashMap<Integer, HashMap<String, Float>>();

        int docNum = FileList.size();

        for(int i = 0; i < docNum; i++){

            HashMap<String, Float> tfidf = new HashMap<String, Float>();
            HashMap<String, Float> temp = all_tf.get(i);
            Iterator iter = temp.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry entry = (Map.Entry)iter.next();
                String word = entry.getKey().toString();
                Float value = (float)Float.parseFloat(entry.getValue().toString()) * idfs.get(word);
                tfidf.put(word, value);
            }
            resTfIdf.put(i, tfidf);
        }
//        System.out.println("TF-IDF for Every file is :");
        output=DisTfIdf(resTfIdf);
        return output;
    }
    public static Map<Integer,List<String>> DisTfIdf(HashMap<Integer, HashMap<String, Float>> tfidf){
        Map<Integer,List<String>> output =new HashMap<Integer,List<String>>();
        Iterator iter1 = tfidf.entrySet().iterator();
        while(iter1.hasNext()){
            Map.Entry entrys = (Map.Entry)iter1.next();
//            String[] tmp_string=entrys.getKey().toString().split("/");
            int index = (int)entrys.getKey();
//            int tmp_len=tmp_string.length;
            System.out.println("index: " + index);
            System.out.print("{");
            HashMap<String, Float> temp = (HashMap<String, Float>) entrys.getValue();

            List<Map.Entry<String, Float>> wordMap = new ArrayList<HashMap.Entry<String, Float>>(temp.entrySet());

            Collections.sort(wordMap, new Comparator<Map.Entry<String, Float>>() {//根据value排序
                public int compare(Map.Entry<String, Float> o1,
                                   Map.Entry<String, Float> o2) {
                    double result = o2.getValue() - o1.getValue();
                    if(result > 0)
                        return 1;
                    else if(result == 0)
                        return 0;
                    else
                        return -1;
                }
            });

            int count =0;
            List<String> list=new ArrayList<String>();
            for(Map.Entry<String, Float>wordmap:wordMap){
                if(count<topicNumber){
                    System.out.print(wordmap.getKey().toString() + /*" = " + wordmap.getValue().toString()*/ ", ");
                    count++;
                    list.add(wordmap.getKey().toString());
                }
            }
            System.out.println("}");
            output.put(index,list);
        }
        return output;
    }

    public static Map<Integer,List<String>> tfIdf(List<String> input, int tN )throws IOException{
        Map<Integer,List<String>> output =new HashMap<Integer,List<String>>();
        FileList=input;
        topicNumber=tN;
        HashMap<Integer,HashMap<String, Float>> all_tf = tfAllFiles(input);
        System.out.println();
        HashMap<String, Float> idfs = idf(all_tf);
        System.out.println();
        output = tf_idf(all_tf, idfs);
        return output;
    }
    // 增加一个从数据库获取数据集进行tfIdf的函数，功能和tfIdf一致
    /*
    核心接口2，根据得到的文本数据，返回前topK个关键词
    参数：
    documents: 共两列
               第一列文件名，在数据库中属于key的字段
               第二列文件内容，在数据库中属于value的字段
               。。。可扩展列
    tN: 前top tN个关键词
    */
//    public static Map<String,List<String>>  tfIdfByMysql( Vector<Vector<String>> documents,int tN )throws IOException{
//        Map<String,List<String>> map = new HashMap<String,List<String>>();
//        topicNumber=tN;
//        HashMap<String,HashMap<String, Float>> all_tf = tfAllDocuments(documents);
//        System.out.println();
//        HashMap<String, Float> idfs = idf(all_tf);
//        System.out.println();
//        map = tf_idf(all_tf, idfs);
//        return map;
//    }
    //tf for all file
    public static HashMap<String,HashMap<String, Float>> tfAllDocuments( Vector<Vector<String>> documents) throws IOException{
        HashMap<String, HashMap<String, Float>> allTF = new HashMap<String, HashMap<String, Float>>();
        FileList = new ArrayList<String>();
        for(int i=0 ; i<documents.size() ; i++){
            HashMap<String, Float> dict = new HashMap<String, Float>();
            ArrayList<String> cutwords = TF_IDF.cutWordsByDocument(documents.get(i).get(1)); //get cut words for one file
            System.out.println("cutwords"+cutwords);
            dict = TF_IDF.tf(cutwords);
            System.out.println("cutwords"+dict);
            allTF.put(documents.get(i).get(0), dict); // 将(k,v):(文件名,文件内容词典)加入HashMap中
            FileList.add(documents.get(i).get(0));
        }
        return allTF;
    }
    // 增加一个功能，针对整个已有的文本进行分词
    //word segmentation
    public static ArrayList<String> cutWordsByDocument(String document) throws IOException{

        ArrayList<String> words = new ArrayList<String>();


        //开始分词
        List<Term> termList = HanLP.segment(document);

        for (int i = 0; i < termList.size() ; i++){

            words.add(termList.get(i).word);
        }

        //用来存放停用词的集合
        // ganjun update load stopwords function

        FileUtil.loadStopWords();

        Set<String> stopWordSet = FileUtil.stopWords;

        ArrayList<String> newwords=new ArrayList<String>();

        Iterator it = words.iterator();

        while(it.hasNext()){
            //把获取到的值强转成字符串类型
            String s = (String) it.next();
            //过滤数字
            String new_s=number_filter(s);
            //删除标点符号
            new_s=new_s.replaceAll("[\\pP\\pS\\pZ]", "");
            //过滤出中文
            new_s=new_s.replaceAll("[^(\\u4e00-\\u9fa5)]", "");
            if(stopWordSet.contains(new_s)||new_s.length()==0) {
                continue;
            }
            // 过滤停用词后
            newwords.add(new_s);
        }
        return newwords;
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub


        List<String> input = new ArrayList<String>();
        Map<Integer,List<String>> output =new HashMap<Integer,List<String>>();

        String documents = "程序员(英文Programmer)是从事程序开发、\n" +
                "维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，\n" +
                "但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程\n" +
                "序员、系统分析员和项目经理四大类。";
        String documents1 = "如果说李泽言跳舞是华锐破产了。\n" +
                "如果说许墨跳舞是研究做失败了。\n" +
                "如果说白起跳舞是做任务做疯了。\n" +
                "周棋洛表示：………………";
        input.add(documents);
        input.add(documents1);

        int tN = 5;
        output=tfIdf(input, tN);
        System.out.println("output:"+output);
    }

}

