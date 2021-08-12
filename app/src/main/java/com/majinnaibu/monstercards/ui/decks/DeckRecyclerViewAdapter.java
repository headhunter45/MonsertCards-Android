package com.majinnaibu.monstercards.ui.decks;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.majinnaibu.monstercards.databinding.FragmentSimpleListItemBinding;
import com.majinnaibu.monstercards.helpers.ItemClickedCallback;
import com.majinnaibu.monstercards.models.Monster;
import com.majinnaibu.monstercards.ui.shared.SimpleListItemViewHolder;

import java.util.Objects;

public class DeckRecyclerViewAdapter extends ListAdapter<Monster, SimpleListItemViewHolder<Monster>> {
    private static final DiffUtil.ItemCallback<Monster> DIFF_CALLBACK = new DiffUtil.ItemCallback<Monster>() {
        @Override
        public boolean areItemsTheSame(@NonNull Monster oldItem, @NonNull Monster newItem) {
            return Objects.equals(oldItem.id, newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Monster oldItem, @NonNull Monster newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };
    private final ItemClickedCallback<Monster> mOnItemClicked;

    protected DeckRecyclerViewAdapter(ItemClickedCallback<Monster> onItemClicked) {
        super(DIFF_CALLBACK);
        mOnItemClicked = onItemClicked;
    }

    @NonNull
    @Override
    public SimpleListItemViewHolder<Monster> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentSimpleListItemBinding binding = FragmentSimpleListItemBinding.inflate(inflater, parent, false);
        return new SimpleListItemViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleListItemViewHolder<Monster> holder, int position) {
        Monster item = getItem(position);
        holder.item = item;
        holder.content.setText(item.name);
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClicked != null) {
                mOnItemClicked.onItemClicked(holder.item);
            }
        });
    }
}
