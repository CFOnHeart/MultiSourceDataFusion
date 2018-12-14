package com.iip.ui.space_time.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
//        rawDataList.addAll(tmpData);
//        LVRawDataListView.setItems(rawDataList);
        // 根据文件选择器选取文件加载要读入的数据
        Stage stage = (Stage) LVRawDataListView.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        // 从获取到的文件中读入文本数据，以行为单位
        List<String> tmpData = new ArrayList<>();
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                tmpData.add(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
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
