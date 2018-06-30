package com.stardust.guangzhou.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stardust.guangzhou.App;
import com.stardust.guangzhou.R;
import com.stardust.guangzhou.model.Post;
import com.stardust.guangzhou.model.ProductItem;
import com.stardust.guangzhou.net.Client;
import com.stardust.guangzhou.ui.product.ProductAdapter;
import com.stardust.widget.RecyclerItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bob on 2018/4/17.
 */

public class MyProductFragment extends Fragment {

    public static MyProductFragment newInstance(String type) {
        MyProductFragment fragment = new MyProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    private ProductAdapter mProductAdapter = new ProductAdapter();
    private String mUrl = "list";
    private String mType = Post.TYPE_CONSUMER;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getString("type", Post.TYPE_CONSUMER);
        mUrl = mType.equals(Post.TYPE_CONSUMER) ? "list1" : "list";
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_provider_list, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView productList = (RecyclerView) view.findViewById(R.id.product_list);
        productList.setLayoutManager(new LinearLayoutManager(getActivity()));
        productList.setAdapter(mProductAdapter);
        productList.addOnItemTouchListener(new RecyclerItemClickListener(getActivity()) {
            @Override
            public void onItemClick(View view, int position) {
                ProductItem item = mProductAdapter.getItemAt(position);
                getActivity().startActivity(new Intent(getActivity(), DetailActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("item", item));
            }

        });
        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!App.getClient().loginIfNeeded()) {
                    return;
                }
                getActivity().startActivity(new Intent(getActivity(), PostActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtras(getArguments()));
            }
        });
        refresh();
    }


    @Subscribe
    public void onListChange(String message) {
        refresh();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void refresh() {
        App.getClient().list(mUrl, new Client.Callback<List<ProductItem>>() {
            @Override
            public void accept(final List<ProductItem> productItems) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<ProductItem> res = new ArrayList<>();
                        for(int i = 0;i < productItems.size();i++){
                            if (App.getClient().getUser().getName().equals(productItems.get(i).getUserName()))
                                res.add(productItems.get(i));
                        }
                        mProductAdapter.setProductItemList(res);
                    }
                });
            }
        });
    }

    private void addDemoData() {
        mProductAdapter.setProductItemList(Arrays.asList(
                new ProductItem("杰佬带你领略广州独特的足球文化", "2017.02.13  5天  1000次浏览", "大学城番禺", "Stardust","100",60,"2017.12.2","2017.12.2"),
                new ProductItem("杰佬带你领略广州独特的足球文化", "2017.02.13  5天  1000次浏览", "大学城番禺", "Stardust","200",80,"2017.12.2","2017.12.2"),
                new ProductItem("杰佬带你领略广州独特的足球文化", "2017.02.13  5天  1000次浏览", "大学城番禺", "Stardust","300",60,"2017.12.2","2017.12.2"),
                new ProductItem("川美可师带你体验1次绘画", "2017.02.13  5天  8999次浏览", "慎思园6号", "孔令爽","500",80,"2017.12.2","2017.12.2")
        ));
    }
}
