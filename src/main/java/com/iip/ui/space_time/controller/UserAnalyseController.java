package com.iip.ui.space_time.controller;

import com.iip.data.space_time.SpaceTimeData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author Junnor.G
 * @Date 2019/1/12 下午5:08
 */
public class UserAnalyseController extends RootController implements Initializable {
    @FXML
    private TableView TVUserActionShow;
    @FXML
    private TableColumn TCUserId;
    @FXML
    private TableColumn TCUserName;
    @FXML
    private TableColumn TCUserTravelTime;
    @FXML
    private TableColumn TCUserTravelDis;
    @FXML
    private TableColumn TCUserLevelPredict;

    @FXML
    public void backSpaceTimeClicked(){
        SpaceTimeMainViewController controller =
                (SpaceTimeMainViewController)Context.controllers.get("SpaceTimeMainViewController");
        controller.presentSpaceTimeView();
    }

    public void refreshTable(){
        TVUserActionShow.setItems(SpaceTimeData.peopleActions);
        TVUserActionShow.refresh();
    }
    @Override
    public void init(){
        // todo
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        refreshTable();
        init();
        TCUserId.setCellValueFactory( new PropertyValueFactory("userId") );
        TCUserName.setCellValueFactory(new PropertyValueFactory("userName"));
        TCUserTravelTime.setCellValueFactory( new PropertyValueFactory("userTravelTime"));
        TCUserTravelDis.setCellValueFactory( new PropertyValueFactory("userTravelDis") );
        TCUserLevelPredict.setCellValueFactory( new PropertyValueFactory("userLevelPredict"));
    }
}
