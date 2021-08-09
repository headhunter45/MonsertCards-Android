package com.majinnaibu.monstercards.ui.editmonster;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.majinnaibu.monstercards.R;
import com.majinnaibu.monstercards.databinding.FragmentEditSkillsListBinding;
import com.majinnaibu.monstercards.models.Skill;
import com.majinnaibu.monstercards.ui.shared.MCFragment;
import com.majinnaibu.monstercards.ui.shared.SwipeToDeleteCallback;
import com.majinnaibu.monstercards.utils.Logger;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class EditSkillsFragment extends MCFragment {
    private EditMonsterViewModel mViewModel;
    private ViewHolder mHolder;
    private EditSkillsRecyclerViewAdapter mAdapter;

    private void navigateToEditSkill(Skill skill) {
        Navigation.findNavController(requireView()).navigate(
                EditSkillsFragmentDirections.actionEditSkillsFragmentToEditSkillFragment(skill.name, skill.abilityScore, skill.proficiencyType, skill.advantageType));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        NavBackStackEntry backStackEntry = navController.getBackStackEntry(R.id.edit_monster_navigation);
        mViewModel = new ViewModelProvider(backStackEntry).get(EditMonsterViewModel.class);
        FragmentEditSkillsListBinding binding = FragmentEditSkillsListBinding.inflate(inflater, container, false);
        mHolder = new ViewHolder(binding);
        setupRecyclerView(mHolder.list);
        setupAddSkillButton(mHolder.addSkill);
        return binding.getRoot();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Context context = requireContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        LiveData<List<Skill>> skillsData = mViewModel.getSkills();
        mAdapter = new EditSkillsRecyclerViewAdapter(skill -> {
            if (skill != null) {
                navigateToEditSkill(skill);
            } else {
                Logger.logError("Can't navigate to EditSkill with a null skill");
            }
        });
        if (skillsData != null) {
            skillsData.observe(getViewLifecycleOwner(), skills -> mAdapter.submitList(skills));
        }
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(context, (position, direction) -> mViewModel.removeSkill(position), null));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void setupAddSkillButton(@NonNull FloatingActionButton addSkillsButton) {
        addSkillsButton.setOnClickListener(view -> {
            Skill newSkill = mViewModel.addNewSkill();
            navigateToEditSkill(newSkill);
        });
    }

    private static class ViewHolder {
        RecyclerView list;
        FloatingActionButton addSkill;

        ViewHolder(FragmentEditSkillsListBinding binding) {
            this.list = binding.list;
            this.addSkill = binding.addSkill;
        }
    }
}
