package com.iip.ui.ner.Resource;

import com.iip.data.entity.Entity;
import java.util.List;
import com.iip.data.entity.NameEntity;
import com.iip.data.entity.OrganizationEntity;
import com.iip.data.entity.PlaceEntity;
import java.util.ArrayList;


/**
 * @Author Junnor.G
 * @Date 2018/12/25 下午4:13
 */
public class SingleDocEntity {
    private int id;
    private String text;
    private String personEntityResult = "";
    private String locationEntityResult = "";
    private String organizationEntityResult = "";
//    private String dateStr = "";
//    private Date date;
    private List<Entity> personEntities = new ArrayList<>();
    private List<Entity> locationEntities = new ArrayList<>();
    private List<Entity> organizationEntities = new ArrayList<>();


    public static List<Entity> peosonEntityExtract(String text){
        List<Entity> list = new ArrayList<>();
        List<Entity> nameList = NameEntity.entityRecognition(text);
        list.addAll(nameList);
//        List<Entity> placeEntity = PlaceEntity.entityRecognition(text);
//        list.addAll(placeEntity);
//        List<Entity> orgnizationEntity = OrganizationEntity.entityRecognition(text);
//        list.addAll(orgnizationEntity);
        return list;
    }

    public void personEntityExtract(){
        personEntities = peosonEntityExtract(text);
        personEntityResult = personEntitytoString();
    }

    public String personEntitytoString(){
        if (personEntities.size() == 0) return "找不到人名实体信息";
        String res = "";
        for(Entity entity: personEntities){
            res += entity.word +";";
        }
        return res;
    }






    public static List<Entity> locationEntityExtract(String text){
        List<Entity> list = new ArrayList<>();
//        List<Entity> nameList = NameEntity.entityRecognition(text);
//        list.addAll(nameList);
        List<Entity> placeEntity = PlaceEntity.entityRecognition(text);
        list.addAll(placeEntity);
//        List<Entity> orgnizationEntity = OrganizationEntity.entityRecognition(text);
//        list.addAll(orgnizationEntity);
        return list;
    }

    public void locationEntityExtract(){
        locationEntities = locationEntityExtract(text);
        locationEntityResult = locationEntitytoString();
    }

    public String locationEntitytoString(){
        if (locationEntities.size() == 0) return "找不到地名实体信息";
        String res = "";
        for(Entity entity: locationEntities){
            res += entity.word +";";
        }
        return res;
    }


    public static List<Entity> organizationEntityExtract(String text){
        List<Entity> list = new ArrayList<>();
//        List<Entity> nameList = NameEntity.entityRecognition(text);
//        list.addAll(nameList);
//        List<Entity> placeEntity = PlaceEntity.entityRecognition(text);
//        list.addAll(placeEntity);
        List<Entity> orgnizationEntity = OrganizationEntity.entityRecognition(text);
        list.addAll(orgnizationEntity);
        return list;
    }

    public void organizationEntityExtract(){
        organizationEntities = organizationEntityExtract(text);
        organizationEntityResult = organizationEntitytoString();
    }

    public String organizationEntitytoString(){
        if (organizationEntities.size() == 0) return "找不到机构名实体信息";
        String res = "";
        for(Entity entity: organizationEntities){
            res += entity.word +";";
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
//
        this.text=text;
    }

//    public String getDateStr() {
//        return dateStr;
//    }
//
//    public void setDateStr(String dateStr) {
//        this.dateStr = dateStr;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    public String getPersonEntityResult() {
        return personEntityResult;
    }

    public void setPersonEntityResult(String personEntityResult) {
        this.personEntityResult = personEntityResult;
    }

    public List<Entity> getPersonEntities() {
        return personEntities;
    }

    public void setpersonEntities(List<Entity> personEntities) {
        this.personEntities= personEntities;
    }






    public String getLocationEntityResult() {
        return locationEntityResult;
    }

    public void setLocationEntityResult(String locationEntityResult) {
        this.locationEntityResult = locationEntityResult;
    }

    public List<Entity> getLocationEntities() {
        return locationEntities;
    }

    public void setLocationEntities(List<Entity> locationEntities) {
        this.personEntities= locationEntities;
    }




    public String getOrganizationEntityResult() {
        return organizationEntityResult;
    }

    public void setOrganizationEntityResult(String organizationEntityResult) {
        this.organizationEntityResult = organizationEntityResult;
    }

    public List<Entity> getOrganizationEntities() {
        return organizationEntities;
    }

    public void setOrganizationEntities(List<Entity> organizationEntities) {
        this.organizationEntities= organizationEntities;
    }






    public static void main(String [] args){
        com.iip.ui.ner.Resource.SingleDocEntity item = new com.iip.ui.ner.Resource.SingleDocEntity();
        item.setId(1);
        item.setText("济南杨铭宇餐饮管理有限公司是由杨先生创办的餐饮企业，晚上九点去吃饭，2008年5月3日北京今天很热");
        System.out.println(item.text);
        item.personEntityExtract();
        System.out.println(item.personEntityResult);
    }
}

