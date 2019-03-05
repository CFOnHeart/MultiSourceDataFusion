package com.iip.data.space_time;

import com.iip.util.PeopleActionAnalyse;

/**
 * @Author Junnor.G
 * @Date 2019/1/12 下午5:32
 */
public class SinglePeopleAction {
    public SinglePeopleAction(){

    }

    public SinglePeopleAction(int userId,
                              String userName,
                              double userTravelTime,
                              double userTravelDis,
                              PeopleActionAnalyse.FinalncialSituationLevel userLevelPredict){
        this.userId = userId;
        this.userName = userName;
        this.userTravelTime = userTravelTime;
        this.userTravelDis = userTravelDis;
        this.userLevelPredict = userLevelPredict;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getUserTravelTime() {
        return userTravelTime;
    }

    public void setUserTravelTime(double userTravelTime) {
        this.userTravelTime = userTravelTime;
    }

    public double getUserTravelDis() {
        return userTravelDis;
    }

    public void setUserTravelDis(double userTravelDis) {
        this.userTravelDis = userTravelDis;
    }

    public PeopleActionAnalyse.FinalncialSituationLevel getUserLevelPredict() {
        return userLevelPredict;
    }

    public void setUserLevelPredict(PeopleActionAnalyse.FinalncialSituationLevel userLevelPredict) {
        this.userLevelPredict = userLevelPredict;
    }

    private int userId;
    private String userName;
    private double userTravelTime;
    private double userTravelDis;
    private PeopleActionAnalyse.FinalncialSituationLevel userLevelPredict;
}
