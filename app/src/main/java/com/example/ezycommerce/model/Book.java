package com.example.ezycommerce.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.ezycommerce.BR;

public class Book extends BaseObservable implements Parcelable {

    private Integer id;
    private String name;
    private String description;
    private Float price;
    private String type;
    private String author;
    private String img;
    private String category;

    public Book() {}

    public Book(Parcel in) {
        super();
        readFromParcel(in);
    }

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

    @Bindable
    public String getCategory() {
        return category;
    }

    public Book setCategory(String category) {
        this.category = category;
        notifyPropertyChanged(BR.category);
        return this;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        price = in.readFloat();
        type = in.readString();
        author = in.readString();
        img = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(description);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(price);
        }
        dest.writeString(type);
        dest.writeString(author);
        dest.writeString(img);
    }
}
