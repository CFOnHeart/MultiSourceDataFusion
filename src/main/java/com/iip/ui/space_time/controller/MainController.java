package com.iip.ui.space_time.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author Junnor.G
 * @Date 2018/11/28 下午7:24
 */
public class MainController implements Initializable {
    @FXML
    private TextArea textArea_1;

    @FXML
    private Button button_1;

    @FXML
    protected  void buttonClick(){
        textArea_1.appendText("Hello world!\r\n");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //todo
    }
}
