package com.iip.ui.ner.Resource;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;


import java.util.ArrayList;
import java.util.List;

public class SingleDocParticiple {
    private int id;
    private String text;
    private String participleResult = "";
    private String posResult = "";
//    private String dateStr = "";
//    private Date date;
    private List<Term> words = new ArrayList<>();

    // 将words转化为字符串的表达形式，方便转换给participleResult
    public String toString(){
        String res = "";
        for(Term word: words){
            res += word.word+";";
        }
        return res;
    }
    public String toStringPos(){
        String res = "";
        for(Term word: words){
            res += word.word+"("+word.nature+")"+";";
        }
        return res;
    }


    public void participleHanlp(){

        Segment segment = HanLP.newSegment();
        words = segment.seg(text);
        participleResult = toString();
//        posResult=toStringPos();
    }
    public void posHanlp(){

        Segment segment = HanLP.newSegment();
        words = segment.seg(text);
//        participleResult = toString();
        posResult=toStringPos();
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
//        int index = text.indexOf(')');
//        this.text = text.substring(index+1);
//        this.dateStr = text.substring(1, index);
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try{
//            date = simpleDateFormat.parse(dateStr);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
        this.text=text;
    }

    public String getParticipleResult() {
        return participleResult;
    }

    public void setParticipleResult(String participleResult) {
        this.participleResult = participleResult;
    }

    public String getPosResult() {
        return posResult;
    }

    public void setPosResult(String posResult) {
        this.posResult = posResult;
    }


//    public List<Word> getWords() {
//        return words;
//    }

//    public void setWords(int index, Word word){
//        words.set(index, word);
//    }

//    public String getDateStr() {
//        return dateStr;
//    }

//    public void setDateStr(String dateStr) {
//        this.dateStr = dateStr;
//    }

//    public Date getDate() {
//        return date;
//    }

//    public void setDate(Date date) {
//        this.date = date;
//    }

//    public void setWords(List<Word> words) {
//        this.words = words;
//    }

    // for test
    public static void main(String [] args){
        com.iip.ui.ner.Resource.SingleDocParticiple item = new com.iip.ui.ner.Resource.SingleDocParticiple();
        item.setId(1);
        item.setText("济南杨铭宇餐饮管理有限公司是由杨先生创办的餐饮企业，晚上九点去吃饭，2008年5月3日北京今天很热");
        System.out.println(item.text);
        item.posHanlp();
        System.out.println(item.posResult);
    }
}
