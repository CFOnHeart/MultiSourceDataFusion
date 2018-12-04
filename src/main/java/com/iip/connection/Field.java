package com.iip.connection;

/**
 * Created by gegaojian on 7/20/17.
 */
public class Field {
    protected String name;
    protected String fieldType;
    protected String isKey;//有“Y” “N” “”三种取值

    public Field(String name, String fieldType, String isKey) {
        this.name = name;
        this.fieldType = fieldType;
        this.isKey = isKey;
    }

    public String getName() {
        return name;
    }

    public String getFieldType() {
        return fieldType;
    }

    public String getIsKey() {
        return isKey;
    }

    public boolean isKey() {
        return isKey == "Y";
    }

}
