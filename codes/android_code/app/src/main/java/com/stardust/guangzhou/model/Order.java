package com.stardust.guangzhou.model;

import java.io.Serializable;

/**
 * Created by Stardust on 2017/4/16.
 */

public class Order implements Serializable {

    public static final String STATUS_COMPLETE = "订单完成";
    public static final String STATUS_UNPAY = "待支付";
    public static final String STATUS_PAYED = "买家已支付";
    public static final String STATUS_REFUND = "已退款";


    public String type;
    public String title;
    public String name;
    public String location;
    public String price;
    public String status;
    public int liquidateDamages;
    public String startday;
    public String endday;
    public String image;


    public Order(String type, String title,String name,String location, String price, String status,int liquidateDamages,String startday,String endday,String image) {
        this.type = type;
        this.title = title;
        this.name = name;
        this.location = location;
        this.price = price;
        this.status = status;
        this.liquidateDamages = liquidateDamages;
        this.startday = startday;
        this.endday = endday;
        this.image = image;
    }

    public int getLiquidateDamages() {
        return liquidateDamages;
    }

    public String getEndday() {
        return endday;
    }

    public String getImage() {
        return image;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }

    public String getStartday() {
        return startday;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
