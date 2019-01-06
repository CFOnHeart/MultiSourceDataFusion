package com.iip.ui.feature_extraction;

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

public class Main extends Application {

    private double xOffset;
    private double yOffset;

    @Override
    public void init() throws Exception {
        // 初始化参数配置
        super.init();
        xOffset = 0.0;
        yOffset = 0.0;

//        // 初始化连接池 - 这句话不要删掉
//        ConnectionPool.getInstance();
//        // 初始化数据服务
//        DataService.getInstance();
//
//        // test - add SourceSeverConnection
//        ConnectionConfigBean connectionConfigBean = new ConnectionConfigBean();
//        connectionConfigBean.setPort("3306");
//        connectionConfigBean.setUsername("root");
////        connectionConfigBean.setPassword("root");
//        connectionConfigBean.setNickName("education-system");
//        connectionConfigBean.setHostIP("127.0.0.1");
////        connectionConfigBean.setHostIP("114.212.83.171");
//        connectionConfigBean.setDriver(DriverType.MYSQL);
//        connectionConfigBean.setTargetDB(false);
//        connectionConfigBean.setName("education_system");
//        ConnectionPool.getInstance().getSourceConnections().put("education-system", new ServerConnection(connectionConfigBean));
//        for (String dbName: ConnectionPool.getInstance().getSourceConnections().keySet()){
//            System.out.println("DBNAME:" + dbName);
//            System.out.println("TABLES:");
//            for (String tableName: ConnectionPool.getInstance().getSourceConnections().get(dbName).getTables().keySet()
//                    ) {
//                System.out.print(tableName + " :");
//                for (String fieldName: ConnectionPool.getInstance().getSourceConnections().get(dbName).getTables().get(tableName).getFields().keySet()
//                        ) {
//                    System.out.print(" " + fieldName);
//                }
//                System.out.print("\n");
//            }
//        }
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        // 主程序开始
        try {
            Parent root = FXMLLoader.load(getClass().getResource("view/FeatureExtractionMainView.fxml"));
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
            scene.getStylesheets().add(getClass().getResource("view/stylesheet/ComboBox.css").toExternalForm());
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
