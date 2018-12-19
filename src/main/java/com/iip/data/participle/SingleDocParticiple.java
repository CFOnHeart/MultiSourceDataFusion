package com.iip.data.participle;

import com.iip.textprocess.participle.Participle;
import com.iip.textprocess.participle.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Junnor.G
 * @Date 2018/12/17 下午4:49
 * 对于单个文本的分词结果的保存，为了构建与 java fx tableview中table column的数据映射
 */
public class SingleDocParticiple {
    private int id;
    private String text;
    private String participleResult = "aaa";
    private List<Word> words = new ArrayList<>();

    // 将words转化为字符串的表达形式，方便转换给participleResult
    public String toString(){
        String res = "";
        for(Word word: words){
            res += word.word+"("+word.tag+");";
        }
        return res;
    }

    public void participleHanlp(){
        words = Participle.participle(text);
        participleResult = toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParticipleResult() {
        return participleResult;
    }

    public void setParticipleResult(String participleResult) {
        this.participleResult = participleResult;
    }


    public List<Word> getWords() {
        return words;
    }

    public void setWords(int index, Word word){
        words.set(index, word);
    }

    // for test
    public static void main(String [] args){
        SingleDocParticiple item = new SingleDocParticiple();
        item.setId(1);
        item.setText("济南杨铭宇餐饮管理有限公司是由杨先生创办的餐饮企业，晚上九点去吃饭，2008年5月3日北京今天很热");
        item.participleHanlp();
        System.out.println(item.participleResult);
    }
}
