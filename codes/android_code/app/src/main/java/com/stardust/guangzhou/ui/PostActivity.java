package com.stardust.guangzhou.ui;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.stardust.guangzhou.App;
import com.stardust.guangzhou.R;
import com.stardust.guangzhou.model.Post;
import com.stardust.guangzhou.net.Client;
import com.stardust.tool.ImageSelector;
import com.stardust.tool.OnActivityResultDelegate;
import com.stardust.tool.ViewBinder;
import com.stardust.tool.ViewBinding;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/**
 * Created by Stardust and someone else on 2018/7/15.
 */

public class PostActivity extends AppCompatActivity {

    private OnActivityResultDelegate.Intermediary mIntermediary = new OnActivityResultDelegate.Intermediary();

    private byte[] mImageData;
    @ViewBinding.Id(R.id.image)
    private ImageView mImage;

    @ViewBinding.Id(R.id.title)
    private EditText mTitle;
    @ViewBinding.Id(R.id.content)
    private EditText mContent;

    @ViewBinding.Id(R.id.price_num)
    private EditText mPrice;

    @ViewBinding.Id(R.id.breach_num)
    private NumberPicker mBreach;

    @ViewBinding.Id(R.id.set_location)
    private LinearLayout mSetLocation;

    @ViewBinding.Id(R.id.start_day)
    private TextView mStart_day;

    @ViewBinding.Id(R.id.end_day)
    private TextView mEnd_day;

    private MaterialDialog mProgressDialog;
    private int startyear,startmonth,startday,endyear,endmonth,endday;

    @ViewBinding.Id(R.id.select_location)
    private TextView location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ViewBinder.bind(this);
        if (getIntent().getStringExtra("type").equals(Post.TYPE_CONSUMER)) {
            mPrice.setVisibility(View.GONE);
        }

