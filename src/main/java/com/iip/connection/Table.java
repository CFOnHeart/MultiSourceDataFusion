package com.iip.connection;

/**
 * 表格类
 * Created by gegaojian on 7/20/17.
 * finished by ... on ...
 * fixed by ... on ...
 */

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;


public class Table {
    private String tableName;
    private Map<String, Field> fields = new LinkedHashMap<String, Field>();
    //....

    public Table(String tableName){
        this.tableName = tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setFields(LinkedHashMap<String, Field> fields) {
        this.fields = fields;
    }

    public String  getTableName(){ return tableName; }

    public Map<String, Field> getFields() {
        return fields;
    }

//    public void addField(TargetField field){
//        this.fields.add(field);
//    }

//    public void deleteFieldByIndex(int index){
//        this.fields.remove(index);
//    }

//    public void editFieldByIndex(int index, TargetField field){
//        this.fields.set(index, field);
//    }
}
