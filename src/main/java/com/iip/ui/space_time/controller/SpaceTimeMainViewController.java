package com.iip.ui.space_time.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;

/**
 * @Author Junnor.G
 * @Date 2018/12/5 下午6:23
 */
class MenuModule{
    private AnchorPane pane;
    private String fxmlPath;
    private String text;
    private String controllerClassName;

    MenuModule(AnchorPane pane, String fxmlPath, String text, String controllerClassName){
        this.pane = pane;
        this.fxmlPath = fxmlPath;
        this.text = text;
        this.controllerClassName = controllerClassName;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public String getFxmlPath(){
        return fxmlPath;
    }

    public String getText(){
        return text;
    }

    public String getControllerClassName(){ return controllerClassName; }
}

public class SpaceTimeMainViewController extends RootController implements Initializable{
    @FXML
    private Label LblMainTitle;
    @FXML
    private AnchorPane APLoadData;
    @FXML
    private AnchorPane APParticiple;
    @FXML
    private AnchorPane APEntity;
    @FXML
    private AnchorPane APSpaceTime;
    @FXML
    private AnchorPane APSetting;
    @FXML
    private BorderPane BPMainViewPane;

    /**
     * 标记当前所选择的菜单栏中的AnchorPane
     */
    private AnchorPane currentMenuPane;
    /**
     * 缓存连接配置界面
     */
    private AnchorPane connectionConfigPane;
    /**
     * 用来保存所有menu的anchorPane模块的设置信息,避免重复代码,
     * 每次增加新的menu,只需要在其中初始化函数initialize()将对应信息添加进去就ok了
     */
    private List<MenuModule> menuModules;

    @FXML
    public void exit(MouseEvent mouseEvent){
        mouseEvent.consume();
        Platform.exit();
    }
    @FXML
    private void menuButtonClicked(MouseEvent mouseEvent){
        mouseEvent.consume();
        AnchorPane selectedMenuPane = (AnchorPane) mouseEvent.getTarget();

        changeMenuView(selectedMenuPane);

    }

    public void changeMenuView(AnchorPane selectedMenuPane){
        if (currentMenuPane == selectedMenuPane) return;

        if (currentMenuPane != null){
            currentMenuPane.getStylesheets().clear();
            currentMenuPane.getStylesheets().add("menuButton");
        }

        // 去判断鼠标是否点击在对应的Pane上
        for (MenuModule menuModule: menuModules){
            if (menuModule.getPane() == selectedMenuPane){
                menuModule.getPane().getStylesheets().clear();
                menuModule.getPane().getStylesheets().add("menuButtonSelected");
                LblMainTitle.setText(menuModule.getText());
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(SpaceTimeMainViewController.class.getResource(menuModule.getFxmlPath()));
                    connectionConfigPane = loader.load();
                    connectionConfigPane.setPrefSize(BPMainViewPane.getWidth()-60, BPMainViewPane.getHeight()-60);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BPMainViewPane.setCenter(connectionConfigPane);
                currentMenuPane = selectedMenuPane;
//                if( Context.controllers.containsKey(menuModule.getControllerClassName()) == true) {
//                    System.out.println("In Main View");
//                    ((RootController) Context.controllers.get(menuModule.getControllerClassName())).init();
//                }
                break;
            }
        }
    }

    public void presentLoginView(){
        AnchorPane selectedMenuPane = APLoadData;
        changeMenuView(selectedMenuPane);
    }

    public void presentSpaceTimeView(){
        AnchorPane selectedMenuPane = APSpaceTime;
        changeMenuView(selectedMenuPane);
    }

    public void presentPeopleActionView(){
        currentMenuPane = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SpaceTimeMainViewController.class.getResource("../view/UserAnalyse.fxml"));
            connectionConfigPane = loader.load();
            connectionConfigPane.setPrefSize(BPMainViewPane.getWidth()-60, BPMainViewPane.getHeight()-60);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BPMainViewPane.setCenter(connectionConfigPane);
    }
    @Override
    public void init(){
        // todo
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        init();
        System.out.println("In SpaceTimeMainViewController initialize");
        menuModules = new ArrayList<MenuModule>();
        // 加载数据界面
        menuModules.add(new MenuModule(APLoadData, "../view/LoadDataView.fxml", "Load Data", "LoadDataViewController"));
        // 分词界面
        menuModules.add(new MenuModule(APParticiple, "../view/ParticipleView.fxml", "Participle", "ParticipleViewController"));
        // 实体界面
        menuModules.add(new MenuModule(APEntity, "../view/EntityView.fxml", "Entity", "EntityViewController"));
        // 时空性界面
        menuModules.add(new MenuModule(APSpaceTime, "../view/SpaceTimeView.fxml", "Space Time", "SpaceTimeViewController"));
        // 设置界面
        menuModules.add(new MenuModule(APSetting, "../view/SettingView.fxml", "Setting", "SettingViewController"));
    }

}
