package com.example.ezycommerce.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ezycommerce.databinding.FragmentBookListBinding;
import com.example.ezycommerce.model.Book;

import java.util.ArrayList;

public class BookListFragment extends Fragment {

    public BookListFragment() {}

    private BookListFragment(ArrayList<Book> books, BookListAdapter.IBookListAction mListener) {
        this.books = books;
        this.mListener = mListener;
    }

    public static BookListFragment newInstance(ArrayList<Book> books, BookListAdapter.IBookListAction mListener) {
       return new BookListFragment(books, mListener);
    }

    private ArrayList<Book> books;
    private BookListAdapter.IBookListAction mListener;
    private FragmentBookListBinding binding;
    private BookListAdapter bookListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBookListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeAdapter();
    }

    private void initializeAdapter() {
        bookListAdapter = new BookListAdapter(requireContext(), mListener);
        binding.rvBooks.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvBooks.setAdapter(bookListAdapter);
        bookListAdapter.updateData(books);
    }
}
