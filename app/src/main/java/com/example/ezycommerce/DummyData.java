package com.example.ezycommerce;

import com.example.ezycommerce.model.Category;

import java.util.ArrayList;

public class DummyData {

    public static ArrayList<Category> getCategoryData() {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Scifi"));
        categories.add(new Category("Accessories"));
        categories.add(new Category("Business"));
        categories.add(new Category("Mystery"));
        categories.add(new Category("Cookbook"));
        return categories;
    }
}
