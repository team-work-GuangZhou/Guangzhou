package com.stardust.guangzhou.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.facebook.drawee.view.GenericDraweeView;
import com.stardust.guangzhou.App;
import com.stardust.guangzhou.R;
import com.stardust.guangzhou.model.Order;

/**
 * Created by Bob(who?) on 2017/12/3.
 */

public class OrderActivity extends AppCompatActivity {

    private static final String TAG = "Order Detail Activity";
    private Order item;
    private AMapLocationClient mLocationClient = null;
    private  AMapLocationClientOption mLocationOption = null;

    private Button ckLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initialLocationClient();

        item = (Order) getIntent().getSerializableExtra("item");

        ((TextView) findViewById(R.id.order_status)).setText(item.getStatus());
        ((TextView) findViewById(R.id.price)).setText(item.getPrice());
        ((TextView) findViewById(R.id.price1)).setText(item.getPrice());
        ((TextView) findViewById(R.id.breach_num_pay)).setText(item.getLiquidateDamages()+"");
        ((TextView) findViewById(R.id.start_day)).setText(item.getStartday());
        ((TextView) findViewById(R.id.end_day)).setText(item.getEndday());
        ((TextView) findViewById(R.id.post_title)).setText(item.getTitle());
        ((TextView) findViewById(R.id.producer_name)).setText(item.getName());
        ((TextView) findViewById(R.id.consumer_name)).setText(App.getClient().getUser().name);
        ((TextView) findViewById(R.id.location)).setText(item.getLocation());
        ((GenericDraweeView) findViewById(R.id.post_image)).setImageURI(Uri.parse(App.getClient().getBaseUrl() + item.getImage()));
        //打卡
        ckLocation = (Button) findViewById(R.id.check_location);

        ckLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mLocationClient.startLocation();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mLocationClient != null) {
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
            mLocationClient = null;
        }
    }

    private void initialLocationClient(){
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置单次定位
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (null != aMapLocation) {

                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if(aMapLocation.getErrorCode() == 0){
                    sb.append("定位成功" + "\n");
                    /*sb.append("定位类型: " + aMapLocation.getLocationType() + "\n");

                    sb.append("国    家    : " + aMapLocation.getCountry() + "\n");
                    sb.append("省            : " + aMapLocation.getProvince() + "\n");
                    sb.append("市            : " + aMapLocation.getCity() + "\n");
                    sb.append("区            : " + aMapLocation.getDistrict() + "\n");*/

                    sb.append("地    址    : " + aMapLocation.getAddress() + "\n");
                    ((TextView)findViewById(R.id.order_location)).setText("打卡地点    : " + aMapLocation.getAddress() + "\n");
                    ckLocation.setText("打卡完成");
                    ckLocation.setEnabled(false);

                    //定位完成的时间
//                    sb.append("定位时间: " + Utils.formatUTC(aMapLocation.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + aMapLocation.getErrorCode() + "\n");
//                    sb.append("错误信息:" + aMapLocation.getErrorInfo() + "\n");
                    sb.append("错误描述:" + aMapLocation.getLocationDetail() + "\n");
                }
                //定位之后的回调时间
//                sb.append("回调时间: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");

                //解析定位结果，
                String result = sb.toString();
                Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), "定位失败", Toast.LENGTH_SHORT).show();
            }
            mLocationClient.stopLocation();
        }
    };
}
