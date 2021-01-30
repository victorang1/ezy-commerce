package com.example.ezycommerce.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookResponse {

    @SerializedName("nim")
    private String userId;

    @SerializedName("nama")
    private String username;

    private ArrayList<BookItemResponse> products;

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<BookItemResponse> getProducts() {
        return products;
    }
}
