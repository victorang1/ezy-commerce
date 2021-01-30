package com.example.ezycommerce.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezycommerce.databinding.CategoryItemLayoutBinding;
import com.example.ezycommerce.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final ArrayList<Category> categories;
    private ICategoryAction mListener;

    public CategoryAdapter(ICategoryAction listener) {
        categories = new ArrayList<>();
        this.mListener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryItemLayoutBinding itemLayoutBinding = CategoryItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CategoryViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.bind(category);
        holder.itemLayoutBinding.getRoot().setOnClickListener(v -> {
            category.setSelected(!category.getSelected());
            int index = 0;
            for (Category c : categories) {
                if (c.getSelected() && index != position) {
                    c.setSelected(false);
                    break;
                }
                index++;
            }
            mListener.onCategoryClick(category);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void updateData(ArrayList<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
        notifyDataSetChanged();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private CategoryItemLayoutBinding itemLayoutBinding;

        public CategoryViewHolder(CategoryItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }

        private void bind(Category category) {
            itemLayoutBinding.setCategory(category);
        }
    }

    public interface ICategoryAction {
        void onCategoryClick(Category category);
    }
}
