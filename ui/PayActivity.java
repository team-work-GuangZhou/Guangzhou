package com.stardust.guangzhou.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.GenericDraweeView;
import com.stardust.guangzhou.App;
import com.stardust.guangzhou.R;
import com.stardust.guangzhou.model.Order;
import com.stardust.guangzhou.model.OrderList;
import com.stardust.guangzhou.model.Post;
import com.stardust.guangzhou.model.ProductItem;

/**
 * Created by Bob on 2017/11/23.
 */

public class PayActivity extends AppCompatActivity {

    private ProductItem item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        item = (ProductItem) getIntent().getSerializableExtra("item");

        ((TextView) findViewById(R.id.price)).setText(item.getPrice());
        ((TextView) findViewById(R.id.price_total)).setText(item.getPrice());
        ((TextView) findViewById(R.id.location_bottom)).setText(item.getLocation());
        ((TextView) findViewById(R.id.breach_num_pay)).setText(item.getBreach()+"");
        ((TextView) findViewById(R.id.start_day)).setText(item.getStartday());
        ((TextView) findViewById(R.id.end_day)).setText(item.getEndday());
        ((TextView) findViewById(R.id.post_title)).setText(item.getTitle());
        ((TextView) findViewById(R.id.producer_name)).setText(item.getUserName());
        ((TextView) findViewById(R.id.consumer_name)).setText(App.getClient().getUser().name);
        ((TextView) findViewById(R.id.location)).setText(item.getLocation());
        ((GenericDraweeView) findViewById(R.id.post_image)).setImageURI(Uri.parse(App.getClient().getBaseUrl() + item.getImageUrl()));

        ((Button) findViewById(R.id.pay)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = new Order(Post.TYPE_CONSUMER,item.getTitle(),item.getUserName(),item.getLocation(),item.getPrice(),Order.STATUS_PAYED,item.getBreach(),item.getStartday(),item.getEndday(),item.getImageUrl());
                OrderList.add2list(order);
                startActivity(new Intent(PayActivity.this,OrderActivity.class).putExtra("item",order));
                finish();
            }
        });
    }



}
