package com.majinnaibu.monstercards.ui.editmonster;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.majinnaibu.monstercards.databinding.SimpleListItemBinding;
import com.majinnaibu.monstercards.helpers.ItemClickedCallback;
import com.majinnaibu.monstercards.ui.shared.SimpleListItemViewHolder;

import org.jetbrains.annotations.NotNull;

public class EditStringsRecyclerViewAdapter extends ListAdapter<String, SimpleListItemViewHolder<String>> {
    private static final DiffUtil.ItemCallback<String> DIFF_CALLBACK = new DiffUtil.ItemCallback<String>() {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }
    };
    private final ItemClickedCallback<String> mOnItemClicked;

    public EditStringsRecyclerViewAdapter(ItemClickedCallback<String> onItemClicked) {
        super(DIFF_CALLBACK);
        mOnItemClicked = onItemClicked;
    }

    @Override
    @NotNull
    public SimpleListItemViewHolder<String> onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new SimpleListItemViewHolder<>(SimpleListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final SimpleListItemViewHolder<String> holder, int position) {
        String item = getItem(position);
        holder.item = item;
        holder.content.setText(item);
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClicked != null) {
                mOnItemClicked.onItemClicked(holder.item);
            }
        });
    }
}
