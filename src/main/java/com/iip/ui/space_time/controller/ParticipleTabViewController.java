package com.iip.ui.space_time.controller;

import com.iip.data.participle.SingleDocParticiple;
import com.iip.data.space_time.SpaceTimeData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Author Junnor.G
 * @Date 2018/12/18 下午1:26
 */
public class ParticipleTabViewController implements Initializable{

    @FXML
    private TableView TVParticiple;
    @FXML
    private TableColumn TCID;
    @FXML
    private TableColumn TCDate;
    @FXML
    private TableColumn TCRawData;
    @FXML
    private TableColumn TCParticipleRes;

    @FXML
    public void participleAllClicked(){
        ParticipleViewController controller =
                (ParticipleViewController)Context.controllers.get("ParticipleViewController");
        int cnt = controller.getTPParticiple().getSelectionModel().getSelectedIndex();
        // 根据不同的tab选择不同的分词方法
        switch (cnt){
            case 0:
                for(SingleDocParticiple item: SpaceTimeData.participleItems){
                    item.participleHanlp();
                }
                break;
            case 1:
//                        for(SingleDocParticiple item: SpaceTimeData.participleItems){
//                            item.participleHanlp();
//                        }
                break;
            default:
                break;
        }
        refreshTableData();
    }

    @FXML
    public void participleSelectClicked(){
        ParticipleViewController controller =
                (ParticipleViewController)Context.controllers.get("ParticipleViewController");
        int selectRowIndex = TVParticiple.getSelectionModel().getSelectedIndex();
        if (selectRowIndex < 0){
            // 未选中
            System.out.println("请先选中表格中的一行");
        }
        else{
            SpaceTimeData.participleItems.get(selectRowIndex).participleHanlp();
            refreshTableData();
        }
    }

    public void refreshTableData(){
        TVParticiple.setItems(SpaceTimeData.participleItems);
        TVParticiple.refresh();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources){
        refreshTableData();
        TCID.setCellValueFactory( new PropertyValueFactory("id") );
        TCDate.setCellValueFactory(new PropertyValueFactory("dateStr"));
        TCRawData.setCellValueFactory( new PropertyValueFactory("text"));
        TCParticipleRes.setCellValueFactory( new PropertyValueFactory("participleResult") );
    }
}
