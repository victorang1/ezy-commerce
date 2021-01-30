package com.example.ezycommerce.ui.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ezycommerce.databinding.BookItemLayoutBinding;
import com.example.ezycommerce.model.Book;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder> {

    private final ArrayList<Book> books;
    private final Context context;
    private final IBookListAction mListener;

    public BookListAdapter(Context context, IBookListAction mListener) {
        this.books = new ArrayList<>();
        this.context = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookItemLayoutBinding itemLayoutBinding = BookItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new BookListViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bind(book, context);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void updateData(ArrayList<Book> books) {
        this.books.clear();
        this.books.addAll(books);
        notifyDataSetChanged();
    }

    static class BookListViewHolder extends RecyclerView.ViewHolder {

        private final BookItemLayoutBinding itemLayoutBinding;

        public BookListViewHolder(BookItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }

        public void bind(Book book, Context context) {
            itemLayoutBinding.setBook(book);
            Glide.with(context)
                    .load(book.getImg())
                    .into(itemLayoutBinding.ivPhoto);
        }
    }

    public interface IBookListAction {
        void onBookSelected(Book book);
    }
}
