package com.iip.ui.ner.controller;


import com.iip.ui.ner.Resource.nerData;
import com.iip.ui.ner.Resource.SingleDocParticiple;
import com.iip.ui.ner.Resource.SingleDocEntity;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoadDataViewController extends RootController implements Initializable {
    @FXML
    private ListView<String> LVRawDataListView;
    @FXML
    private ListView<String> LVHandledDataListView;


    @FXML
    private void loadDataByFileClicked(MouseEvent mouseEvent){
        // 根据文件选择器选取文件加载要读入的数据
        Stage stage = (Stage) LVRawDataListView.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if(file == null) return;
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
//            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        nerData.rawDataList.clear();
        nerData.rawDataList.addAll(tmpData);
        LVRawDataListView.setItems(nerData.rawDataList);
    }
    @FXML
    private void loadDataByMysqlClicked(MouseEvent mouseEvent){
        // todo
    }
    @FXML
    private void replaceDataClicked(MouseEvent mouseEvent){
        nerData.handledDataList.clear();
        for(String v: nerData.rawDataList)
            nerData.handledDataList.add(v);
        LVHandledDataListView.setItems(nerData.handledDataList);
        synnerData();
    }
    @FXML
    private void appendDataClicked(MouseEvent mouseEvent){
        for(String v: nerData.rawDataList)
            nerData.handledDataList.add(v);
        LVHandledDataListView.setItems(nerData.handledDataList);
        synnerData();
    }

    @Override
    public void init(){
        // todo
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        LVRawDataListView.setItems(nerData.handledDataList);
        LVHandledDataListView.setItems(nerData.handledDataList);
        init();
    }

    public void synnerData(){
        nerData.participleItems.clear();
        // nerData.entityItems.clear();

        for (int i=0 ; i<nerData.handledDataList.size() ; i++){
            // 添加分词数据对象
            SingleDocParticiple item = new SingleDocParticiple();
            item.setId(i);
            item.setText(nerData.handledDataList.get(i));
            nerData.participleItems.add(item);

            // 添加实体数据对象
            SingleDocEntity entity = new SingleDocEntity();
            entity.setId(i);
            entity.setText(nerData.handledDataList.get(i));
            nerData.entityItems.add(entity);
        }
    }

    /**
     * get function
     */
    public ListView<String> getLVRawDataListView() {
        return LVRawDataListView;
    }

    public ListView<String> getLVHandledDataListView() {
        return LVHandledDataListView;
    }
}
