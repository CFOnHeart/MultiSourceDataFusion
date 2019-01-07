package com.iip.ui.feature_extraction.execute;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.ArrayList;
import java.util.List;

public class Participle {

    public static String appendWords = "";
    public static List<List<String>> partedDocs = new ArrayList<List<String>>();

    public static List<String> getPartedWords(String doc){
        List<Term> a = HanLP.segment(doc);
        List<String> words = new ArrayList<String>();
        appendWords = "";
        for(int i=0; i<a.size(); i++){
            String word = a.get(i).toString().split("/")[0];
            words.add(word);
            appendWords += word + " ";
//            print(words.get(i));
        }
        return words;
    }

    public static List<String> getPartedDocs(List<String> docs){
        partedDocs.clear();
        List<String> appendDocs = new ArrayList<String>();
        for(String doc: docs){
            List<String> words = getPartedWords(doc); // split words
            partedDocs.add(words);
            appendDocs.add(appendWords);
        }
        return appendDocs;
    }

    public static void print(Object str){System.out.println(str);}

}
