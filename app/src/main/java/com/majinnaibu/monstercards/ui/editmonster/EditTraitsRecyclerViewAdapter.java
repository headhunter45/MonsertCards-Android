package com.majinnaibu.monstercards.ui.editmonster;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.majinnaibu.monstercards.databinding.SimpleListItemBinding;
import com.majinnaibu.monstercards.helpers.ItemClickedCallback;
import com.majinnaibu.monstercards.models.Trait;
import com.majinnaibu.monstercards.ui.shared.SimpleListItemViewHolder;

import org.jetbrains.annotations.NotNull;

public class EditTraitsRecyclerViewAdapter extends ListAdapter<Trait, SimpleListItemViewHolder<Trait>> {
    private static final DiffUtil.ItemCallback<Trait> DIFF_CALLBACK = new DiffUtil.ItemCallback<Trait>() {

        @Override
        public boolean areItemsTheSame(@NonNull Trait oldItem, @NonNull Trait newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Trait oldItem, @NonNull Trait newItem) {
            return oldItem.equals(newItem);
        }
    };
    private final ItemClickedCallback<Trait> mOnItemClicked;

    protected EditTraitsRecyclerViewAdapter(ItemClickedCallback<Trait> onItemClicked) {
        super(DIFF_CALLBACK);
        mOnItemClicked = onItemClicked;
    }

    @NonNull
    @Override
    public SimpleListItemViewHolder<Trait> onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new SimpleListItemViewHolder<>(SimpleListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final SimpleListItemViewHolder<Trait> holder, int position) {
        Trait item = getItem(position);
        holder.item = item;
        holder.content.setText(item.name);
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClicked != null) {
                mOnItemClicked.onItemClicked(holder.item);
            }
        });
    }
}
