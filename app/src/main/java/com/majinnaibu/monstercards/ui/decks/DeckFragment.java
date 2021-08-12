package com.majinnaibu.monstercards.ui.decks;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.majinnaibu.monstercards.databinding.FragmentDeckBinding;
import com.majinnaibu.monstercards.models.Monster;
import com.majinnaibu.monstercards.ui.shared.MCFragment;
import com.majinnaibu.monstercards.ui.shared.SwipeToDeleteCallback;
import com.majinnaibu.monstercards.utils.Logger;

import java.util.List;
import java.util.UUID;

public class DeckFragment extends MCFragment {
    // TODO: in onCreate the the deck ID, get the view model, and set the deck id in the view model
    private DeckViewModel mViewModel;
    private ViewHolder mHolder;
    private DeckRecyclerViewAdapter mAdapter;

    private void navigateToMonsterDetail(Monster monster) {
        Logger.logUnimplementedMethod();
        if (monster != null) {
//            Navigation.findNavController(requireView()).navigate(
//                    DeckFragmentDirections.actionNavigationDecksToMonsterDetailFragment(monster.id.toString()));
        } else {
            Logger.logWTF("Unable to navigate to a null Monster.");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DeckViewModel.class);
        Bundle arguments = getArguments();
        if (arguments != null) {
            try {
                DeckFragmentArgs args = DeckFragmentArgs.fromBundle(arguments);
                mViewModel.setDeckId(UUID.fromString(args.getDeckId()));
            } catch (IllegalArgumentException ex) {
                Logger.logWTF("DeckFragment passed an invalid deck ID.", ex);
                mViewModel.setDeckId(null);
            }
        } else {
            Logger.logWTF("DeckFragment needs arguments.");
            mViewModel.setDeckId(null);
        }
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DeckViewModel.class);
        FragmentDeckBinding binding = FragmentDeckBinding.inflate(inflater, container, false);
        mHolder = new ViewHolder(binding);

        setupAddMonsterButton(mHolder.addMonster);
        setupRecyclerView(mHolder.list);

        return binding.getRoot();
    }

    public void setupAddMonsterButton(@NonNull FloatingActionButton button) {
        button.setOnClickListener(v -> {
            Logger.logUnimplementedMethod();
        });
    }

    public void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Context context = requireContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        LiveData<List<Monster>> monsterData = mViewModel.getMonsters();
        mAdapter = new DeckRecyclerViewAdapter(this::navigateToMonsterDetail);
        if (monsterData != null) {
            monsterData.observe(getViewLifecycleOwner(), monsters -> mAdapter.submitList(monsters));
        }
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(context,
                (position, direction) -> mViewModel.removeMonster(position), (from, to) -> mViewModel.moveMonster(from, to)));
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private static class ViewHolder {
        final FloatingActionButton addMonster;
        final RecyclerView list;

        ViewHolder(FragmentDeckBinding binding) {
            addMonster = binding.addMonster;
            list = binding.list;
        }
    }

}
