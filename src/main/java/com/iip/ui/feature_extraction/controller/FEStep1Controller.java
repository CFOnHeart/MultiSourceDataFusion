package com.iip.ui.feature_extraction.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class FEStep1Controller {

    @FXML
    private ListView<String> rawDataListView;
    private ObservableList<String> rawDataList = FXCollections.observableArrayList();

    @FXML
    private ListView<String> handledDataListView;
    private ObservableList<String> handledDataList = FXCollections.observableArrayList();

    private String [] tmpData = {"hhh", "hhhh", "hhhhhh"};

    @FXML
    private void loadDataClicked(MouseEvent event){
        rawDataList.addAll(tmpData);
        rawDataListView.setItems(rawDataList);
    }

    @FXML
    private void handleDataClicked(MouseEvent event){

    }

}
