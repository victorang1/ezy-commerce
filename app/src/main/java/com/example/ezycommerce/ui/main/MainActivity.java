package com.example.ezycommerce.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ezycommerce.BuildConfig;
import com.example.ezycommerce.R;
import com.example.ezycommerce.databinding.ActivityMainBinding;
import com.example.ezycommerce.datamodel.BookItemResponse;
import com.example.ezycommerce.datamodel.BookResponse;
import com.example.ezycommerce.model.Book;
import com.example.ezycommerce.model.Category;
import com.example.ezycommerce.service.ApiClient;
import com.example.ezycommerce.ui.cart.CartActivity;
import com.example.ezycommerce.ui.detail.DetailActivity;
import com.example.ezycommerce.ui.detail.DetailFragment;
import com.example.ezycommerce.ui.list.BookListAdapter;
import com.example.ezycommerce.ui.list.BookListFragment;
import com.example.ezycommerce.util.MappingUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ezycommerce.ui.detail.DetailActivity.BOOK_ID;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.ICategoryAction, BookListAdapter.IBookListAction {

    private ActivityMainBinding binding;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        books = new ArrayList<>();
        initializeToolbar();
        initializeAdapter();
        loadData();
    }

    @Override
    public void onCategoryClick(Category category, Boolean isSelectedExists) {
        Fragment fragment;
        if (isSelectedExists) {
            ArrayList<Book> filteredList = new ArrayList<>();
            for (Book book : books) {
                if (book.getCategory().equals(category.getName())) {
                    filteredList.add(book);
                }
            }
            fragment = BookListFragment.newInstance(filteredList);
        }
        else {
            fragment = BookListFragment.newInstance(books);
        }
        loadFragment(fragment, true);
    }

    @Override
    public void onBookSelected(Book book) {
        if (binding.flDetailContainer == null) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(BOOK_ID, book.getId());
            startActivity(intent);
        }
        else {
            DetailFragment detail = new DetailFragment();
            detail.setSelectedBookId(book.getId());
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_detail_container, detail)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
        }
    }

    private void initializeToolbar() {
        setSupportActionBar(binding.toolbar);
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
                        binding.progressBar.setVisibility(View.GONE);
                        books.clear();
                        if (response.isSuccessful() && response.body() != null) {
                            ArrayList<BookItemResponse> bookResponse = response.body().getProducts();
                            ArrayList<Book> result = MappingUtil.mapResponseToBook(bookResponse);
                            ArrayList<Category> resultCategories = MappingUtil.mapResponseToCategory(bookResponse);
                            categoryAdapter.updateData(resultCategories);
                            books.addAll(result);
                            binding.tvUsername.setText(response.body().getUsername());
                            binding.rvCategories.setVisibility(View.VISIBLE);
                            loadFragment(BookListFragment.newInstance(books), false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BookResponse> call, Throwable t) {
                        binding.progressBar.setVisibility(View.GONE);
                        t.printStackTrace();
                        books.clear();
                    }
                });
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fl_list_container, fragment);
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null);
            }
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_cart:
                startActivity(new Intent(MainActivity.this, CartActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}