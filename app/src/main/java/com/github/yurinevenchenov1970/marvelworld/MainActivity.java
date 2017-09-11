package com.github.yurinevenchenov1970.marvelworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.yurinevenchenov1970.marvelworld.bean.BaseResponse;
import com.github.yurinevenchenov1970.marvelworld.bean.MarvelCharacter;
import com.github.yurinevenchenov1970.marvelworld.bean.MarvelResource;
import com.github.yurinevenchenov1970.marvelworld.net.ApiClient;
import com.github.yurinevenchenov1970.marvelworld.net.MarvelService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "8ce13978a563fbc8c0353ac9008e8bfd";
    private static final String HASH = "da3930ee8a5cf7ae5bce3dc296b6d6b6";
    private static final String TAG = MainActivity.class.getSimpleName();

    private MarvelService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getResponse();
    }

    private void getResponse() {
        String name = "hul";

        mService = ApiClient.getClient().create(MarvelService.class);
        Call<BaseResponse<MarvelCharacter>> responseCall = mService.getCharacters(name, API_KEY, 1, HASH);

        responseCall.enqueue(new Callback<BaseResponse<MarvelCharacter>>() {
            @Override
            public void onResponse(Call<BaseResponse<MarvelCharacter>> call, Response<BaseResponse<MarvelCharacter>> response) {
                BaseResponse<MarvelCharacter> baseResponse = response.body();
                if (baseResponse != null) {
                    getResource(baseResponse);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<MarvelCharacter>> call, Throwable t) {
                Log.e(TAG, "failure " + t.getMessage());
            }
        });
    }

    private void getResource(BaseResponse<MarvelCharacter> response) {
        List<MarvelCharacter> list = response.mResponseData.mCharacterList;
        MarvelCharacter character = list.get(0);
        String resUrl = character.mComics.mComicList.get(0).mResourseUri;

        Call<BaseResponse<MarvelResource>> responseCall = mService.getMarvelResources(resUrl, 1, HASH, API_KEY);

        responseCall.enqueue(new Callback<BaseResponse<MarvelResource>>() {
            @Override
            public void onResponse(Call<BaseResponse<MarvelResource>> call, Response<BaseResponse<MarvelResource>> response) {
                BaseResponse<MarvelResource> baseResponse = response.body();
                if (baseResponse != null) {
                    System.out.println(baseResponse.mResponseData.toString());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<MarvelResource>> call, Throwable t) {
                Log.e(TAG, "failure with message " + t.getMessage());
            }
        });
    }
}