package com.github.yurinevenchenov1970.marvelworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.yurinevenchenov1970.marvelworld.bean.BaseResponse;
import com.github.yurinevenchenov1970.marvelworld.net.ApiClient;
import com.github.yurinevenchenov1970.marvelworld.net.MarvelService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "8ce13978a563fbc8c0353ac9008e8bfd";
    private static final String HASH = "da3930ee8a5cf7ae5bce3dc296b6d6b6";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getResponse();
    }

    private void getResponse() {
        String name = "hul";

                MarvelService service = ApiClient.getClient().create(MarvelService.class);
        Call<BaseResponse> responseCall = service.getCharacters(name, API_KEY, 1, HASH);

                responseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                BaseResponse baseResponse = response.body();
                                if (baseResponse != null) {
                                        System.out.println(baseResponse.toString());
                                    } else {
                                        System.out.println("it is null");
                                    }
                            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                                System.out.println("failure " + t.getMessage());
                            }
        });
    }
}