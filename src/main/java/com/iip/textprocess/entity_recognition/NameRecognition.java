package com.iip.textprocess.entity_recognition;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.iip.textprocess.util.FileUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

public class NameRecognition {
//    private static Logger logger = LoggerFactory.getLogger(NameRecognition.class);
    public static void main(String[] args){
//        logger.info("Test github");
        Map<String , List<String>> ret = getNameFromDir("ganjun_testdata");
        for(String path : ret.keySet()){
            System.out.println(path + "->");
            for(String str : ret.get(path)){
                System.out.println(str + " " );
            }
            System.out.println("");
        }
//
    }
    // 将字符串中的所有人名实体提取出来(暂时先将整个文件所有的数据读到一起，作为一整个字符串)
    public static List<String> getName(String s){
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        List<Term> termList = segment.seg(s);
        List<String> nr = new ArrayList<String>();

        for(Term term : termList){
            if (term.nature == Nature.valueOf("nr") || term.nature == Nature.valueOf("nrf"))
                nr.add(term.word);
        }

        return nr;
    }
    // 从文件中目录中提取实体，每一个List<String>代表一个文件中的
    public static Map<String , List<String>> getNameFromDir(String dirPath){
        Map<String , List<String>> twoDimensionsEntities = new HashMap<String , List<String>>();
        List<String> filePaths = FileUtil.getAllFilePath(dirPath);
        for(String path : filePaths){
            List<String> entities = new ArrayList<String>();
            String doc = FileUtil.readFileAsString(path);
            twoDimensionsEntities.put(path , getName(doc));
        }

        return twoDimensionsEntities;
    }

    // 为从数据库得到的数据增加接口
    public static Map<String, List<String>> getNameByMysql(Vector<Vector<String>> documents){
        Map<String , List<String>> twoDimensionsEntities = new HashMap<String , List<String>>();
        for (int i=0 ; i<documents.size() ; i++){
            twoDimensionsEntities.put(documents.get(i).get(0), getName(documents.get(i).get(1)));
        }
        return twoDimensionsEntities;
    }
}
