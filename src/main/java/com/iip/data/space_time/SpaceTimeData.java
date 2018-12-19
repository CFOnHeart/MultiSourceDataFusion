package com.iip.data.space_time;

/**
 * @Author Junnor.G
 * @Date 2018/12/6 下午5:44
 */
import com.iip.data.entity.Entity;
import com.iip.data.participle.SingleDocParticiple;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
/**
 * 饿汉单例模式
 */
public class SpaceTimeData {


//    public static SpaceTimeData getData(){
//        return data;
//    }
//
//    public List<String> getRawData(){return rawData;}
//
//    private static void appendData(String dataset){
//        getData().rawData.add(dataset);
//    }
//
//    public synchronized static void appendData(List<String> datasets){
//        for (String dataset: datasets)
//            getData().rawData.add(dataset);
//    }
//
//    public ObservableList<String> getRawDataList() { return rawDataList; }
//
//    public ObservableList<String> getHandledDataList() { return handledDataList; }
//
//    private static SpaceTimeData data = new SpaceTimeData();
//
//    private List<String> rawData = new ArrayList<>(); // 源文本
//    private List< List<Entity> > entities = new ArrayList<>();

    public static ObservableList<String> rawDataList = FXCollections.observableArrayList();
    public static ObservableList<String> handledDataList = FXCollections.observableArrayList();
    public static ObservableList<SingleDocParticiple> participleItems = FXCollections.observableArrayList();
    public static List<PeopleOrientation> peopleOrientations = new ArrayList<>();
}
