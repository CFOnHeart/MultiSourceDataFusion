package com.iip.ui.space_time.controller;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.iip.data.entity.SingleDocEntity;
import com.iip.data.space_time.SpaceTimeData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Author Junnor.G
 * @Date 2018/12/5 下午8:18
 */
public class EntityViewController extends RootController implements Initializable {
    @FXML
    private TextField TFHintLoad;
    @FXML
    private Button BtnHintLoad;

    @FXML
    private TableView TVEntity;
    @FXML
    private TableColumn TCID;
    @FXML
    private TableColumn TCDate;
    @FXML
    private TableColumn TCRawData;
    @FXML
    private TableColumn TCEntityRes;

    @FXML
    private Button BtnImportEntity;
    @FXML
    public void entityAllClicked(){
        for(SingleDocEntity item: SpaceTimeData.entityItems){
            item.entityExtract();
        }
        refreshTableData();
    }

    @FXML
    public void entitySelectClicked(){
        EntityViewController controller =
                (EntityViewController)Context.controllers.get("EntityViewController");
        int selectRowIndex = TVEntity.getSelectionModel().getSelectedIndex();
        if (selectRowIndex < 0){
            // 未选中
            System.out.println("请先选中表格中的一行");
        }
        else{
            SpaceTimeData.entityItems.get(selectRowIndex).entityExtract();
            refreshTableData();
        }
    }

    @FXML
    public void entityImportClicked(){
        Alert information = new Alert(Alert.AlertType.INFORMATION,"提取出的实体已经导入，可在Space Time界面中查看。如果还未提取出实体，请重新点击对所有文本提取实体按钮后导入");
        information.setTitle("information"); //设置标题，不设置默认标题为本地语言的information
        information.setHeaderText("Information"); //设置头标题，默认标题为本地语言的information
        Button infor = new Button("show Information");
        SpaceTimeData.entityItemsToPeopleOrientations();
        information.showAndWait(); //显示弹窗，同时后续代码等挂起
    }

    @Override
    public void init(){
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
    }

    public void refreshTableData(){
        TVEntity.setItems(SpaceTimeData.entityItems);
        TVEntity.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        init();
        refreshTableData();
        TCID.setCellValueFactory( new PropertyValueFactory("id") );
        TCDate.setCellValueFactory(new PropertyValueFactory("dateStr"));
        TCRawData.setCellValueFactory( new PropertyValueFactory("text"));
        TCEntityRes.setCellValueFactory( new PropertyValueFactory("entityResult") );
    }

    public static void main(String [] args){
        String[] testCase = new String[]{
                "我十月三号十二点在上海林原科技有限公司兼职工作，",
                "同时在上海外国语大学日本文化经济学院学习经济与外语。",
                "我经常在台川喜宴餐厅吃饭，",
                "偶尔去地中海影城看电影。",
        };
        Segment segment = HanLP.newSegment();
        for (String sentence : testCase)
        {
            List<Term> termList = segment.seg(sentence);
            System.out.println(termList);
        }
    }
}
