package com.github.yurinevenchenov1970.marvelworld;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.yurinevenchenov1970.marvelworld.adapter.CharacterAdapter;
import com.github.yurinevenchenov1970.marvelworld.adapter.CharacterClickListener;
import com.github.yurinevenchenov1970.marvelworld.bean.MarvelCharacter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharactersFragment extends Fragment implements CharacterClickListener {
    public static final String TAG = CharactersFragment.class.getSimpleName();
    private static final String EXTRA_CHARACTERS = "extra_characters";

    private List<MarvelCharacter> mCharacters;

    private OnItemClickListener mListener;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private CharacterAdapter mAdapter;

    public static CharactersFragment newInstance(ArrayList<MarvelCharacter> characters) {
        CharactersFragment fragment = new CharactersFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(EXTRA_CHARACTERS, characters);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickListener) {
            mListener = (OnItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                   + " must implement OnItemClickListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCharacters = getArguments().getParcelableArrayList(EXTRA_CHARACTERS);
            mAdapter = new CharacterAdapter(mCharacters, this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characters, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(int position) {
        if (mListener != null) {
            mListener.onFragmentInteraction(mCharacters.get(position));
        }
    }

    public interface OnItemClickListener {
        void onFragmentInteraction(MarvelCharacter character);
    }
}