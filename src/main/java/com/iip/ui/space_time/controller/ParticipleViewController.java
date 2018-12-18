package com.iip.ui.space_time.controller;

import com.iip.data.participle.SingleDocParticiple;
import com.iip.data.space_time.SpaceTimeData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Author Junnor.G
 * @Date 2018/12/5 下午8:18
 */
public class ParticipleViewController extends RootController implements Initializable {

    @FXML
    private ListView<String> LVData;
    @FXML
    private Label LBData;
    @FXML
    private TextField TFHintLoad;
    @FXML
    private Button BtnHintLoad;
    @FXML
    private TabPane TPParticiple;


//    @FXML
//    public void participleAllClicked(MouseEvent event){
//        System.out.println("here");
////        System.out.println( "Here debug: "+getTPParticiple().getSelectionModel().getSelectedIndex() );
//    }
//    @FXML
//    public void participleSelectClicked(MouseEvent event){
//        System.out.println("here");
//    }

    public Tab generateNewTab(String id, String text){
        Tab tab = new Tab();
        tab.setId(id);
        tab.setText(text);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ParticipleTabViewController.class.getResource("../view/ParticipleTabView.fxml"));
            BorderPane pane = loader.load();
            tab.setContent(pane);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
        return tab;
    }

    @Override
    public void init(){
        System.out.println("In Participle View");
        // 数据如果已经加载过，就不显示加载数据的提示
        if( SpaceTimeData.handledDataList.size() > 0 ){
            BtnHintLoad.setOpacity(0.0f);
            TFHintLoad.setOpacity(0.0f);
        }
        else{
            BtnHintLoad.setOpacity(1.0f);
            TFHintLoad.setOpacity(1.0f);
            BtnHintLoad.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    SpaceTimeMainViewController controller =
                            (SpaceTimeMainViewController)Context.controllers.get(SpaceTimeMainViewController.class.getSimpleName());
                    controller.presentLoginView();
                }
            });
        }

        // 加载TabView fxml
        Tab tab1 = generateNewTab("Tab1", "Method1");
        if(tab1 != null) TPParticiple.getTabs().add(tab1);
        Tab tab2 = generateNewTab("Tab2", "Method2");
        if(tab2 != null) TPParticiple.getTabs().add(tab2);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        System.out.println("In participle initialize");
        init();
    }

    public TabPane getTPParticiple(){ return TPParticiple; }
}
