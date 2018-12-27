package com.iip.ui.space_time.controller;

import com.iip.data.space_time.Orientation;
import com.iip.data.space_time.PeopleOrientation;
import com.iip.data.space_time.SpaceTimeData;
import com.iip.util.DistanceUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @Author Junnor.G
 * @Date 2018/12/11 下午6:33
 */
public class SpaceTimeViewController extends RootController implements Initializable{
    @FXML
    private ListView<String> LBEntityShow;
    @FXML
    private TreeView TrVEntityShow;

    /**
     * 记录当前选中的人经过的地方的路线图
     */
    private List<Orientation> orientations = new ArrayList<>();

    public ImageView loadImageView(String path, int width, int height){
        ImageView icon = new ImageView();
        Image image = new Image(getClass().getResourceAsStream(path));
        icon.setImage(image);
        icon.setFitWidth(width);
        icon.setFitHeight(height);
        return icon;
    }

    public void updateTrVEntityShow(List<PeopleOrientation> peopleOrientation){
        TreeItem treeItem = new TreeItem("人名总览");
        ImageView peopleIcon = loadImageView("../view/picture/people.png", 30, 30);
        treeItem.setGraphic(peopleIcon);

        for(int i=0 ; i<peopleOrientation.size() ; i++){
            PeopleOrientation peo = peopleOrientation.get(i);
            TreeItem peopleItem = new TreeItem(String.valueOf(i+1)+"."+peo.getName());
            ImageView menIcon = loadImageView("../view/picture/men.png", 28, 28);
            peopleItem.setGraphic(menIcon);
            for (Orientation ori: peo.getOrientations()){
                ImageView houseIcon = loadImageView("../view/picture/house.png", 16, 16);
                System.out.println("debug: " + ori.getPlace()+" ; " + ori.getDate().toString());
                TreeItem oriItem = new TreeItem(ori.getPlace()+" ; " + ori.getDate().toString());
                oriItem.setGraphic(houseIcon);
                peopleItem.getChildren().add(oriItem);
            }

            treeItem.getChildren().add(peopleItem);
            TrVEntityShow.setRoot(treeItem);
            TrVEntityShow.refresh();
        }
    }

    /**
     * 地图展示模块
     */
    @FXML
    private WebView WVChinaMap;

    private String locationsFormatToString(){
        String str = "";
        for (Orientation ori: orientations){
            str += String.valueOf(ori.getLng()) + "," + String.valueOf(ori.getLat()) + "&";
            System.out.println(str);
        }
        return str.substring(0, str.length()-1);
    }

    public void uploadChinaMap(){
        WebEngine engine = WVChinaMap.getEngine();
//        engine.loadContent("<b>asdf</b>");
//        engine.load("http://www.w3cschool.cn");
        String url = "http://localhost/HelloWorld/track.html?"+locationsFormatToString();
        System.out.println("debug url: "+url);
        engine.load(url);
//        engine.loadContent( readHtml("/Users/ganjun/Desktop/python/leetcode/render.html") );
    }

    public void showMapInfoView(){
        // 判断是否选中的是人
        System.out.println("haha");
        TreeItem item = (TreeItem)TrVEntityShow.getSelectionModel().getSelectedItem();
        System.out.println("xixi");
        System.out.println("Selected Text : " + item.getValue());
        if (item.getValue().toString().equals("人名总览")){

        }
        else if (item.getParent().getValue().toString().equals("人名总览") == true){

            // 获得这个人的id
            int id = Integer.valueOf( item.getValue().toString().split("[.]")[0] ) - 1;
            System.out.println("It's name： "+item.getValue().toString() + id);
            // 对所有在这个人身上所处的位置计算整个的经纬度信息
            orientations.clear();
            for(int i=0 ; i<item.getChildren().size() ; i++){
                Object loc = item.getChildren().get(i);
                String temp = ((TreeItem)loc).getValue().toString();
                String [] vals = temp.split(";");
                double [] pos = DistanceUtil.getLatitude(vals[0]);
                SpaceTimeData.peopleOrientations.get(id)
                        .getOrientations().get(i).setLng(pos[0]);
                SpaceTimeData.peopleOrientations.get(id)
                        .getOrientations().get(i).setLat(pos[1]);

                orientations.add( SpaceTimeData.peopleOrientations.get(id)
                        .getOrientations().get(i) );
            }
            // 将这个人的经过的路线打印在地图上
            uploadChinaMap();
        }
        else if(item.getParent().getParent().getValue().toString().equals("人名总览") == true){
            System.out.println("It's location");

            orientations.clear();
            String temp = item.getValue().toString();
            String [] vals = temp.split(";");
            double [] pos = DistanceUtil.getLatitude(vals[0]);

            orientations.add(  new Orientation(vals[0], new Date(), pos[0], pos[1]) );
            // 将这个地点打印在地图上
            uploadChinaMap();
        }
    }

    @FXML
    public void mapInfoClicked(){
//        System.out.println("here");
        showMapInfoView();
    }

    @Override
    public void init(){
        // todo
        test();
//        uploadChinaMap();

        TrVEntityShow.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if(mouseEvent.getClickCount() == 2) {
                    showMapInfoView();
                }
            }
        });


    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        init();
    }

    // 构造一个测试函数，构建一个peopleOrientations用于TreeView的展示
    private void test(){
//        SpaceTimeData.peopleOrientations.clear();
//
//        PeopleOrientation item1 = new PeopleOrientation();
//        item1.setName("Jack");
//        item1.getOrientations().add(new Orientation("清华大学", new Date(2018, 1, 2)));
//        item1.getOrientations().add(new Orientation("中南大学", new Date(2018, 1, 3)));
//        SpaceTimeData.peopleOrientations.add(item1);
//
//        PeopleOrientation item2 = new PeopleOrientation();
//        item2.setName("Tom");
//        item2.getOrientations().add(new Orientation("舟山群岛", new Date(2017, 2, 2)));
//        item2.getOrientations().add(new Orientation("九寨沟", new Date(2018, 2, 3)));
//        SpaceTimeData.peopleOrientations.add(item2);

        updateTrVEntityShow(SpaceTimeData.peopleOrientations);

    }
}
