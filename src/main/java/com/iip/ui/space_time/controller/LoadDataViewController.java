package com.iip.ui.space_time.controller;

import com.iip.data.participle.SingleDocParticiple;
import com.iip.data.space_time.SpaceTimeData;
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
public class LoadDataViewController extends RootController implements Initializable{
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
        SpaceTimeData.rawDataList.clear();
        SpaceTimeData.rawDataList.addAll(tmpData);
        LVRawDataListView.setItems(SpaceTimeData.rawDataList);
    }
    @FXML
    private void loadDataByMysqlClicked(MouseEvent mouseEvent){
        // todo
    }
    @FXML
    private void replaceDataClicked(MouseEvent mouseEvent){
        SpaceTimeData.handledDataList.clear();
        for(String v: SpaceTimeData.rawDataList)
            SpaceTimeData.handledDataList.add(v);
        LVHandledDataListView.setItems(SpaceTimeData.handledDataList);
        synSpaceTimeData();
    }
    @FXML
    private void appendDataClicked(MouseEvent mouseEvent){
        for(String v: SpaceTimeData.rawDataList)
            SpaceTimeData.handledDataList.add(v);
        LVHandledDataListView.setItems(SpaceTimeData.handledDataList);
        synSpaceTimeData();
    }

    @Override
    public void init(){
        // todo
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        LVRawDataListView.setItems(SpaceTimeData.handledDataList);
        LVHandledDataListView.setItems(SpaceTimeData.handledDataList);
        init();
    }

    public void synSpaceTimeData(){
        SpaceTimeData.participleItems.clear();
        for (int i=0 ; i<SpaceTimeData.handledDataList.size() ; i++){
            SingleDocParticiple item = new SingleDocParticiple();
            item.setId(i);
            item.setText(SpaceTimeData.handledDataList.get(i));
            SpaceTimeData.participleItems.add(item);
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
