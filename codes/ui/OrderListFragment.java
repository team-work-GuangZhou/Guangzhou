package com.stardust.guangzhou.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stardust.guangzhou.R;
import com.stardust.guangzhou.ui.product.OrderSubFragment;

/**
 * Created by Stardust and wtf on 2017/4/16.
 */

public class OrderListFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);
        FragmentPagerAdapterBuilder.StoredFragmentPagerAdapter mPagerAdapter = new FragmentPagerAdapterBuilder(getActivity())
                .add(OrderSubFragment.newInstance(0), "全部")
                .add(OrderSubFragment.newInstance(1), "已支付")
                .add(OrderSubFragment.newInstance(2), "待支付")
                .add(OrderSubFragment.newInstance(3), "退款单")
                .build(getChildFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setOffscreenPageLimit(mPagerAdapter.getCount());
        tabLayout.setupWithViewPager(viewPager);
    }


}
