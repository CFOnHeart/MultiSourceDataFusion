package com.iip.data.entity;

/**
 * @Author Junnor.G
 * @Date 2018/12/6 下午6:12
 * 地点实体类
 */
public class PlaceEntity extends Entity{

    public PlaceEntity(String word){
        super(word);
    }
    @Override
    public String getType(){
        return this.type;
    }

    public void hello(){
        System.out.println("Place Entity " + word +" Hello!");
    }
}
