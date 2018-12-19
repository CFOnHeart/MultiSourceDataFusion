package com.iip.ui.space_time.controller;

import com.iip.data.space_time.Orientation;
import com.iip.data.space_time.PeopleOrientation;
import com.iip.data.space_time.SpaceTimeData;
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

        for(PeopleOrientation peo: peopleOrientation){
            TreeItem peopleItem = new TreeItem(peo.getName());
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

    @Override
    public void init(){
        // todo
        test();


    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        init();
    }

    // 构造一个测试函数，构建一个peopleOrientations用于TreeView的展示
    private void test(){
        SpaceTimeData.peopleOrientations.clear();

        PeopleOrientation item1 = new PeopleOrientation();
        item1.setName("Jack");
        item1.getOrientations().add(new Orientation("Starbuck", new Date(2018, 1, 2)));
        item1.getOrientations().add(new Orientation("网鱼网咖", new Date(2018, 1, 3)));
        SpaceTimeData.peopleOrientations.add(item1);

        PeopleOrientation item2 = new PeopleOrientation();
        item2.setName("Tom");
        item2.getOrientations().add(new Orientation("大渝火锅", new Date(2017, 2, 2)));
        item2.getOrientations().add(new Orientation("海底捞", new Date(2018, 2, 3)));
        SpaceTimeData.peopleOrientations.add(item2);

        updateTrVEntityShow(SpaceTimeData.peopleOrientations);

    }
}
