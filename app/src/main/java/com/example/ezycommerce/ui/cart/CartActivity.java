package com.example.ezycommerce.ui.cart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ezycommerce.R;
import com.example.ezycommerce.databinding.ActivityCartBinding;
import com.example.ezycommerce.db.AppDatabase;
import com.example.ezycommerce.model.Cart;
import com.example.ezycommerce.ui.main.MainActivity;
import com.example.ezycommerce.util.PriceUtil;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements CartAdapter.ICartAction, View.OnClickListener {

    private AppDatabase db;
    private ActivityCartBinding binding;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        binding.btnCheckout.setOnClickListener(this);
        db = AppDatabase.getInstance(this);
        initializeAdapter();
        loadData();
    }

    @Override
    public void updateTotal(Double subtotal, Double total) {
        binding.setSubtotal(String.format(getString(R.string.text_price_display), PriceUtil.displayPriceFormat(subtotal)));
        binding.setTotal(String.format(getString(R.string.text_price_display), PriceUtil.displayPriceFormat(total)));
    }

    @Override
    public void onClick(View v) {
        if (v.equals(binding.btnCheckout)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.text_information))
                .setMessage(getString(R.string.text_dialog_checkout))
                .setPositiveButton(getString(R.string.text_yes), (dialog, id) -> {
                    if (db.resetCart()) {
                        startActivity(new Intent(CartActivity.this, MainActivity.class));
                        finish();
                        Toast.makeText(CartActivity.this, getString(R.string.text_checkout_success), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(CartActivity.this, getString(R.string.text_checkout_failed), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(getString(R.string.text_no), (dialog, id) -> {
                    dialog.cancel();
                });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private void initializeAdapter() {
        cartAdapter = new CartAdapter(this, this);
        binding.rvCarts.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCarts.setAdapter(cartAdapter);
    }

    private void loadData() {
        ArrayList<Cart> carts = db.getCarts();
        if (carts.isEmpty()) {
            binding.content.setVisibility(View.GONE);
            binding.tvNoData.setVisibility(View.VISIBLE);
        }
        else {
            binding.content.setVisibility(View.VISIBLE);
            binding.tvNoData.setVisibility(View.GONE);
            cartAdapter.updateData(carts);
        }
    }
}