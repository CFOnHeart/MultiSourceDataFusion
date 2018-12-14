package com.iip.data.entity;

/**
 * @Author Junnor.G
 * @Date 2018/12/6 下午6:
 * 时间实体类
 */
public class TimeEntity extends Entity{

    public TimeEntity(String word){
        super(word);
    }
    @Override
    public String getType(){
        return this.type;
    }
}
