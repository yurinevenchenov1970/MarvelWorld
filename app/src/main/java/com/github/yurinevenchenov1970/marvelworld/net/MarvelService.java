package com.github.yurinevenchenov1970.marvelworld.net;

import com.github.yurinevenchenov1970.marvelworld.bean.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author Yuri Nevenchenov on 9/6/2017.
 */

public interface MarvelService {
    @GET("v1/public/characters")
    Call<BaseResponse> getCharacters(@Query("nameStartsWith") String nameStartsWith,
                                     @Query("apikey") String apiKey,
                                     @Query("ts") int timeStamp,
                                     @Query("hash") String hash);
}