package com.example.ezycommerce.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.ezycommerce.BR;

public class Book extends BaseObservable {

    private Integer id;
    private String name;
    private String description;
    private Float price;
    private String type;
    private String author;
    private String img;

    @Bindable
    public Integer getId() {
        return id;
    }

    public Book setId(Integer id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
        return this;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public Book setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
        return this;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public Book setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
        return this;
    }

    @Bindable
    public Float getPrice() {
        return price;
    }

    public Book setPrice(Float price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
        return this;
    }

    @Bindable
    public String getType() {
        return type;
    }

    public Book setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
        return this;
    }

    @Bindable
    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        notifyPropertyChanged(BR.author);
        return this;
    }

    @Bindable
    public String getImg() {
        return img;
    }

    public Book setImg(String img) {
        this.img = img;
        notifyPropertyChanged(BR.img);
        return this;
    }
}
