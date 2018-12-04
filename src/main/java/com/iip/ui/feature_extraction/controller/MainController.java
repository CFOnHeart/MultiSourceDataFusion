package com.iip.ui.feature_extraction.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by YLX on 2018/12/3
 */

public class MainController {

    @FXML
    private BorderPane feMainViewPane;

    @FXML
    private Label feMainTitle;

    @FXML
    private AnchorPane feStep1;

    @FXML
    private AnchorPane feStep2;

    @FXML
    private AnchorPane feStep3;

    @FXML
    private AnchorPane feStep4;

    /**
     * 标记当前所选择的菜单栏中的按钮
     */
    private AnchorPane currentMenuButton;
    /**
     * 缓存连接配置界面
     */
    private AnchorPane connectionConfigPane;

    private VBox generatePane;

    private VBox searchPane;

    @FXML
    private void menuButtonClicked(MouseEvent mouseEvent) {
        mouseEvent.consume();
        AnchorPane selectedMenuButton = (AnchorPane) mouseEvent.getTarget();

        if (currentMenuButton == selectedMenuButton){
            return;
        }

        if (currentMenuButton != null){
            currentMenuButton.getStyleClass().clear();
            currentMenuButton.getStyleClass().add("menuButton");
        }

        if (selectedMenuButton == feStep1){
            feStep1.getStyleClass().clear();
            feStep1.getStyleClass().add("menuButtonSelected");
            feMainTitle.setText("FE-STEP1");
            if (connectionConfigPane == null){
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainController.class.getResource("../view/FEStep1View.fxml"));
                    connectionConfigPane = loader.load();
                    connectionConfigPane.setPrefSize(feMainViewPane.getWidth()-60, feMainViewPane.getHeight()-60);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            feMainViewPane.setCenter(connectionConfigPane);
            currentMenuButton = selectedMenuButton;
        }

        if (selectedMenuButton == feStep2){
            feStep2.getStyleClass().clear();
            feStep2.getStyleClass().add("menuButtonSelected");
            feMainTitle.setText("FE-STEP2");

//            try {
//                FXMLLoader loader = new FXMLLoader();
//
//                loader.setLocation(MainController.class.getResource("../view/FEStep2View.fxml"));
//                searchPane = (VBox) loader.load();
//
//                searchPane.setPrefSize(feMainViewPane.getWidth()-60, feMainViewPane.getHeight()-60);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            feMainViewPane.setCenter(searchPane);
            currentMenuButton = selectedMenuButton;
        }

        if (selectedMenuButton == feStep3){
            feStep3.getStyleClass().clear();
            feStep3.getStyleClass().add("menuButtonSelected");
            feMainTitle.setText("FE-STEP3");

//            if (generatePane == null){
//            try {
//                FXMLLoader loader = new FXMLLoader();
//
//                loader.setLocation(MainController.class.getResource("../view/FEStep3View.fxml"));
//                generatePane = loader.load();
//                GenerateDatabaseController controller = loader.getController();
//                controller.initTextArea();
//                generatePane.setPrefSize(feMainViewPane.getWidth()-60, feMainViewPane.getHeight()-60);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
////            }
//
//            feMainViewPane.setCenter(generatePane);
            currentMenuButton = selectedMenuButton;
        }

        if (selectedMenuButton == feStep4){
            feStep4.getStyleClass().clear();
            feStep4.getStyleClass().add("menuButtonSelected");
            feMainTitle.setText("FE-STEP4");

//            feMainViewPane.setCenter(null);
            currentMenuButton = selectedMenuButton;
        }
    }

    @FXML
    private void exit(MouseEvent mouseEvent){
        mouseEvent.consume();
        Platform.exit();
    }

}
