package com.example.ezycommerce.ui.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.ezycommerce.BuildConfig;
import com.example.ezycommerce.R;
import com.example.ezycommerce.databinding.FragmentDetailBinding;
import com.example.ezycommerce.datamodel.BookResponse;
import com.example.ezycommerce.db.AppDatabase;
import com.example.ezycommerce.model.Book;
import com.example.ezycommerce.service.ApiClient;
import com.example.ezycommerce.util.MappingUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFragment extends Fragment implements View.OnClickListener {

    private FragmentDetailBinding binding;
    private Integer selectedBookId;
    private AppDatabase appDatabase;
    private Book result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        appDatabase = AppDatabase.getInstance(requireContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBuy.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadBookDataById();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(binding.btnBuy)) {
            if (appDatabase.insertCart(result)) {
                Toast.makeText(requireContext(), getString(R.string.text_add_success), Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(requireContext(), getString(R.string.text_add_failed), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadBookDataById() {
        ApiClient.service().getBookById(selectedBookId, BuildConfig.USER_ID, BuildConfig.USERNAME)
                .enqueue(new Callback<BookResponse>() {
                    @Override
                    public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            result = MappingUtil.mapResponseToBook(response.body().getProducts().get(0));
                            binding.setBook(result);
                            Glide.with(requireContext())
                                    .load(result.getImg())
                                    .into(binding.ivPhoto);
                        }
                        else {
                            Log.d("<RESULT>", "onResponse FAILED: ");
                        }
                    }

                    @Override
                    public void onFailure(Call<BookResponse> call, Throwable t) {
                        t.printStackTrace();
                        Log.d("<RESULT>", "onFailure: ");
                    }
                });
    }

    public void setSelectedBookId(Integer selectedBookId) {
        this.selectedBookId = selectedBookId;
    }
}
