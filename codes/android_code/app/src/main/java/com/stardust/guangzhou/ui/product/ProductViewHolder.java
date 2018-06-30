package com.stardust.guangzhou.ui.product;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.GenericDraweeView;
import com.stardust.guangzhou.App;
import com.stardust.guangzhou.R;
import com.stardust.guangzhou.model.ProductItem;

import java.net.URI;

/**
 * Created by Stardust on 2017/4/15.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private TextView mTitle, mSummary, mLocation, mUserName;
    private GenericDraweeView mImage;
    private ImageView mPortrait;

    public ProductViewHolder(View itemView) {
        super(itemView);
        mTitle = $(R.id.title);
        mSummary = $(R.id.summary);
        mLocation = $(R.id.location);
        mImage = $(R.id.image);
        mUserName = $(R.id.username);
        mPortrait = $(R.id.portrait);
    }

    public void bind(ProductItem item) {
        mTitle.setText(item.getTitle());
        mSummary.setText(item.getSummary());
        mLocation.setText(item.getLocation());
        mUserName.setText(item.getUserName());
        mImage.setImageURI(Uri.parse(App.getClient().getBaseUrl() + item.getImageUrl()));
        mPortrait.setImageURI(Uri.parse(App.getClient().getBaseUrl() + item.getAvatar()));
    }

    @SuppressWarnings("unchecked")
    private <T extends View> T $(int id) {
        return (T) itemView.findViewById(id);
    }
}
