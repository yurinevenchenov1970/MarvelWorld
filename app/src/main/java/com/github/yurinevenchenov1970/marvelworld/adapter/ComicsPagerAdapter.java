package com.github.yurinevenchenov1970.marvelworld.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.yurinevenchenov1970.marvelworld.R;
import com.github.yurinevenchenov1970.marvelworld.bean.Thumbnail;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Yuri Nevenchenov on 9/15/2017.
 */

public class ComicsPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Thumbnail> mImages;

    public ComicsPagerAdapter(Context context,
                              List<Thumbnail> images) {
        mImages = images;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.comics_preview, container, false);
        Thumbnail thumbnail = mImages.get(position);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.comics_image_view);
        Picasso.with(mContext)
                .load(thumbnail.getFullSize())
                .into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}