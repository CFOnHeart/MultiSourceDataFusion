package com.iip.ui.space_time.controller;

/**
 * @Author Junnor.G
 * @Date 2018/12/11 下午7:29
 */
public abstract class RootController {
    public RootController(){
        //初始化时保存当前Controller实例
        Context.controllers.put(this.getClass().getSimpleName(), this);
    }

    public abstract void init();
}
