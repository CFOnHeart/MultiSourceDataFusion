package com.iip.data.entity;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Junnor.G
 * @Date 2018/12/25 下午4:25
 */
public class OrganizationEntity extends Entity {
    public OrganizationEntity(String word){
        super(word);
    }

    @Override
    public String getType(){
        return this.type;
    }

    public static List<Entity> entityRecognition(String sentence){
        Segment segment = HanLP.newSegment();
        segment.enableOrganizationRecognize(true);

        List<Term> termList = segment.seg(sentence);
        System.out.println(termList);
        List<Entity> entities = new ArrayList<>();
        for (Term term: termList){
            if ( term.nature.name().contains("nt") )
                entities.add( new OrganizationEntity(term.word) );
        }
        return entities;
    }
}
