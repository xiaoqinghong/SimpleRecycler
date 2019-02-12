package com.eric.simple.adapter;

import com.eric.adapter.component.SimpleMultipleAdapter;

/**
 * Author: Eric
 * Date: Created in 2019/2/12 2:08 PM
 * Description： MultipleAdapter需要的bean类必须实现SimpleMultipleAdapter.TypeBind接口
 */
public class MultipleBean implements SimpleMultipleAdapter.TypeBind {
    private int type;
    private String name;

    public MultipleBean(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int type() {
        return type;
    }
}
