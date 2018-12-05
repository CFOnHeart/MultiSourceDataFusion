package com.iip.ui.space_time.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author Junnor.G
 * @Date 2018/12/5 下午7:56
 */
public class LoadDataViewController implements Initializable{
    @FXML
    private ListView<String> LVRawDataListView;
    @FXML
    private ListView<String> LVHandledDataListView;

    private ObservableList<String> rawDataList = FXCollections.observableArrayList();
    private ObservableList<String> handledDataList = FXCollections.observableArrayList();

    private String [] tmpData = {"hhh", "hhhh", "hhhhhh"};

    @FXML
    private void loadDataClicked(MouseEvent mouseEvent){
        rawDataList.addAll(tmpData);
        LVRawDataListView.setItems(rawDataList);
    }
    @FXML
    private void handleDataClicked(MouseEvent mouseEvent){

    }
    @Override
    public void initialize(URL location, ResourceBundle resources){

    }
}
