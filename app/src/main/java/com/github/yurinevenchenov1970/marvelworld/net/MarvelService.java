package com.github.yurinevenchenov1970.marvelworld.net;

import com.github.yurinevenchenov1970.marvelworld.bean.BaseResponse;
import com.github.yurinevenchenov1970.marvelworld.bean.MarvelCharacter;
import com.github.yurinevenchenov1970.marvelworld.bean.MarvelResource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author Yuri Nevenchenov on 9/6/2017.
 */
public interface MarvelService {

    @GET("v1/public/characters")
    Call<BaseResponse<MarvelCharacter>> getCharacters(@Query("nameStartsWith") String nameStartsWith,
                                                      @Query("apikey") String apiKey,
                                                      @Query("ts") int timeStamp,
                                                      @Query("hash") String hash);

    @GET
    Call<BaseResponse<MarvelResource>> getMarvelResources(@Url String path,
                                                          @Query("ts") int timeStamp,
                                                          @Query("hash") String hash,
                                                          @Query("apikey") String apiKey);
}