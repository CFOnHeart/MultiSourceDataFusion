package com.iip.data.space_time;

import com.iip.util.DistanceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author Junnor.G
 * @Date 2018/12/19 下午2:28
 * 对于一个人经历的所有地方以及对应时间的对象保存
 */
public class PeopleOrientation {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Orientation> getOrientations() {
        return orientations;
    }

    public void setOrientations(List<Orientation> orientations) {
        this.orientations = orientations;
        sort();
    }

    public double allDistance(){
        sort();
        double dis = 0.0;
        for(int i=0 ; i<orientations.size()-1 ; i++){
            System.out.println(orientations.get(i).getLng()+" "+
                    orientations.get(i).getLat()+ " "+
                    orientations.get(i+1).getLng()+" "+
                    orientations.get(i+1).getLat());
            dis += DistanceUtil.calculateDistance(
                    orientations.get(i).getLng(),
                    orientations.get(i).getLat(),
                    orientations.get(i+1).getLng(),
                    orientations.get(i+1).getLat()
                    );
        }
        return dis;
    }

    public double throughDays(){
        sort();
        if (orientations.size() == 0) return 0.01;
        long through_milliseconds = orientations.get(orientations.size()-1).getDate().getTime()
                        - orientations.get(0).getDate().getTime();
        return through_milliseconds / 1000.0 / 3600 / 24;
    }

    public void sort(){
        Collections.sort(orientations);
    }

    private String id;
    private String name;
    private List<Orientation> orientations = new ArrayList<>();
}
