package com.iip.data.space_time;

import java.util.Date;

/**
 * @Author Junnor.G
 * @Date 2018/12/19 下午2:36
 */
public class Orientation{
    public Orientation(){}

    public Orientation(String place, Date date){ this.date=date; this.place=place; }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private String place;
    private Date date;

}
