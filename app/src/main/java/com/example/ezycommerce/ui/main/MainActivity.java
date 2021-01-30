package com.example.ezycommerce.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.ezycommerce.BuildConfig;
import com.example.ezycommerce.R;
import com.example.ezycommerce.databinding.ActivityMainBinding;
import com.example.ezycommerce.datamodel.BookItemResponse;
import com.example.ezycommerce.datamodel.BookResponse;
import com.example.ezycommerce.model.Book;
import com.example.ezycommerce.model.Category;
import com.example.ezycommerce.service.ApiClient;
import com.example.ezycommerce.ui.list.BookListAdapter;
import com.example.ezycommerce.ui.list.BookListFragment;
import com.example.ezycommerce.util.MappingUtil;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.ICategoryAction, BookListAdapter.IBookListAction {

    private ActivityMainBinding binding;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        books = new ArrayList<>();
        initializeAdapter();
        loadData();
    }

    @Override
    public void onCategoryClick(Category category, Boolean isSelectedExists) {
        Fragment fragment = null;
        if (isSelectedExists) {
            Log.d("<RESULT>", "onCategoryClick if: ");
            ArrayList<Book> filteredList = new ArrayList<>();
            for (Book book : books) {
                if (book.getType().equals(category.getName())) {
                    filteredList.add(book);
                }
            }
            fragment = BookListFragment.newInstance(filteredList, MainActivity.this);
        }
        else {
            Log.d("<RESULT>", "onCategoryClick else: ");
            fragment = BookListFragment.newInstance(books, MainActivity.this);
        }
        loadFragment(fragment);
    }

    @Override
    public void onBookSelected(Book book) {

    }

    private void initializeAdapter() {
        categoryAdapter = new CategoryAdapter(this);
        binding.rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvCategories.setHasFixedSize(true);
        binding.rvCategories.setAdapter(categoryAdapter);
    }

    private void loadData() {
        ApiClient.service().getAllBook(BuildConfig.USER_ID, BuildConfig.USERNAME)
                .enqueue(new Callback<BookResponse>() {
                    @Override
                    public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                        books.clear();
                        if (response.isSuccessful() && response.body() != null) {
                            Log.d("<RESULT>>", "onResponse: " + new Gson().toJson(response.body()));
                            ArrayList<BookItemResponse> bookResponse = response.body().getProducts();
                            ArrayList<Book> result = MappingUtil.mapResponseToBook(bookResponse);
                            ArrayList<Category> resultCategories = MappingUtil.mapResponseToCategory(bookResponse);
                            categoryAdapter.updateData(resultCategories);
                            books.addAll(result);
                            loadFragment(BookListFragment.newInstance(books, MainActivity.this));
                        }
                    }

                    @Override
                    public void onFailure(Call<BookResponse> call, Throwable t) {
                        t.printStackTrace();
                        books.clear();
                    }
                });
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_list_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}