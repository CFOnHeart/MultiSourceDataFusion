package com.iip.ui.space_time;

/**
 * @Author Junnor.G
 * @Date 2018/12/5 下午7:41
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SpaceTimeMain extends Application {

    private double xOffset;
    private double yOffset;

    @Override
    public void init() throws Exception {
        // 初始化参数配置
        super.init();
        xOffset = 0.0;
        yOffset = 0.0;


    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        // 主程序开始
        try {
            Parent root = FXMLLoader.load(getClass().getResource("view/SpaceTimeMainView.fxml"));
            // 设定stage可以通过鼠标拖动到屏幕的其他地方
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    event.consume();
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    event.consume();
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                }
            });
            Scene scene = new Scene(root);
//            scene.getStylesheets().add(getClass().getResource("view/stylesheet/ComboBox.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);

//            primaryStage.setX(Screen.getPrimary().getVisualBounds().getMinX());
//            primaryStage.setY(Screen.getPrimary().getVisualBounds().getMinY());
//            primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
//            primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());

            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

