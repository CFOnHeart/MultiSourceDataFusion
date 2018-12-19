package com.iip.ui.space_time.controller;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Author Junnor.G
 * @Date 2018/12/5 下午8:18
 */
public class EntityViewController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources){
        // todo
    }

    public static void main(String [] args){
        String[] testCase = new String[]{
                "我十月三号十二点在上海林原科技有限公司兼职工作，",
                "同时在上海外国语大学日本文化经济学院学习经济与外语。",
                "我经常在台川喜宴餐厅吃饭，",
                "偶尔去地中海影城看电影。",
        };
        Segment segment = HanLP.newSegment();
        for (String sentence : testCase)
        {
            List<Term> termList = segment.seg(sentence);
            System.out.println(termList);
        }
    }
}
