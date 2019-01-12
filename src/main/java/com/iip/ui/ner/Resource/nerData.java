package com.iip.ui.ner.Resource;

import com.iip.ui.ner.Resource.SingleDocParticiple;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class nerData {
    public static ObservableList<String> rawDataList = FXCollections.observableArrayList();
    public static ObservableList<String> handledDataList = FXCollections.observableArrayList();
    public static ObservableList<SingleDocParticiple> participleItems = FXCollections.observableArrayList();
    public static ObservableList<SingleDocEntity> entityItems = FXCollections.observableArrayList();
}
