package com.iip.textprocess.classification;

/**
 * @Author Junnor.G
 * @Date 2018/8/6 下午3:32
 */
import java.util.Vector;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Bayes {
    private Vector<String> labels; // 所有的标签,这里面保存的是不同的标签
    private int cntOfLabels;
    private Map<String, Integer> labelToId;

    private List<Double> sumOfAttribute; // 对于每一列属性值的总和

    public Bayes(Vector<String> allLabels){
        labels = new Vector<String>();
        labelToId = new HashMap<String, Integer>();
        cntOfLabels = 0;
        Set<String> set = new HashSet<String>();
        for(String label : allLabels){
            if(set.contains(label) == false){
                labels.add(label);
                labelToId.put(label, cntOfLabels);
                cntOfLabels++;
            }
        }
    }

    public void train(Vector<Vector<Double>> trainData, Vector<String> trainLabel){

    }

    /**
     *
     * @param data
     * @return 对一条数据预测出其类别
     */
    public String predict(Vector<Double> data){
        return null;
    }
}
