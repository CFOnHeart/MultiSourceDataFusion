package com.iip.textprocess.word2vec;

import com.iip.textprocess.word2vec.Doc2vec;

public class test {
    public static void main(String args[]){
        String vecsPath = "src/main/resources/zhwiki_2017_03.sg_50d.word2vec";
        String documents1 = "程序员(英文Programmer)是从事程序开发、\n" +
                "维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，\n" +
                "但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程\n" +
                "序员、系统分析员和项目经理四大类。";
        String documents2 = "这里面是原味牛乳制品";
        float []v1 = Doc2vec.doc2vec(documents1, vecsPath);
        float []v2 = Doc2vec.doc2vec(documents2, vecsPath);
        System.out.println("" + v1[0] + " " + v1.length);
        System.out.println("" + v2[0] + " " + v2.length);
    }
}
