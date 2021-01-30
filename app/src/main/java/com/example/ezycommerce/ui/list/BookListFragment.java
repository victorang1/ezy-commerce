package com.example.ezycommerce.ui.list;

import android.content.Context;
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

    public static final String BOOKS = "books";

    public static BookListFragment newInstance(ArrayList<Book> books) {

        Bundle args = new Bundle();
        args.putParcelableArrayList(BOOKS, books);
        BookListFragment fragment = new BookListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ArrayList<Book> books;
    private BookListAdapter.IBookListAction mListener;
    private FragmentBookListBinding binding;
    private BookListAdapter bookListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBookListBinding.inflate(inflater, container, false);
        if (getArguments() != null) {
            books = getArguments().getParcelableArrayList(BOOKS);
        }
        else books = new ArrayList<>();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeAdapter();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mListener = (BookListAdapter.IBookListAction) context;
    }

    private void initializeAdapter() {
        bookListAdapter = new BookListAdapter(requireContext(), mListener);
        binding.rvBooks.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvBooks.setAdapter(bookListAdapter);
        bookListAdapter.updateData(books);
    }
}
