package com.github.yurinevenchenov1970.marvelworld.net;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Yuri Nevenchenov on 9/7/2017.
 */

public class ApiClient {
    public static final String BASE_URL = "http://gateway.marvel.com";
    private static Retrofit sRetrofit = null;

    private ApiClient() {
        throw new IllegalStateException("can't create an object");
    }

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}