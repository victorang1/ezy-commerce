package com.example.ezycommerce.util;

import com.example.ezycommerce.datamodel.BookItemResponse;
import com.example.ezycommerce.model.Book;
import com.example.ezycommerce.model.Category;

import java.util.ArrayList;

public class MappingUtil {

    public static ArrayList<Book> mapResponseToBook(ArrayList<BookItemResponse> responses) {
        ArrayList<Book> books = new ArrayList<>();
        for (BookItemResponse itemResponse : responses) {
            Book book = new Book()
                    .setId(itemResponse.getId())
                    .setName(itemResponse.getName())
                    .setDescription(itemResponse.getDescription())
                    .setAuthor(itemResponse.getAuthor())
                    .setImg(itemResponse.getImg())
                    .setPrice(itemResponse.getPrice())
                    .setType(itemResponse.getType());
            books.add(book);
        }
        return books;
    }

    public static ArrayList<Category> mapResponseToCategory(ArrayList<BookItemResponse> responses) {
        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        for (BookItemResponse itemResponse : responses) {
            if (!temp.contains(itemResponse.getType())) {
                temp.add(itemResponse.getType());
                categories.add(new Category(itemResponse.getType()));
            }
        }
        return categories;
    }
}
