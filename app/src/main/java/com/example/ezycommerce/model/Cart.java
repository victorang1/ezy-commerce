package com.example.ezycommerce.model;

import androidx.databinding.Bindable;

import com.example.ezycommerce.BR;

public class Cart extends Book {

    private String quantity;
    private Boolean isError;

    @Bindable
    public String getQuantity() {
        return quantity;
    }

    public Cart setQuantity(String quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(BR.quantity);
        return this;
    }

    @Bindable
    public Boolean getError() {
        return isError;
    }

    public Cart setError(Boolean error) {
        isError = error;
        notifyPropertyChanged(BR.error);
        return this;
    }
}
