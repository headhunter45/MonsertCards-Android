package com.majinnaibu.monstercards.ui.decks;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.majinnaibu.monstercards.databinding.SimpleListItemBinding;
import com.majinnaibu.monstercards.models.Deck;

import java.util.Objects;

public class DecksRecyclerViewAdapter extends ListAdapter<Deck, DecksRecyclerViewAdapter.ViewHolder> {
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
    private final ItemCallback mOnClick;

    protected DecksRecyclerViewAdapter(ItemCallback onClick) {
        super(DIFF_CALLBACK);
        mOnClick = onClick;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(SimpleListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mItem = getItem(position);
        holder.mContentView.setText(holder.mItem.name);
        holder.itemView.setOnClickListener(v -> {
            if (mOnClick != null) {
                mOnClick.onItemCallback(holder.mItem);
            }
        });
    }

    public interface ItemCallback {
        void onItemCallback(Deck collection);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mContentView;
        public Deck mItem;

        public ViewHolder(@NonNull SimpleListItemBinding binding) {
            super(binding.getRoot());
            mContentView = binding.content;
        }
    }
}
