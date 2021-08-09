package com.majinnaibu.monstercards.ui.decks;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.majinnaibu.monstercards.databinding.SimpleListItemBinding;
import com.majinnaibu.monstercards.helpers.ItemClickedCallback;
import com.majinnaibu.monstercards.models.Deck;
import com.majinnaibu.monstercards.ui.shared.SimpleListItemViewHolder;

import java.util.Objects;

public class DecksRecyclerViewAdapter extends ListAdapter<Deck, SimpleListItemViewHolder<Deck>> {
    private static final DiffUtil.ItemCallback<Deck> DIFF_CALLBACK = new DiffUtil.ItemCallback<Deck>() {
        @Override
        public boolean areItemsTheSame(@NonNull Deck oldItem, @NonNull Deck newItem) {
            return Objects.equals(oldItem.id, newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Deck oldItem, @NonNull Deck newItem) {
            return oldItem.equals(newItem);
        }
    };
    private final ItemClickedCallback<Deck> mOnItemClicked;

    protected DecksRecyclerViewAdapter(ItemClickedCallback<Deck> onItemClicked) {
        super(DIFF_CALLBACK);
        mOnItemClicked = onItemClicked;
    }

    @Override
    @NonNull
    public SimpleListItemViewHolder<Deck> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimpleListItemViewHolder<>(SimpleListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SimpleListItemViewHolder<Deck> holder, int position) {
        Deck deck = getItem(position);
        holder.item = deck;
        holder.content.setText(deck.name);
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClicked != null) {
                mOnItemClicked.onItemClicked(holder.item);
            }
        });
    }
}
