package com.iip.textprocess.entity_recognition;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.iip.textprocess.util.FileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Junnor.G
 * @Date 2018/8/28 下午6:57
 */
public class OrganizationRecognition {
    public static void main(String[] args){
//        logger.info("Test github");
//        Map<String , List<String>> ret = getNameFromDir("ganjun_testdata");
//        for(String path : ret.keySet()){
//            System.out.println(path + "->");
//            for(String str : ret.get(path)){
//                System.out.println(str + " " );
//            }
//            System.out.println("");
//        }
        String doc = "济南杨铭宇餐饮管理有限公司是由杨先生创办的餐饮企业，晚上九点去吃饭，2008年5月3日北京今天很热";
        List<String> orgs = getOrganization(doc);
        for(String org : orgs){
            System.out.println(org);
        }
//
    }
    // 将字符串中的所有机构或者地点实体提取出来(暂时先将整个文件所有的数据读到一起，作为一整个字符串)
    public static List<String> getOrganization(String sentence){
        Segment segment = HanLP.newSegment().enableOrganizationRecognize(true);
        List<Term> termList = segment.seg(sentence);
        List<String> nt = new ArrayList<String>();

        for (Term term : termList){
//            System.out.println("debug: "+term.nature+term.word);
            if(term.nature == Nature.valueOf("nt")){
                nt.add(term.word);
            }
        }
        return nt;
    }
    // 从文件中目录中提取实体，每一个List<String>代表一个文件中的
    public static Map<String , List<String>> getNameFromDir(String dirPath){
        Map<String , List<String>> twoDimensionsEntities = new HashMap<String , List<String>>();
        List<String> filePaths = FileUtil.getAllFilePath(dirPath);
        for(String path : filePaths){
            List<String> entities = new ArrayList<String>();
            String doc = FileUtil.readFileAsString(path);
            twoDimensionsEntities.put(path , getOrganization(doc));
        }

        return twoDimensionsEntities;
    }
}
