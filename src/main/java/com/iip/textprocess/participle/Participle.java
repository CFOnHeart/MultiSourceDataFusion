package com.iip.textprocess.participle;

/**
 * @Author Junnor.G
 * @Date 2018/12/17 下午5:00
 */
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.iip.textprocess.util.FileUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class Participle {

    public static Set<String> userStopWords = new HashSet<>();

    public static List<Word> participle(String doc){
        List<Word> words = new ArrayList<>();

        // method1: han nlp
        List<Term> termList = participleHanNlp(doc);
        for(Term term : termList){
            Nature nature = term.nature; // 词性
            words.add(new Word(term.word, nature.name()));
        }
        return words;
    }

    // 利用HanNlp包得到的分词结果
    public static List<Term> participleHanNlp(String doc){
        List<Term> words = new ArrayList<>();
        // 分词
        Segment segment= HanLP.newSegment();
        List<Term> termList = segment.seg(doc);
        /**
         * 去除停用词
         * 将词性不重要的词去除,只保留名词、动词、形容词、副词,以及删除停词表中的词语
         * hanlp词性标注表示可参考 http://www.hankcs.com/nlp/part-of-speech-tagging.html
         */
        FileUtil.loadStopWords(); //加载默认的停词表文件

        for(Term term : termList){
            if(FileUtil.stopWords.contains(term.word) || userStopWords.contains(term.word)) continue;
            Nature nature = term.nature; // 词性
            if(nature.firstChar()  == 'n' || nature.firstChar() == 'v'
                    || nature.firstChar() == 'a' || nature.firstChar() == 'd'){
                words.add(term);
            }
        }
        return words;
    }

}

