package com.github.yurinevenchenov1970.marvelworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.yurinevenchenov1970.marvelworld.bean.MarvelUrl;

import java.util.List;

/**
 * @author Yuri Nevenchenov on 9/13/2017.
 */

public class UrlAdapter extends RecyclerView.Adapter<UrlViewHolder> {

    private List<MarvelUrl> mMarvelUrls;

    @Override
    public UrlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(UrlViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}