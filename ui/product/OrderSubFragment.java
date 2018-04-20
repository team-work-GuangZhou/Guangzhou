package com.stardust.guangzhou.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stardust.guangzhou.R;
import com.stardust.guangzhou.model.Order;
import com.stardust.guangzhou.model.OrderList;
import com.stardust.guangzhou.ui.OrderActivity;
import com.stardust.widget.RecyclerItemClickListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

/**
 * Created by Stardust on 2017/4/16.
 */

public class OrderSubFragment extends Fragment {


    private OrderAdapter adapter = new OrderAdapter();

    public static OrderSubFragment newInstance(int type) {
        OrderSubFragment fragment = new OrderSubFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_sub, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.setOrderList(OrderList.get(getArguments().getInt("type")));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.order_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(0xffd9d9d9)
                .size(20)
                .showLastDivider()
                .build());
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity()) {
            @Override
            public void onItemClick(View view, int position) {
                Order item = adapter.getItemAt(position);
                getActivity().startActivity(new Intent(getActivity(), OrderActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("item", item));
            }
        });
    }
}
