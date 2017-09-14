package com.github.yurinevenchenov1970.marvelworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.yurinevenchenov1970.marvelworld.R;
import com.github.yurinevenchenov1970.marvelworld.bean.MarvelCharacter;
import com.github.yurinevenchenov1970.marvelworld.bean.Thumbnail;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Yuri Nevenchenov on 9/11/2017.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private List<MarvelCharacter> mCharacterList;
    private CharacterClickListener mClickListener;

    public CharacterAdapter(List<MarvelCharacter> characterList,
                            CharacterClickListener clickListener) {
        mCharacterList = characterList;
        mClickListener = clickListener;
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_layout, null);
        return new CharacterViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        MarvelCharacter character = mCharacterList.get(position);
        holder.mNameTextView.setText(character.mName);
        holder.mDescriptionTextView.setText(character.mDescription);
        Picasso.with(holder.itemView.getContext())
                .load(character.mThumbnail.getFullPath(Thumbnail.PORTRAIT_FANTASTIC))
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.error)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mCharacterList.size();
    }
}