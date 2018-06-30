package com.stardust.guangzhou.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.stardust.guangzhou.R;
import com.stardust.guangzhou.model.Post;

/**
 * Created by Bob on 2018/4/16.
 */

public class MyProductActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_rent:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_borrow:
                    mViewPager.setCurrentItem(1);
                    return true;
            }
            return false;
        }

    };
    private ViewPager mViewPager;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.getImagePipeline().clearCaches();
        setContentView(R.layout.activity_my_product);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return MyProductFragment.newInstance(Post.TYPE_PRODUCER);
                    case 1:
                        return MyProductFragment.newInstance(Post.TYPE_CONSUMER);
                    default:
                        return MyProductFragment.newInstance(Post.TYPE_CONSUMER);
                }
            }
        };
        mViewPager = (ViewPager) findViewById(R.id.content);
        mViewPager.setAdapter(adapter);
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
            itemView.setShiftingMode(false);
            itemView.setChecked(false);
        }
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public MenuItem mSelectedMenuItem;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            // onPagedSelected need to be override
	    @Override
            public void onPageSelected(int position) {
                if (mSelectedMenuItem != null) {
                    mSelectedMenuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }

                mSelectedMenuItem = navigation.getMenu().getItem(position);
                mSelectedMenuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
