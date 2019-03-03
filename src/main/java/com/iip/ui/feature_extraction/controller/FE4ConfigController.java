package com.iip.ui.feature_extraction.controller;

import com.iip.ui.feature_extraction.Main;
import com.iip.ui.feature_extraction.execute.Doc2vec;
import com.iip.ui.feature_extraction.execute.Participle;
import com.iip.ui.feature_extraction.execute.connection.DatabaseOperations;
import com.iip.ui.feature_extraction.execute.textrank.TextRank;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FE4ConfigController {

    @FXML
    private TextField databaseName, inputTableStep1, outputTableStep1, inputTableStep2, outputTableStep2,
            inputTableStep3, outputTableStep3, tfK, tfPath;
    @FXML
    private Label labelNote, labelK, labelPath;
    @FXML
    private Button btnConfirm;
    private String dbname, it1, ot1, it2, ot2, it3, ot3, path, k= "";

    private boolean reload_database = true;
    private boolean isTableSet = false;
//    public static List<String> docs = new ArrayList<String>();
//    private List<String> partedDocs = new ArrayList<String>();

    public void initialize(){
        labelNote.setText("Note: 请先确认源数据表，导出数据表和其它参数再导入数据库!");
        btnConfirm.setText("确认表格和参数");
        labelK.setText("关键词个数:");
        labelPath.setText("词典的路径:");
        tfPath.setPromptText("词向量字典路径");
    }

    @FXML
    private void loadDataClicked(MouseEvent event){
        dbname = databaseName.getText(); // "kjb_fe" databaseName.getText()
        if(dbname.equals("")){
            DatabaseOperations.print("数据库名为空");
            Main.f_alert_informationDialog("操作错误", "数据库名存为空!");
            return;
        }
        if(!isTableSet){
            DatabaseOperations.print("数据表未确认");
            Main.f_alert_informationDialog("操作错误", "数据表和参数未确认!");
            return;
        }
        if(reload_database){
            DatabaseOperations.DBNAME = dbname;
            DatabaseOperations.DB_URL = DatabaseOperations.DB_URL_prefix + dbname;
            if(!DatabaseOperations.connectDB()) {
                DatabaseOperations.print("数据库加载失败!");
                Main.f_alert_informationDialog("连接失败!", "请检查数据库名和表名!");
                return;
            }
            DatabaseOperations.isReady = true;
            reload_database = false;
            DatabaseOperations.print("数据库加载完成!");
            Main.f_alert_informationDialog("操作成功", "数据库加载完成!");
        }
    }

    @FXML
    private void confirm(MouseEvent event){//确认数据表
        isTableSet=false;
        it1 = inputTableStep1.getText().trim();
        it2 = inputTableStep2.getText().trim();
        it3 = inputTableStep3.getText().trim();
        ot1 = outputTableStep1.getText().trim();
        ot2 = outputTableStep2.getText().trim();
        ot3 = outputTableStep3.getText().trim();
        k = tfK.getText().trim();
        path = tfPath.getText().trim();
        if(it1.equals("") || it2.equals("") || it3.equals("") || ot1.equals("") || ot2.equals("") ||
                ot3.equals("") || k.equals("") || path.equals("")){
            DatabaseOperations.print("数据表名存在空的");
            Main.f_alert_informationDialog("操作错误", "数据表名或参数存在空的!");
            return;
        }
        if(Integer.valueOf(k)<=0){
            DatabaseOperations.print("关键词参数个数不能小于0");
            Main.f_alert_informationDialog("操作错误", "关键词参数个数不能小于0!");
            return;
        }
        if(!new File(path).exists()){
            DatabaseOperations.print("词典文件不存在");
            Main.f_alert_informationDialog("操作错误", "词典文件不存在!");
            return;
        }
        DatabaseOperations.originalDataTables[0] = it1;
        DatabaseOperations.originalDataTables[1] = it2;
        DatabaseOperations.originalDataTables[2] = it3;
        DatabaseOperations.outputTableNames[0] = ot1;
        DatabaseOperations.outputTableNames[1] = ot2;
        DatabaseOperations.outputTableNames[2] = ot3; //todo: undo
        DatabaseOperations.k = k;
        DatabaseOperations.path = path;
        isTableSet=true;
        DatabaseOperations.print("表格设置完成!");
        Main.f_alert_informationDialog("操作成功", "表格和参数设置完成!");
    }

    @FXML
    private void resetDababase(MouseEvent event){//重置数据库
        if(reload_database) return;
        reload_database = true;
        DatabaseOperations.disconnect();
        inputTableStep1.setText("");
        inputTableStep2.setText("");
        inputTableStep3.setText("");
        outputTableStep1.setText("");
        outputTableStep2.setText("");
        outputTableStep3.setText("");
        databaseName.setText("");
        dbname= it1= ot1= it2= ot2= it3= ot3 = "";
        isTableSet = false;
    }

    @FXML
    private void exit(MouseEvent event){
        DatabaseOperations.disconnect();
        System.exit(0);
    }

}
