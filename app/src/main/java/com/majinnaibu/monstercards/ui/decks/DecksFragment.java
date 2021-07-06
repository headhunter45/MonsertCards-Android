package com.majinnaibu.monstercards.ui.decks;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.majinnaibu.monstercards.databinding.FragmentDecksBinding;
import com.majinnaibu.monstercards.models.Deck;
import com.majinnaibu.monstercards.ui.shared.MCFragment;
import com.majinnaibu.monstercards.ui.shared.SwipeToDeleteCallback;
import com.majinnaibu.monstercards.utils.Logger;

import java.util.List;

public class DecksFragment extends MCFragment {
    private DecksViewModel mViewModel;
    private ViewHolder mHolder;
    private DecksRecyclerViewAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DecksViewModel.class);
        FragmentDecksBinding binding = FragmentDecksBinding.inflate(inflater, container, false);
        mHolder = new ViewHolder(binding);

        setupAddDeckButton(mHolder.addDeck);
        setupRecyclerView(mHolder.list);

        return binding.getRoot();
    }

    private void setupAddDeckButton(FloatingActionButton button) {
        button.setOnClickListener(v -> {
            Deck newDeck = mViewModel.addNewDeck();
            if (newDeck != null) {
                navigateToDeck(newDeck);
            }
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        Logger.logUnimplementedMethod();
        Context context = requireContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        LiveData<List<Deck>> deckData = mViewModel.getDecks();
        mAdapter = new DecksRecyclerViewAdapter(deck -> {
            if (deck != null) {
                navigateToDeck(deck);
            } else {
                Logger.logError("Can't navigate to DeckFragment with a null deck.");
            }
        });
        if (deckData != null) {
            deckData.observe(getViewLifecycleOwner(), decks -> mAdapter.submitList(decks));
        }
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(context,
                (position, direction) -> mViewModel.removeDeck(position), null));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void navigateToDeck(Deck deck) {
        Logger.logUnimplementedMethod();
    }

    private static class ViewHolder {
        final FloatingActionButton addDeck;
        final RecyclerView list;

        ViewHolder(FragmentDecksBinding binding) {
            addDeck = binding.addDeck;
            list = binding.list;
        }
    }
}
