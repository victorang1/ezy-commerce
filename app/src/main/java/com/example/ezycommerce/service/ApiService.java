package com.example.ezycommerce.service;

import com.example.ezycommerce.datamodel.BookResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("staging/book")
    Call<BookResponse> getAllBook(@Query("nim") String userId, @Query("nama") String username);
}
