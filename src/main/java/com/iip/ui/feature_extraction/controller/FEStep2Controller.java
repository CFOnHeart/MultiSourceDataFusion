package com.iip.ui.feature_extraction.controller;

import com.iip.ui.feature_extraction.execute.Participle;
import com.iip.ui.feature_extraction.execute.textrank.TextRank;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class FEStep2Controller {

    @FXML
    private ListView<String> rawDataListView;
    private ObservableList<String> rawDataList = FXCollections.observableArrayList();

    @FXML
    private ListView<String> handledDataListView;
    private ObservableList<String> handledDataList = FXCollections.observableArrayList();

    public static List<String> docs = new ArrayList<String>();
    private List<String> partedDocs = new ArrayList<String>();

    @FXML
    private void loadDataClicked(MouseEvent event){
        if(docs.size()>0) return;
        rawDataList.addAll(getDocs());
        rawDataListView.setItems(rawDataList);
    }

    @FXML
    private void handleDataClicked(MouseEvent event){
        if(partedDocs.size()>0) return;
        handledDataList.addAll(getKeyedDocs(docs));
        handledDataListView.setItems(handledDataList);
    }

    @FXML
    private void exportDataClicked(MouseEvent event){
//        if(partedDocs.size()>0) return;
//        handledDataList.addAll(getPartedDocs(docs));
//        handledDataListView.setItems(handledDataList);
    }

    // 从数据源获取数据
    private List<String> getDocs() {
        String doc = "通过物资扶持，技术扶持，技能培训等帮扶措施，综合政策，实现脱贫";
        docs.add(doc); docs.add(doc); docs.add(doc);
        return docs;
    }

    //选取关键词操作
    private List<String> getKeyedDocs(List<String> docs){
        partedDocs = TextRank.topKDocs(docs, 5);
        return partedDocs;
    }

}
