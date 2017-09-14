package com.github.yurinevenchenov1970.marvelworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Yuri Nevenchenov on 9/13/2017.
 */

public class UrlViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    private CharacterClickListener mClickListener;

    public UrlViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onClick(View v) {
        mClickListener.onItemClick(getLayoutPosition());
    }
}