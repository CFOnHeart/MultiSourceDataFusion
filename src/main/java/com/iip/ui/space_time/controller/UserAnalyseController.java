package com.iip.ui.space_time.controller;

import com.iip.data.space_time.SinglePeopleAction;
import com.iip.data.space_time.SpaceTimeData;
import com.iip.util.PeopleActionAnalyse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
    private PieChart PCUserAction;
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
        // 绘制Chart
        int userActionCount [] = {0, 0, 0, 0};
        for (SinglePeopleAction action: SpaceTimeData.peopleActions){
            switch (action.getUserLevelPredict()){
                case RICH:
                    userActionCount[0]++;
                    break;
                case FAIRY_WELL_OF:
                    userActionCount[1]++;
                    break;
                case SUBSISTENCE:
                    userActionCount[2]++;
                    break;
                case POVERTY:
                    userActionCount[3]++;
                default:
                    break;

            }
        }
        System.out.println("here: "+userActionCount[0]+" "+userActionCount[1]+" "+userActionCount[2]+" "+userActionCount[3]);
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("RICH", userActionCount[0]),
                        new PieChart.Data("FAIRY_WELL_OF", userActionCount[1]),
                        new PieChart.Data("SUBSISTENCE", userActionCount[2]),
                        new PieChart.Data("POVERTY", userActionCount[3]));
        PCUserAction.setData(pieChartData);
        PCUserAction.setTitle("User Financial Situation");

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
