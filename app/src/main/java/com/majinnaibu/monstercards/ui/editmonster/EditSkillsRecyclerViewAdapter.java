package com.majinnaibu.monstercards.ui.editmonster;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.majinnaibu.monstercards.databinding.SimpleListItemBinding;
import com.majinnaibu.monstercards.helpers.ItemClickedCallback;
import com.majinnaibu.monstercards.models.Skill;
import com.majinnaibu.monstercards.ui.shared.SimpleListItemViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Skill}.
 */
public class EditSkillsRecyclerViewAdapter extends ListAdapter<Skill, SimpleListItemViewHolder<Skill>> {
    private static final DiffUtil.ItemCallback<Skill> DIFF_CALLBACK = new DiffUtil.ItemCallback<Skill>() {
        @Override
        public boolean areItemsTheSame(@NonNull Skill oldItem, @NonNull Skill newItem) {
            return Objects.equals(oldItem.name, newItem.name);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Skill oldItem, @NonNull Skill newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };
    private final ItemClickedCallback<Skill> mOnItemClicked;

    public EditSkillsRecyclerViewAdapter(ItemClickedCallback<Skill> onItemClicked) {
        super(DIFF_CALLBACK);
        mOnItemClicked = onItemClicked;
    }

    @NonNull
    @Override
    public SimpleListItemViewHolder<Skill> onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new SimpleListItemViewHolder<>(SimpleListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final SimpleListItemViewHolder<Skill> holder, int position) {
        Skill item = getItem(position);
        holder.item = item;
        holder.content.setText(item.name);
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClicked != null) {
                mOnItemClicked.onItemClicked(holder.item);
            }
        });
    }
}
