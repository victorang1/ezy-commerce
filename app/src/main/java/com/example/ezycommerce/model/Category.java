package com.example.ezycommerce.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.ezycommerce.BR;

public class Category extends BaseObservable {

    private String name;
    private Boolean selected;

    public Category(String name) {
        this.name = name;
        this.selected = false;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
        return this;
    }

    @Bindable
    public Boolean getSelected() {
        return selected;
    }

    public Category setSelected(Boolean selected) {
        this.selected = selected;
        notifyPropertyChanged(BR.selected);
        return this;
    }
}
