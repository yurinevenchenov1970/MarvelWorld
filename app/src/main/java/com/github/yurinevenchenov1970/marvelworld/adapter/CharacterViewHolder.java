package com.github.yurinevenchenov1970.marvelworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.yurinevenchenov1970.marvelworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Yuri Nevenchenov on 9/11/2017.
 */

public class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private CharacterClickListener mClickListener;

    @BindView(R.id.character_image_view)
    ImageView mImageView;

    @BindView(R.id.character_name_text_view)
    TextView mNameTextView;

    @BindView(R.id.description_text_view)
    TextView mDescriptionTextView;

    public CharacterViewHolder(View itemView, CharacterClickListener clickListener) {
        super(itemView);
        mClickListener = clickListener;
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mClickListener.onItemClick(getLayoutPosition());
    }
}