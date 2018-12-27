package com.iip.data.space_time;

/**
 * @Author Junnor.G
 * @Date 2018/12/6 下午5:44
 */
import com.iip.data.entity.Entity;
import com.iip.data.entity.PlaceEntity;
import com.iip.data.entity.SingleDocEntity;
import com.iip.data.participle.SingleDocParticiple;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

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
    public static ObservableList<SingleDocEntity> entityItems = FXCollections.observableArrayList();
    public static List<PeopleOrientation> peopleOrientations = new ArrayList<>();


    public static void
        entityItemsToPeopleOrientations(){

        // first step
        Map<String, List<Orientation> > hashMapPeopleOrientations = new HashMap<>();

        for(SingleDocEntity item: entityItems){
            // 如果存在了 organization 的实体，就不需要将place的实体加入了
            Entity placeEntityTemp = null;
            List<String> names = new ArrayList<>();
            boolean isAdd = false;
            List<Orientation> orientations = new ArrayList<>();
            for(Entity entity: item.getEntities()){
                if (isAdd == false && entity.getType().equals("place")){
                    placeEntityTemp = entity;
                }

                if (entity.getType().equals("organization")){
                    isAdd = true;
                    placeEntityTemp = null;
                    orientations.add(new Orientation(entity.word, new Date()));
                }
                if (entity.getType().equals("name")){
                    names.add(entity.word);
                }
            }

            if (orientations.size() == 0 && placeEntityTemp != null)
                orientations.add(new Orientation(placeEntityTemp.word, new Date()));

            if(names.size() == 0) continue;
            for (String name: names){
                if(hashMapPeopleOrientations.containsKey(name)){
//                    System.out.println(name+":"+hashMapPeopleOrientations.get(name).size());
                    List<Orientation> list = hashMapPeopleOrientations.get(name);
                    list.addAll(orientations);
                    hashMapPeopleOrientations.put(name, list);

                }
                else{
                    hashMapPeopleOrientations.put(name, orientations);
                }
            }
        }

        // second step
        peopleOrientations.clear();
        for(String name: hashMapPeopleOrientations.keySet()){
            PeopleOrientation peopleOrientation = new PeopleOrientation();
            peopleOrientation.setName(name);
            peopleOrientation.setOrientations(hashMapPeopleOrientations.get(name));
            peopleOrientations.add(peopleOrientation);
        }
    }
}
