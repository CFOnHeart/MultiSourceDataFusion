package com.iip.ui.space_time.controller;

import com.iip.data.space_time.SpaceTimeData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @Author Junnor.G
 * @Date 2018/12/11 下午6:33
 */
public class SpaceTimeViewController extends RootController implements Initializable{
    @FXML
    private ListView<String> LVData;
    @FXML
    private Label LBData;
    @FXML
    private TextField TFHintLoad;
    @FXML
    private Button BtnHintLoad;

    private void hintLoadClicked(MouseEvent event){
//        SpaceTimeMainViewController.LblMainTitle.setText("Load Data");
//        SpaceTimeMainViewController.APLoadData.getStylesheets().clear();
//        SpaceTimeMainViewController.APLoadData.getStylesheets().add("menuButtonSelected");
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(SpaceTimeMainViewController.class.getResource("../view/LoadDataView.fxml"));
//            AnchorPane connectionConfigPane = loader.load();
//            connectionConfigPane.setPrefSize(SpaceTimeMainViewController.BPMainViewPane.getWidth()-60, SpaceTimeMainViewController.BPMainViewPane.getHeight()-60);
//            SpaceTimeMainViewController.BPMainViewPane.setCenter(connectionConfigPane);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        SpaceTimeMainViewController.currentMenuPane = SpaceTimeMainViewController.APLoadData;
    }
    @Override
    public void init(){
        // todo
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        init();
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
                    SpaceTimeMainViewController controller = (SpaceTimeMainViewController)Context.controllers.get(SpaceTimeMainViewController.class.getSimpleName());
                    controller.presentLoginView();
                }
            });
        }
    }
}
