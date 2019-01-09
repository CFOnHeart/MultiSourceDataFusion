package com.iip.ui.feature_extraction.controller;

import com.iip.ui.feature_extraction.Main;
import com.iip.ui.feature_extraction.execute.Participle;
import com.iip.ui.feature_extraction.execute.connection.DatabaseOperations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class FE1ParticipleController {

    @FXML
    private ListView<String> rawDataListView;
    private ObservableList<String> rawDataList = FXCollections.observableArrayList();

    @FXML
    private ListView<String> handledDataListView;
    private ObservableList<String> handledDataList = FXCollections.observableArrayList();

    @FXML
    private Label dbname, dbname_, dataOrigin, dataOutput;

    private List<String> docs = new ArrayList<String>();
    private List<String> partedDocs = new ArrayList<String>();

    public void initialize(){
        dbname.setText("数据库: database");
        dbname_.setText("数据库: database");
        dataOrigin.setText("源数据表格: table");
        dataOutput.setText("导出数据表格: table");
    }

    @FXML
    private void loadDataClicked(MouseEvent event){
        if(docs.size()>0) return;
        rawDataList.addAll(getDocs());
        rawDataListView.setItems(rawDataList);
    }

    @FXML
    private void handleDataClicked(MouseEvent event){
        if(partedDocs.size()>0) return;
        handledDataList.addAll(getPartedDocs(docs));
        handledDataListView.setItems(handledDataList);
    }

    @FXML
    private void exportDataClicked(MouseEvent event){
        if(!DatabaseOperations.isReady){
            DatabaseOperations.print("数据库还没链接好");
            Main.f_alert_informationDialog("操作失败", "数据库还没链接好!");
            return;
        }
        DatabaseOperations.write(DatabaseOperations.outputTableNames[0], DatabaseOperations.dataColumn, partedDocs);
        DatabaseOperations.print("导出表完成!");
        Main.f_alert_informationDialog("操作成功", "导出表完成!");
    }

    // 从数据源获取数据
    private List<String> getDocs() {
//        String doc = "通过物资扶持，技术扶持，技能培训等帮扶措施，综合政策，实现脱贫";
//        docs.add(doc); docs.add(doc); docs.add(doc);
        if(!DatabaseOperations.isReady){
            DatabaseOperations.print("数据库还没链接好");
            Main.f_alert_informationDialog("操作失败", "数据库还没链接好!");
            return docs;
        }
        docs = DatabaseOperations.read(DatabaseOperations.originalDataTables[0], DatabaseOperations.dataColumn);
        dbname.setText("数据库: "+DatabaseOperations.DBNAME);
        dbname_.setText("数据库: "+DatabaseOperations.DBNAME);
        dataOrigin.setText("源数据表格: "+DatabaseOperations.originalDataTables[0]);
        dataOutput.setText("导出数据表格: "+DatabaseOperations.outputTableNames[0]);
        DatabaseOperations.print("导入表完成!");
        Main.f_alert_informationDialog("操作成功", "导入表完成!");
        return docs;
    }

    //分词操作
    private List<String> getPartedDocs(List<String> docs){
        partedDocs = Participle.getPartedDocs(docs);
        return partedDocs;
    }

}
