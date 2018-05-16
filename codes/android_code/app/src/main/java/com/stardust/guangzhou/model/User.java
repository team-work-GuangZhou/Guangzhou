package com.stardust.guangzhou.model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by Stardust on 2017/4/16.
 */

public class User {

    public static final Type TYPE = new TypeToken<User>() {
    }.getType();

    public String name;
    public String avatar;


    public User() {
        reset();
    }

    public String getName(){
        return name;
    }

    public void reset() {
        avatar = null;
        name = "未登录";
    }
}
