package com.iip.ui.space_time.controller;

import com.iip.data.participle.SingleDocParticiple;
import com.iip.data.space_time.SpaceTimeData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Author Junnor.G
 * @Date 2018/12/18 下午1:26
 */
public class ParticipleTabViewController extends RootController implements Initializable{

    @FXML
    private TableView TVParticiple;
    @FXML
    private TableColumn TCID;
    @FXML
    private TableColumn TCRawData;
    @FXML
    private TableColumn TCParticipleRes;


    public void loadTableData(){
        List<String> datasets = SpaceTimeData.handledDataList;
        System.out.println("debug load Table Data -> size: " + datasets.size());
        ObservableList<SingleDocParticiple> items = FXCollections.observableArrayList();
        for (int i=0 ; i<datasets.size() ; i++){
            SingleDocParticiple item = new SingleDocParticiple();
            item.setId(i);
            item.setText(datasets.get(i));
            items.add(item);
        }
        TVParticiple.setItems(items);
    }

    @Override
    public void init(){
        // todo
        loadTableData();
        TCID.setCellValueFactory( new PropertyValueFactory("id") );
        TCRawData.setCellValueFactory( new PropertyValueFactory("text"));
        TCParticipleRes.setCellValueFactory( new PropertyValueFactory("participleResult") );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        init();
    }
}
