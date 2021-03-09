package com.vitor238.shoestore.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.vitor238.shoestore.data.model.Product;

public class DiffUtilsProduct extends DiffUtil.ItemCallback<Product> {

    @Override
    public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
        return oldItem.equals(newItem);
    }
}
