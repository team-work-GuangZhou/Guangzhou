package com.stardust.guangzhou.ui;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.GenericDraweeView;
import com.stardust.guangzhou.App;
import com.stardust.guangzhou.R;
import com.stardust.guangzhou.model.ProductItem;

/**
 * Created by Stardust on 2017/4/15.
 */

public class DetailActivity extends AppCompatActivity {

    private Button mPay;

    private ProductItem item;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        item = (ProductItem) getIntent().getSerializableExtra("item");
        ((TextView) findViewById(R.id.username)).setText(item.getUserName());
        ((TextView) findViewById(R.id.location)).setText(item.getLocation());
        ((TextView) findViewById(R.id.detail)).setText(item.getDescription());
        ((TextView) findViewById(R.id.title)).setText(item.getTitle());
        ((TextView) findViewById(R.id.start_day)).setText(item.getStartday());
        ((TextView) findViewById(R.id.end_day)).setText(item.getEndday());
        ((TextView) findViewById(R.id.price)).setText(item.getPrice());
        ((TextView) findViewById(R.id.breach)).setText(item.getBreach()+"%");
        mPay = (Button)findViewById(R.id.pay) ;
        mPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!App.getClient().loginIfNeeded()) {
                    return;
                }
                startActivity(new Intent(DetailActivity.this,PayActivity.class).putExtra("item", item));
            }
        });
        ((GenericDraweeView) findViewById(R.id.image)).setImageURI(Uri.parse(App.getClient().getBaseUrl() + item.getImageUrl()));
        ((GenericDraweeView) findViewById(R.id.portrait)).setImageURI(Uri.parse(App.getClient().getBaseUrl() + item.getAvatar()));
    }

}
