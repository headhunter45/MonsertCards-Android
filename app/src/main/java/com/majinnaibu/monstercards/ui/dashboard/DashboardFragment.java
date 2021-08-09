package com.majinnaibu.monstercards.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.majinnaibu.monstercards.databinding.FragmentDashboardBinding;
import com.majinnaibu.monstercards.models.Monster;
import com.majinnaibu.monstercards.ui.shared.MCFragment;
import com.majinnaibu.monstercards.utils.Logger;

import java.util.List;

public class DashboardFragment extends MCFragment {
    private DashboardViewModel mViewModel;
    private ViewHolder mHolder;
    private DashboardRecyclerViewAdapter mAdapter;

    private void navigateToMonsterDetail(Monster monster) {
        Navigation.findNavController(requireView()).navigate(
                DashboardFragmentDirections.actionNavigationDashboardToNavigationMonster(monster.id.toString()));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        FragmentDashboardBinding binding = FragmentDashboardBinding.inflate(inflater, container, false);
        mHolder = new ViewHolder(binding);

        setupRecyclerView(mHolder.list);

        return binding.getRoot();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        int columnCount = Math.max(1, (int) Math.floor(getResources().getConfiguration().screenWidthDp / 396.0f));
        Context context = requireContext();
        GridLayoutManager layoutManager = new GridLayoutManager(context, columnCount);
        recyclerView.setLayoutManager(layoutManager);

        LiveData<List<Monster>> monsterData = mViewModel.getMonsters();
        mAdapter = new DashboardRecyclerViewAdapter(monster -> {
            if (monster != null) {
                navigateToMonsterDetail(monster);
            } else {
                Logger.logError("Can't navigate to MonsterDetailFragment with a null monster");
            }
        });
        if (monsterData != null) {
            monsterData.observe(getViewLifecycleOwner(), monsters -> mAdapter.submitList(monsters));
        }
        recyclerView.setAdapter(mAdapter);
    }

    private static class ViewHolder {
        final RecyclerView list;

        ViewHolder(FragmentDashboardBinding binding) {
            list = binding.list;
        }
    }
}
