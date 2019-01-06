package com.iip.data.space_time;

import java.util.Date;

/**
 * @Author Junnor.G
 * @Date 2018/12/19 下午2:36
 */
public class Orientation implements Comparable<Orientation>{
    public Orientation(){}

    public Orientation(String place, Date date){ this.date=date; this.place=place; }

    public Orientation(String place, Date date, double lng, double lat){
        this.date=date; this.place=place;
        this.lng = lng; this.lat = lat;
    }

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

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    private String place;
    private Date date;
    private double lng;
    private double lat;

    @Override
    public int compareTo(Orientation obj){
        return date.compareTo(obj.date);
    }

}
