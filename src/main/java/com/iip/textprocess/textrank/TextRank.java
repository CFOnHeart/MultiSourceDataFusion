package com.iip.textprocess.textrank;

/**
 * Created by ganjun on 2018/1/3.
 */
import com.hankcs.hanlp.seg.common.Term;
import com.iip.textprocess.util.FileUtil;

import java.io.File;
import java.util.*;

public class TextRank {

    TextRankGraph graph ;
    double d;  // 阻尼系数
    public TextRank(){
        d = 0.5;
        graph = new TextRankGraph();
    }

    public TextRank(int k , double d){
        this.d = d;
        graph = new TextRankGraph(k);
    }

    public TextRank(List<String> wordsList , int k , double d){
        this.d = d;
        graph = new TextRankGraph(wordsList , k);
    }

    public void addSentence(List<String> wordsList){
        graph.addSentence(wordsList);
    }

    public void addSentenceTerm(List<Term> termList){
        List<String> wordsList = new ArrayList<String>();
        for(Term term: termList){
            wordsList.add(term.word);
        }
        graph.addSentence(wordsList);
    }

    // 计算每个词语在文档中的权重值
    public List<Double> calWordRankValue(){
        // 初始化所有词都是平均的
        List<Double> weights = new ArrayList<Double>();
        List<Double> tmp = new ArrayList<Double>();
        System.out.println("graph.getWordId()"+graph.getWordId());
        for(int i=0 ; i<graph.getWordId() ; i++){
            weights.add(1.0 / graph.getWordId());
            tmp.add(0.0);
        }

        // 规定一个迭代次数
        int MAX_ITERS = 100;
        for(int iter = 1 ; iter <= MAX_ITERS ; iter++){
            for(int u=0 ; u<graph.getWordId() ; u++){
                double value = 1-d;
                double sum = 0;
                for(int v : graph.getEdges().get(u)){
                    sum += weights.get(v) * (1.0 / graph.getEdges().get(v).size());
                }
                value += d * sum;
                tmp.set(u , value);
            }
            for(int u=0 ; u<graph.getWordId() ; u++) weights.set(u , tmp.get(u));
        }
        return weights;
    }

    // 根据权重输出排名前名的 topK 个单词 , 如果总的单词个数不超过topK个，那么就输出所有的单词
    public List<Word> getFirstKWords(List<Double> weights , int topK){
        List<Word> words = new ArrayList<Word>();

        for(int i=0 ; i<weights.size() ; i++){
            words.add(new Word(weights.get(i) , i , graph.getWords().get(i)));
        }
        Collections.sort(words);

        return kWords(words , topK);
    }
    // 根据已得到的权重词，进行排序，选择出前topK个返回
    public List<Word> kWords(List<Word> words , int topK){
        List<Word> ret = new ArrayList<Word>();

        for(int i=0 ; i<words.size() && i<topK ; i++){
            ret.add(words.get(i));
        }
        return ret;
    }

    /*
    核心接口，根据文件，返回前topK个关键词
    参数：
    dirPath: 文件目录路径
    topK: 前topK个关键词
    k: textrank 的窗口大小
    d: 阻尼系数
    输出：
    String 表示文件相对路径
    List<String> 表示对应文件下的keywords列表
     */
    public static Map<String, List<String>> topKWordsFromFile(String dirPath , int topK , int k , double d){
        Map<String, List<String>> keyWords = new HashMap<String, List<String>>();
        FileUtil.loadStopWords();
        List<String> filePaths = FileUtil.getAllFilePath(dirPath);
        for(String path : filePaths) {
            List<List<Term>> sentences = Participle.fileParticiple(path);
            TextRank textRank = new TextRank(k, d);
            for (List<Term> sentence : sentences) {
                textRank.addSentenceTerm(sentence);
            }
            List<Double> weights = textRank.calWordRankValue();
            List<Word> ret = textRank.getFirstKWords(weights, topK);
            List<String> keys = new ArrayList<String>();
            for(Word word : ret) keys.add(word.getWord());
            keyWords.put(path , keys);
        }

        return keyWords;
    }

    /*
    核心接口2，根据得到的文本数据，返回前topK个关键词
    参数：
    documents: 共两列
               第一列文件名，在数据库中属于key的字段
               第二列文件内容，在数据库中属于value的字段
               。。。可扩展列
    topK: 前topK个关键词
    k: textrank 的窗口大小
    d: 阻尼系数
    输出：
    String 表示文件相对路径
    List<String> 表示对应文件下的keywords列表
     */
    public static Map<String, List<String>> topKWordsFromMysql(Vector<Vector<String>> documents,
                                                               int topK , int k , double d){
        Map<String, List<String>> keyWords = new HashMap<String, List<String>>();
        FileUtil.loadStopWords();
        for(int i=0 ; i<documents.size() ; i++) {
            List<String> keys = topKWords(documents.get(i).get(1), topK, k, d);
            keyWords.put(documents.get(i).get(0) , keys);
        }

        return keyWords;
    }

    /*核心接口，传入一个文本字符串，传出关键词的个数*/
    public static List<String> topKWords(String documents, int topK , int k , double d){
        Map<String, List<String>> keyWords = new HashMap<String, List<String>>();
        FileUtil.loadStopWords();

        List<List<Term>> sentences = Participle.fileParticipleByMysql(documents);
        TextRank textRank = new TextRank(k, d);
        for (List<Term> sentence : sentences) {
            textRank.addSentenceTerm(sentence);
        }
        List<Double> weights = textRank.calWordRankValue();
        List<Word> ret = textRank.getFirstKWords(weights, topK);
        List<String> keys = new ArrayList<String>();
        for(Word word : ret) keys.add(word.getWord());
        return keys;
    }
    public static List<String> topKWords(String documents, int topK){
        return topKWords(documents, topK, 3, 0.5);
    }

    public static void main(String [] args){
        String documents = "通过物资扶持，技术扶持，技能培训等帮扶措施，综合政策，实现脱贫。";
        List<String> keys = topKWords(documents, 5);
        for(String key : keys){
            System.out.println(key);
        }
    }
}
