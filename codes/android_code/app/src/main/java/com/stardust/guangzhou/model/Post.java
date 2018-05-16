package com.stardust.guangzhou.model;

/**
 * Created by Stardust on 2017/4/15.
 */

public class Post {

    public static final String TYPE_CONSUMER = "consumer";
    public static final String TYPE_PRODUCER = "producer";

    public String title;
    public String post;
    public String location;
    public String type;
    public String image;
    public String price;
    public int liquidateDamages;
    public String startAt;
    public String endAt;


    public Post(String title, String post, String location, String type, String image,String price,int liquidateDamages,String startAt,String endAt) {
        this.title = title;
        this.post = post;
        this.location = location;
        this.type = type;
        this.image = image;
        this.price = price;
        this.liquidateDamages = liquidateDamages;
        this.startAt = startAt;
        this.endAt = endAt;
    }


}
