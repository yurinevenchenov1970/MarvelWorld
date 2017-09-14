package com.github.yurinevenchenov1970.marvelworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.github.yurinevenchenov1970.marvelworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Yuri Nevenchenov on 9/14/2017.
 */

public class MarvelUrlViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private CharacterClickListener mClickListener;

    @BindView(R.id.url_button)
    Button mUrlButton;

    public MarvelUrlViewHolder(View itemView, CharacterClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mClickListener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mClickListener.onItemClick(getLayoutPosition());
    }
}