        //设置违约金
        set_mBreach();
        //初始化开始结束日期
        set_day();
    }

    private void set_day() {
        Calendar now = Calendar.getInstance();
        mStart_day.setText(now.get(Calendar.YEAR)+"."+(now.get(Calendar.MONTH)+1)+"."+now.get(Calendar.DAY_OF_MONTH));
        mEnd_day.setText(now.get(Calendar.YEAR)+"."+(now.get(Calendar.MONTH)+1)+"."+now.get(Calendar.DAY_OF_MONTH));
        startyear = Calendar.YEAR;
        startmonth = Calendar.MONTH+1;
        startday = Calendar.DAY_OF_MONTH;
        endyear = Calendar.YEAR;
        endmonth = Calendar.MONTH+1;
        endday = Calendar.DAY_OF_MONTH;
    }

    private void set_mBreach() {
        mBreach.setMinValue(60);
        mBreach.setMaxValue(100);
        mBreach.setValue(60);
        mBreach.setWrapSelectorWheel(false);
        setPickerMargin(mBreach);
    }


    @ViewBinding.Click(R.id.image_container)
    private void selectImage() {
        new ImageSelector(this, mIntermediary, new ImageSelector.ImageSelectorCallback() {
            @Override
            public void onImageSelected(ImageSelector selector, InputStream is) {
                if (is == null)
                    return;
                try {
                    mImageData = new byte[is.available()];
                    is.read(mImageData);
                    is.close();
                    Drawable drawable = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(mImageData, 0, mImageData.length));
                    mImage.setImageDrawable(drawable);
                    mImage.setPadding(0, 0, 0, 0);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }).select();
    }

    @ViewBinding.Click(R.id.set_location)
    private void set_location(){
        Intent intent = new Intent(PostActivity.this, LocationActivity.class);
        startActivityForResult(intent, 1);
    }

    @ViewBinding.Click(R.id.send)
    private void send() {
        if (!validateInput()) {
            return;
        }
        if(verifyDate()){
            showProgressBar();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String imgData = Base64.encodeToString(mImageData, Base64.DEFAULT);
                    String title = mTitle.getText().toString();
                    String content = mContent.getText().toString();
                    String price = mPrice.getText().toString();
                    int breach = mBreach.getValue();
                    String startday = mStart_day.getText().toString();
                    String endday = mEnd_day.getText().toString();
                    App.getClient().sendPost(new Post(title, content, "广州大学城", getIntent().getStringExtra("type"), imgData,price,breach,startday,endday), new Client.Callback<Exception>() {
                        @Override
                        public void accept(Exception e) {
                            if (e == null) {
                                hideProgressBar();
                                EventBus.getDefault().post("");
                                finish();
                            } else {
                                e.printStackTrace();
                                Toast.makeText(PostActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                                hideProgressBar();
                            }
                        }
                    });

                }
            }).start();
        }
        else
            Toast.makeText(this, "活动结束日期不能小于开始日期", Toast.LENGTH_SHORT).show();

    }
    //判断日期是否合法
    private boolean verifyDate() {
        if(endyear < startyear)
            return false;
        else if(endyear == startyear && endmonth < startmonth)
            return false;
        else if(endyear == startyear && endmonth == startmonth&& endday < startday)
            return false;
        else return true;
    }

    @ViewBinding.Click(R.id.set_startday)
    private void setStartDay(){
        Calendar cal = Calendar.getInstance();
        final DatePickerDialog mDialog = new DatePickerDialog(this,null,
                cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
        mDialog.setButton(DialogInterface.BUTTON_POSITIVE,"完成",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //通过mDialog.getDatePicker()获得dialog上的DatePicker组件，然后可以获取日期信息
                DatePicker datePicker = mDialog.getDatePicker();
                startyear = datePicker.getYear();
                startmonth = datePicker.getMonth()+1;
                startday = datePicker.getDayOfMonth();
                mStart_day.setText(startyear+ "." + startmonth + "."+ startday);
            }
        });
        //取消按钮，如果不需要直接不设置即可
        mDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.cancel();
                    }
                });
        mDialog.show();
    }

    @ViewBinding.Click(R.id.set_endday)
    private void setEndDay(){
        Calendar cal = Calendar.getInstance();
        final DatePickerDialog mDialog = new DatePickerDialog(this,null,
                cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
        mDialog.setButton(DialogInterface.BUTTON_POSITIVE,"完成",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //通过mDialog.getDatePicker()获得dialog上的DatePicker组件，然后可以获取日期信息
                DatePicker datePicker = mDialog.getDatePicker();
                endyear = datePicker.getYear();
                endmonth = datePicker.getMonth()+1;
                endday = datePicker.getDayOfMonth();
                mEnd_day.setText(endyear+ "." + endmonth + "."+ endday);
            }
        });
        //取消按钮，如果不需要直接不设置即可
        mDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDialog.cancel();
            }
        });
        mDialog.show();
    }

    private void showProgressBar() {
        mProgressDialog = new MaterialDialog.Builder(this)
                .progress(true, 0)
                .content("发送中...")
                .show();
    }

    private void hideProgressBar() {
        if (mProgressDialog == null)
            return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        });
    }

    private boolean validateInput() {
        if (!validateInput(mTitle, "标题"))
            return false;
        if (!validateInput(mContent, "内容"))
            return false;
        if (mImageData == null) {
            Toast.makeText(this, "需要选择一张图片", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(location.getText().equals("选择地点")){
            Toast.makeText(this, "请选择活动地点", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateInput(EditText text, String field) {
        if (TextUtils.isEmpty(text.getText())) {
            Toast.makeText(this, field + "不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String select_location = data.getStringExtra("select_location");

                    location.setText(select_location);
                }
                break;
            default:
                mIntermediary.onActivityResult(requestCode, resultCode, data);
        }
    }
    //修改numpicker的元素间隔
    private void setPickerMargin(NumberPicker picker) {
        LinearLayout.LayoutParams p= (LinearLayout.LayoutParams) picker.getLayoutParams();
        p.setMargins(0,0,0,0);
        if(Build.VERSION.SDK_INT> Build.VERSION_CODES.JELLY_BEAN_MR1){
            p.setMarginStart(0);
            p.setMarginEnd(0);
        }
    }
}
