package com.iip.ui.ner.controller;

import com.iip.ui.ner.controller.Context;

public abstract class RootController {
    public RootController(){
        //初始化时保存当前Controller实例
        com.iip.ui.ner.controller.Context.controllers.put(this.getClass().getSimpleName(), this);
    }

    public abstract void init();
}

