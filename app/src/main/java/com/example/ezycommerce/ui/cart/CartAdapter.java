package com.example.ezycommerce.ui.cart;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ezycommerce.databinding.CartItemLayoutBinding;
import com.example.ezycommerce.model.Cart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final ArrayList<Cart> carts;
    private final Context mContext;
    private final ICartAction mListener;

    public CartAdapter(Context context, ICartAction mListener) {
        this.carts = new ArrayList<>();
        this.mContext = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemLayoutBinding itemLayoutBinding = CartItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CartViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = carts.get(position);
        holder.bind(cart, mContext);
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public void updateData(ArrayList<Cart> carts) {
        this.carts.clear();
        this.carts.addAll(carts);
        notifyDataSetChanged();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {

        private final CartItemLayoutBinding itemLayoutBinding;

        public CartViewHolder(CartItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }

        public void bind(Cart cart, Context context) {
            itemLayoutBinding.setCart(cart);
            itemLayoutBinding.etQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    updateValue();
                }
            });
            Glide.with(context)
                    .load(cart.getImg())
                    .into(itemLayoutBinding.ivPhoto);
        }

        private void updateValue() {
            double subtotal = 0.0;
            double total = 0.0;
            for (Cart cart : carts) {
                try {
                    if (Integer.parseInt(cart.getQuantity()) <= 0) {
                        cart.setError(true);
                        continue;
                    }
                    else cart.setError(false);
                } catch (Exception e) {
                    cart.setError(true);
                    continue;
                }
                subtotal += (cart.getPrice() * Double.parseDouble(cart.getQuantity()));
            }
            total += (subtotal + 10);

            mListener.updateTotal(subtotal, total);
        }
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public interface ICartAction {
        void updateTotal(Double subtotal, Double total);
    }
}
