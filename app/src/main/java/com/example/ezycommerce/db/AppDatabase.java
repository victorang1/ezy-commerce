package com.example.ezycommerce.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ezycommerce.model.Book;
import com.example.ezycommerce.model.Cart;

import java.util.ArrayList;

import static com.example.ezycommerce.db.DatabaseAccess.COLUMN_AUTHOR;
import static com.example.ezycommerce.db.DatabaseAccess.COLUMN_BOOK_ID;
import static com.example.ezycommerce.db.DatabaseAccess.COLUMN_CATEGORY;
import static com.example.ezycommerce.db.DatabaseAccess.COLUMN_DESCRIPTION;
import static com.example.ezycommerce.db.DatabaseAccess.COLUMN_ID;
import static com.example.ezycommerce.db.DatabaseAccess.COLUMN_IMG;
import static com.example.ezycommerce.db.DatabaseAccess.COLUMN_NAME;
import static com.example.ezycommerce.db.DatabaseAccess.COLUMN_PRICE;
import static com.example.ezycommerce.db.DatabaseAccess.COLUMN_QUANTITY;
import static com.example.ezycommerce.db.DatabaseAccess.COLUMN_TYPE;
import static com.example.ezycommerce.db.DatabaseAccess.TABLE_NAME;

public class AppDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EzyCommerce.db";
    private static final Integer DATABASE_VERSION = 1;

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new AppDatabase(context);
        }
        return instance;
    }

    public AppDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseAccess.createBookTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseAccess.deleteBookTable());
        onCreate(db);
    }

    public Boolean insertCart(Book book) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, book.getId());
            values.put(COLUMN_AUTHOR, book.getAuthor());
            values.put(COLUMN_CATEGORY, book.getCategory());
            values.put(COLUMN_DESCRIPTION, book.getDescription());
            values.put(COLUMN_IMG, book.getImg());
            values.put(COLUMN_NAME, book.getName());
            values.put(COLUMN_PRICE, book.getPrice());
            values.put(COLUMN_TYPE, book.getType());
            values.put(COLUMN_QUANTITY, "1");
            db.insert(TABLE_NAME, null, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Cart> getCarts() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(DatabaseAccess.getCartsQuery(), new String[]{});
        ArrayList<Cart> carts = new ArrayList<>();
        while (cursor.moveToNext()) {
            Cart cart = new Cart();
            cart.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_BOOK_ID)));
            cart.setAuthor(cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR)));
            cart.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
            cart.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
            cart.setImg(cursor.getString(cursor.getColumnIndex(COLUMN_IMG)));
            cart.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            cart.setPrice(cursor.getFloat(cursor.getColumnIndex(COLUMN_PRICE)));
            cart.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));
            cart.setQuantity(cursor.getString(cursor.getColumnIndex(COLUMN_QUANTITY)));
            carts.add(cart);
        }
        cursor.close();
        return carts;
    }

    public boolean resetCart() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, null, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
