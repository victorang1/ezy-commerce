package com.example.ezycommerce.db;

public class DatabaseAccess {

    public static final String TABLE_NAME = "cart";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BOOK_ID = "book_id";
    public static final String COLUMN_IMG = "img";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_QUANTITY = "quantity";

    public static String createBookTableQuery() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_BOOK_ID + " INTEGER," +
                COLUMN_AUTHOR + " TEXT," +
                COLUMN_CATEGORY + " TEXT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_PRICE + " FLOAT," +
                COLUMN_QUANTITY + " TEXT," +
                COLUMN_TYPE + " TEXT," +
                COLUMN_IMG + " TEXT)";
    }

    public static String deleteBookTable() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static String getCartsQuery() {
        return "SELECT * FROM " + TABLE_NAME;
    }
}
