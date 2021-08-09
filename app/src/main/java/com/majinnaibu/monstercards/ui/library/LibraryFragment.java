package com.majinnaibu.monstercards.ui.library;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.majinnaibu.monstercards.databinding.FragmentLibraryBinding;
import com.majinnaibu.monstercards.models.Monster;
import com.majinnaibu.monstercards.ui.shared.MCFragment;
import com.majinnaibu.monstercards.ui.shared.SwipeToDeleteCallback;

import java.util.List;

public class LibraryFragment extends MCFragment {
    private LibraryViewModel mViewModel;
    private ViewHolder mHolder;
    private LibraryRecyclerViewAdapter mAdapter;

    private void navigateToEditMonster(Monster monster) {
        Navigation.findNavController(requireView()).navigate(
                LibraryFragmentDirections.actionNavigationLibraryToNavigationMonster(monster.id.toString()));
    }

    private void navigateToMonsterDetail(Monster monster) {
        Navigation.findNavController(requireView()).navigate(
                LibraryFragmentDirections.actionNavigationLibraryToNavigationMonster(monster.id.toString()));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(LibraryViewModel.class);
        FragmentLibraryBinding binding = FragmentLibraryBinding.inflate(inflater, container, false);
        mHolder = new ViewHolder(binding);
        // TODO: set the title with setTitle(...)
        setupAddMonsterButton(mHolder.addButton);
        setupMonsterList(mHolder.list);
        return binding.getRoot();
    }

    private void setupAddMonsterButton(@NonNull FloatingActionButton button) {
        button.setOnClickListener(v -> {
            Monster newMonster = mViewModel.addNewMonster();
            if (newMonster != null) {
                navigateToEditMonster(newMonster);
            }
        });
    }

    private void setupMonsterList(@NonNull RecyclerView recyclerView) {
        Context context = requireContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        LiveData<List<Monster>> monsterData = mViewModel.getMonsters();
        mAdapter = new LibraryRecyclerViewAdapter(this::navigateToMonsterDetail);
        if (monsterData != null) {
            monsterData.observe(getViewLifecycleOwner(), monsters -> mAdapter.submitList(monsters));
        }
        recyclerView.setAdapter(mAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(
                requireContext(),
                (position, direction) -> mViewModel.removeMonster(position),
                null));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private static class ViewHolder {
        final FloatingActionButton addButton;
        final RecyclerView list;

        public ViewHolder(FragmentLibraryBinding binding) {
            addButton = binding.fab;
            list = binding.monsterList;
        }
    }
}
