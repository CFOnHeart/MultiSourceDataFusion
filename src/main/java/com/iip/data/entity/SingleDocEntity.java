package com.iip.data.entity;

import com.iip.textprocess.participle.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Junnor.G
 * @Date 2018/12/25 下午4:13
 */
public class SingleDocEntity {
    private int id;
    private String text;
    private String entityResult = "";
    private String date = "";
    private List<Entity> entities = new ArrayList<>();

    public static List<Entity> entityExtract(String text){
        List<Entity> list = new ArrayList<>();
        List<Entity> nameList = NameEntity.entityRecognition(text);
        list.addAll(nameList);
        List<Entity> placeEntity = PlaceEntity.entityRecognition(text);
        list.addAll(placeEntity);
        List<Entity> orgnizationEntity = OrganizationEntity.entityRecognition(text);
        list.addAll(orgnizationEntity);
        return list;
    }

    public void entityExtract(){
        entities = entityExtract(text);
        entityResult = toString();
    }

    public String toString(){
        if (entities.size() == 0) return "找不到相关实体信息";
        String res = "";
        for(Entity entity: entities){
            res += entity.word + "(" + entity.getType() + ");";
        }
        return res;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEntityResult() {
        return entityResult;
    }

    public void setEntityResult(String entityResult) {
        this.entityResult = entityResult;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
