package com.stardust.guangzhou.ui.product;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.stardust.guangzhou.R;
import com.stardust.guangzhou.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stardust on 2017/4/16.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    private List<Order> mOrderList = new ArrayList<>();


    public void setOrderList(List<Order> orderList) {
        mOrderList = orderList;
        notifyDataSetChanged();
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        holder.bind(mOrderList.get(position));
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }

    public Order getItemAt(int position) {
        return mOrderList.get(position);
    }
}
