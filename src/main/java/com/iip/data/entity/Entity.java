package com.iip.data.entity;

/**
 * @Author Junnor.G
 * @Date 2018/12/6 下午6:05
 */
import java.util.HashMap;
import java.util.Map;

public abstract class Entity {
    protected String type;
    protected String word;
    protected final static Map<String, String> CLASS_TYPE = new HashMap<String, String>() {
        {
            put("PlaceEntity", "place");
            put("TimeEntity", "time");
        }
    };

    public Entity(String word){
        this.type = CLASS_TYPE.get( this.getClass().getSimpleName() );
        this.word = word;
    }
    abstract String getType();
}
