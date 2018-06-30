package com.stardust.guangzhou.ui.product;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.stardust.guangzhou.R;
import com.stardust.guangzhou.model.ProductItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stardust on 2017/4/15.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private List<ProductItem> mProductItemList = new ArrayList<>();

    public void setProductItemList(List<ProductItem> productItemList) {
        mProductItemList = productItemList;
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bind(mProductItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mProductItemList.size();
    }

    public ProductItem getItemAt(int position) {
        return mProductItemList.get(position);
    }
}
