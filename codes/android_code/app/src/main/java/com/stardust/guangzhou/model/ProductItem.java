package com.stardust.guangzhou.model;

import java.io.Serializable;

/**
 * Created by Stardust on 2017/4/15.
 */

public class ProductItem implements Serializable {

    private String title;
    private String summary;
    private String name;
    private String location;
    private String image;
    private String post;
    private String avatar;
    private String price;
    public int liquidateDamages;
    public String startAt;
    public String endAt;

    public ProductItem() {

    }

    public ProductItem(String title, String summary, String location, String userName,String price,int liquidateDamages,String startAt,String endAt) {
        this.title = title;
        this.summary = summary;
        name = userName;
        this.location = location;
        this.price = price;
        this.liquidateDamages = liquidateDamages;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public String getUserName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getImageUrl() {
        return image;
    }

    public String getPrice(){
        return price;
    }

    public int getBreach(){
        return liquidateDamages;
    }
    public String getDescription() {
        return post;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getStartday(){
        return startAt;
    }
    public String getEndday(){
        return endAt;
    }
}
