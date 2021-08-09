package com.majinnaibu.monstercards.ui.shared;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.majinnaibu.monstercards.databinding.SimpleListItemBinding;


public class SimpleListItemViewHolder<T> extends RecyclerView.ViewHolder {
    public final TextView content;
    public T item;

    public SimpleListItemViewHolder(@NonNull SimpleListItemBinding binding) {
        super(binding.getRoot());
        item = null;
        content = binding.content;
    }
}
