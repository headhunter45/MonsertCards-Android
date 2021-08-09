package com.majinnaibu.monstercards.ui.editmonster;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.majinnaibu.monstercards.data.enums.StringType;
import com.majinnaibu.monstercards.databinding.FragmentEditStringsListBinding;
import com.majinnaibu.monstercards.ui.shared.MCFragment;
import com.majinnaibu.monstercards.ui.shared.SwipeToDeleteCallback;
import com.majinnaibu.monstercards.utils.Logger;

import java.util.List;

public class EditStringsFragment extends MCFragment {
    private EditMonsterViewModel mViewModel;
    private ViewHolder mHolder;
    private EditStringsRecyclerViewAdapter mAdapter;
    private StringType mStringType;

    protected void navigateToEditString(String value) {
        Navigation.findNavController(requireView()).navigate(
                EditStringsFragmentDirections.actionEditStringsFragmentToEditStringFragment(mStringType, value));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            EditStringsFragmentArgs args = EditStringsFragmentArgs.fromBundle(arguments);
            mStringType = args.getStringType();
        } else {
            Logger.logWTF("EditStringsFragment needs arguments");
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        NavBackStackEntry backStackEntry = navController.getBackStackEntry(R.id.edit_monster_navigation);
        mViewModel = new ViewModelProvider(backStackEntry).get(EditMonsterViewModel.class);
        FragmentEditStringsListBinding binding = FragmentEditStringsListBinding.inflate(inflater, container, false);
        mHolder = new ViewHolder(binding);
        setTitle(getTitleForStringType(mStringType));
        setupRecyclerView(mHolder.list);
        setupAddButton(mHolder.addItem);
        return binding.getRoot();
    }

    @NonNull
    private String getTitleForStringType(StringType type) {
        switch (type) {
            case CONDITION_IMMUNITY:
                return getString(R.string.title_editConditionImmunities);
            case DAMAGE_IMMUNITY:
                return getString(R.string.title_editDamageImmunities);
            case DAMAGE_RESISTANCE:
                return getString(R.string.title_editDamageResistances);
            case DAMAGE_VULNERABILITY:
                return getString(R.string.title_editDamageVulnerabilities);
            case SENSE:
                return getString(R.string.title_editSenses);
            default:
                return getString(R.string.title_editStrings);
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Context context = requireContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        LiveData<List<String>> stringsData = mViewModel.getStrings(mStringType);
        mAdapter = new EditStringsRecyclerViewAdapter(value -> {
            if (value != null) {
                navigateToEditString(value);
            } else {
                Logger.logError("Can't navigate to EditStringFragment with a null trait");
            }
        });
        if (stringsData != null) {
            stringsData.observe(getViewLifecycleOwner(), strings -> mAdapter.submitList(strings));
        }
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(context, (position, direction) -> mViewModel.removeString(mStringType, position), null));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void setupAddButton(@NonNull FloatingActionButton addStringButton) {
        addStringButton.setOnClickListener(view -> {
            String newValue = mViewModel.addNewString(mStringType);
            if (newValue != null) {
                navigateToEditString(newValue);
            }
        });
    }

    private static class ViewHolder {
        RecyclerView list;
        FloatingActionButton addItem;

        ViewHolder(FragmentEditStringsListBinding binding) {
            list = binding.list;
            addItem = binding.addItem;
        }
    }
}
