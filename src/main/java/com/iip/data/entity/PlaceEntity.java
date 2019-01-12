package com.iip.data.entity;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Junnor.G
 * @Date 2018/12/6 下午6:12
 * 地点实体类
 */
public class PlaceEntity extends Entity{

    public PlaceEntity(String word){
        super(word);
    }
    @Override
    public String getType(){
        return this.type;
    }

    public void hello(){
        System.out.println("Place Entity " + word +" Hello!");
    }

    public static List<Entity> entityRecognition(String sentence){
        Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
/**
 * hanlp地名识别可参考 http://hanlp.linrunsoft.com
 */
        List<Term> termList = segment.seg(sentence);
        System.out.println(termList);
        List<Entity> entities = new ArrayList<>();
        for (Term term: termList){
            if ( term.nature.toString().equals("ns") )
                entities.add( new PlaceEntity(term.word) );
        }
        return entities;
    }
}
