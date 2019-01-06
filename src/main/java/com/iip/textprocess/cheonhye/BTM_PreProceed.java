package com.iip.textprocess.cheonhye;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class BTM_PreProceed {

        public static List<String> toNormal(List<String> input) throws IOException{


        //List<String> filelist = textprocess.util.FileUtil.getAllFilePath(dirc);
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(new File("./toNormal.txt")), "UTF-8"));

        for(String file : input){
            ArrayList<String> cutwords = TF_IDF.cutWords(file); //get cut words for one file
            for(String word: cutwords){
                bw.write(word);
                bw.write(" ");
            }
            bw.newLine();
        }
        bw.close();
        return input;
    }

    // 下方是为了辅助给从数据库读取的数据集功能进行主题模型的
    public static List<String> toNormalByDocuments(Vector<Vector<String>> documents) throws IOException{

        List<String> filelist = new ArrayList<String>();
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(new File("./toNormal.txt")), "UTF-8"));

        for(int i=0 ; i<documents.size() ; i++){
            ArrayList<String> cutwords = TF_IDF.cutWords(documents.get(i).get(1)); //get cut words for one file
            for(String word: cutwords){
                bw.write(word);
                bw.write(" ");
            }
            bw.newLine();
            filelist.add(documents.get(i).get(0));
        }
        bw.close();
        return filelist;
    }

}
