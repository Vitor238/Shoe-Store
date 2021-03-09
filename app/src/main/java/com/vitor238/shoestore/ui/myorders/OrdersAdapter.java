package com.vitor238.shoestore.ui.myorders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vitor238.shoestore.R;
import com.vitor238.shoestore.data.model.Product;
import com.vitor238.shoestore.databinding.ItemProductBinding;

public class OrdersAdapter extends ListAdapter<Product, OrdersAdapter.ViewHolder> {


    protected OrdersAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback) {
        super(diffCallback);
    }

    protected OrdersAdapter(@NonNull AsyncDifferConfig<Product> config) {
        super(config);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = getItem(position);
        holder.binding(product);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagePhoto;
        TextView textPrice;
        TextView textName;

        public ViewHolder(@NonNull ItemProductBinding itemView) {
            super(itemView.getRoot());

            imagePhoto = itemView.imagePhoto;
            textPrice = itemView.textPrice;
            textName = itemView.textName;

        }

        public void binding(Product product) {

            Glide.with(imagePhoto.getContext()).load(product.getPhotoUrl())
                    .into(imagePhoto);

            Float price = product.getPrice().floatValue();
            textPrice.setText(textPrice.getContext().getString(R.string.price_value, price));
            textName.setText(textName.getContext().getString(R.string.product_name,
                    product.getBrand(),
                    product.getName()));
        }
    }

}
