package com.example.ezycommerce.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ezycommerce.R;

public class DetailActivity extends AppCompatActivity {

    public static final String BOOK_ID = "BOOK_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DetailFragment fragment = (DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detail_fragment);
        Integer bookId = getIntent().getIntExtra(BOOK_ID, 0);
        if (fragment != null) {
            fragment.setSelectedBookId(bookId);
        }
    }
}