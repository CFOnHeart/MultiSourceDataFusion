package com.iip.data.space_time;

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

    public void sort(){
        Collections.sort(orientations);
    }

    private String id;
    private String name;
    private List<Orientation> orientations = new ArrayList<>();
}
