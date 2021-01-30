package com.example.ezycommerce.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit mClient;

    private static Retrofit getClient() {
        if (mClient == null) {
            mClient = new Retrofit.Builder()
                    .baseUrl("https://u73olh7vwg.execute-api.ap-northeast-2.amazonaws.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mClient;
    }

    public static ApiService service() {
        return getClient().create(ApiService.class);
    }
}
