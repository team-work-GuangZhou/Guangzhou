package com.stardust.guangzhou.ui.product;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stardust.guangzhou.R;
import com.stardust.guangzhou.model.Order;
import com.stardust.guangzhou.model.Post;
import com.stardust.tool.ViewBinder;
import com.stardust.tool.ViewBinding;

/**
 * Created by Stardust on 2017/4/16.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder {

    @ViewBinding.Id(R.id.type)
    private TextView mType;

    @ViewBinding.Id(R.id.status)
    private TextView mStatus;


    @ViewBinding.Id(R.id.title)
    private TextView mTitle;


    @ViewBinding.Id(R.id.location)
    private TextView mLocation;


    @ViewBinding.Id(R.id.price)
    private TextView mPrice;


    @ViewBinding.Id(R.id.comment)
    private TextView mComment;


    @ViewBinding.Id(R.id.service)
    private TextView mService;

    @ViewBinding.Id(R.id.start_day)
    private TextView mStartday;

    @ViewBinding.Id(R.id.end_day)
    private TextView mEndday;

    @ViewBinding.Id(R.id.type_icon)
    private ImageView mTypeIcon;

    public OrderViewHolder(View itemView) {
        super(itemView);
        ViewBinder.bind(this, itemView);
    }

    public void bind(Order order) {
        mType.setText(order.type.equals(Post.TYPE_CONSUMER) ? "租我" : "我租");
        mTypeIcon.setImageResource(!order.type.equals(Post.TYPE_CONSUMER) ? R.drawable.iconmonstr_paper_plane_1_48 : R.drawable.paper_plane_1_48);
        mStatus.setText(order.status);
        mTitle.setText(order.title);
        mLocation.setText(order.location);
        mPrice.setText(order.price);
        mStartday.setText(order.startday);
        mEndday.setText(order.endday);
        setEnabled(mComment, true);
        setEnabled(mService, true);
        mComment.setText("评价订单");
        mService.setText("申请售后");
        switch (order.status) {
            case Order.STATUS_UNPAY:
                mComment.setText("去支付");
                mService.setText("取消订单");
                break;
            case Order.STATUS_PAYED:
                mComment.setText("联系对方");
                mService.setText("申请退款");
                break;
            case Order.STATUS_REFUND:
                setEnabled(mComment, false);
                setEnabled(mService, false);
                break;
        }
    }

    private void setEnabled(TextView textView, boolean enabled) {
        textView.setBackgroundResource(enabled ? R.drawable.btn_enabled : R.drawable.btn);
        textView.setTextColor(enabled ? 0xff4abdcc : 0xffcccccc);
    }
}
