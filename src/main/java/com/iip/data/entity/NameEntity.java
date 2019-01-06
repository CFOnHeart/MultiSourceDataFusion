package com.iip.data.entity;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Junnor.G
 * @Date 2018/12/19 下午2:06
 */
public class NameEntity extends Entity{

    public NameEntity(String word){
        super(word);
    }

    @Override
    public String getType(){
        return this.type;
    }

    public static List<Entity> entityRecognition(String sentence){
        Segment segment = HanLP.newSegment();
        segment.enableNameRecognize(true);
        segment.enableTranslatedNameRecognize(true);
        segment.enableJapaneseNameRecognize(true);

        List<Term> termList = segment.seg(sentence);
        System.out.println(termList);
        List<Entity> entities = new ArrayList<>();
        for (Term term: termList){
            if ( term.nature.name().contains("nr") )
                entities.add( new NameEntity(term.word) );
        }
        return entities;
    }
}
