package com.iip.data.entity;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Junnor.G
 * @Date 2018/12/6 下午6:15
 */
public class Test {
    public static List<Entity> entities;

    public static void main(String [] args){
        entities = new ArrayList<>();
        entities.add(new NameEntity("周瑜"));

        entities.add(new TimeEntity("2019年"));

        entities.add(new PlaceEntity("天安门"));

        for(Entity entity: entities){
            System.out.println(entity.getType());
            if(entity.getType().equals("place")){
                ((PlaceEntity)entity).hello();
            }
        }
    }
}
