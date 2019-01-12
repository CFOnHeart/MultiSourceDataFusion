package com.iip.ui.ner.controller;

import com.iip.ui.ner.Resource.nerData;
import com.iip.ui.ner.Resource.SingleDocParticiple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class PosTabViewController implements Initializable{

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
    public void posAllClicked(){
        PosViewController controller =
                (PosViewController) com.iip.ui.ner.controller.Context.controllers.get("PosViewController");
        int cnt = controller.getTPPos().getSelectionModel().getSelectedIndex();
        // 根据不同的tab选择不同的分词方法
        switch (cnt){
            case 0:
                for(SingleDocParticiple item: nerData.participleItems){
                    item.posHanlp();
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
    public void posSelectClicked(){
        PosViewController controller =
                (PosViewController) com.iip.ui.ner.controller.Context.controllers.get("PosViewController");
        int selectRowIndex = TVParticiple.getSelectionModel().getSelectedIndex();
        if (selectRowIndex < 0){
            // 未选中
            System.out.println("请先选中表格中的一行");
        }
        else{
            nerData.participleItems.get(selectRowIndex).posHanlp();
            refreshTableData();
        }
    }

    public void refreshTableData(){
        TVParticiple.setItems(nerData.participleItems);
        TVParticiple.refresh();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources){
        refreshTableData();
        TCID.setCellValueFactory( new PropertyValueFactory("id") );
//        TCDate.setCellValueFactory(new PropertyValueFactory("dateStr"));
        TCRawData.setCellValueFactory( new PropertyValueFactory("text"));
        TCParticipleRes.setCellValueFactory( new PropertyValueFactory("posResult") );
    }
}
