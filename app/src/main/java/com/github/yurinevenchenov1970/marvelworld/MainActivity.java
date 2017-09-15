package com.github.yurinevenchenov1970.marvelworld;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.yurinevenchenov1970.marvelworld.bean.BaseResponse;
import com.github.yurinevenchenov1970.marvelworld.bean.MarvelCharacter;
import com.github.yurinevenchenov1970.marvelworld.net.ApiClient;
import com.github.yurinevenchenov1970.marvelworld.net.MarvelService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CharactersFragment.OnItemClickListener{

    private static final String API_KEY = "8ce13978a563fbc8c0353ac9008e8bfd";
    private static final String HASH = "da3930ee8a5cf7ae5bce3dc296b6d6b6";
    private static final String TAG = MainActivity.class.getSimpleName();

    private MarvelService mService;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.search_view)
    SearchView mSearchView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
    }

    @Override
    public void onFragmentInteraction(MarvelCharacter character){
        startActivity(CharacterActivity.createExplicitIntent(this, character));
    }

    //region private methods

    private void initUI() {
        setSupportActionBar(mToolbar);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (Utils.hasConnection(getApplicationContext())) {
                    showProgressBar();
                    getDataFromServer(query);
                    mSearchView.clearFocus();
                } else {
                    showNoConnectionMessage();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void showNoConnectionMessage() {
        Toast.makeText(this, R.string.no_connection, Toast.LENGTH_LONG).show();
    }

    private void getDataFromServer(String query) {
        mService = ApiClient.getClient().create(MarvelService.class);
        Call<BaseResponse<MarvelCharacter>> responseCall = mService.getCharacters(query, API_KEY, 1, HASH);
        responseCall.enqueue(new Callback<BaseResponse<MarvelCharacter>>() {
            @Override
            public void onResponse(Call<BaseResponse<MarvelCharacter>> call, Response<BaseResponse<MarvelCharacter>> response) {
                BaseResponse<MarvelCharacter> baseResponse = response.body();
                if(baseResponse != null) {
                    hideProgressBar();
                    showData(new ArrayList<>(baseResponse.mResponseData.mCharacterList));
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<MarvelCharacter>> call, Throwable t) {
                Log.e(TAG, "failure " + t.getMessage());
            }
        });
    }

    private void showData(ArrayList<MarvelCharacter> characters) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, CharactersFragment.newInstance(characters))
                .addToBackStack(CharactersFragment.TAG)
                .commit();
    }

    //endregion
}