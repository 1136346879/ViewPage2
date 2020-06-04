package com.example.viewpager2demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author wdx
 * @date 2020/6/01
 */
public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {
    private List<Integer> images = new ArrayList<>();

    {
        images.add(R.drawable.bg1);
        images.add(R.drawable.bg2);
        images.add(R.drawable.bg3);
        images.add(R.drawable.bg4);
    }
    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        holder.mTvTitle.setText("item--页面--position--" + position);
        holder.mContainer.setBackgroundResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class ViewPagerViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        RelativeLayout mContainer;
        ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            mContainer = itemView.findViewById(R.id.container);
            mTvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
