package com.pondoku.pondoku.sharing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pondoku.pondoku.MainApplication;
import com.pondoku.pondoku.R;
import com.pondoku.pondoku.databinding.FragmentSharingBinding;

import java.util.ArrayList;
import java.util.List;


public class SharingFragment extends Fragment {


    private SharingViewModel mViewModel;
    private FragmentSharingBinding binding;
    private SharingListAdapter mAdapter;
    private List<FollowingEntity> mFollowingList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSharingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        startObserve();
    }

    /**
     * initilze the basic View
     */
    private void initView() {
        mFollowingList = new ArrayList<>();
        mAdapter = new SharingListAdapter(getContext(), mFollowingList);
        mViewModel = new ViewModelProvider(this).get(SharingViewModel.class);
        binding.userList.setAdapter(mAdapter);
        binding.searchBar.onActionViewExpanded();
        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mViewModel.getFollowing(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mViewModel.getFollowing(newText);
                return false;
            }
        });
        binding.searchBar.setOnCloseListener((SearchView.OnCloseListener) () -> {
            mViewModel.getFollowing(null);
            return false;
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void startObserve() {
        mViewModel.following.observe(getViewLifecycleOwner(), list -> {
            mFollowingList.clear();
            mFollowingList.addAll(list);
            mAdapter.notifyDataSetChanged();
        });
    }
    /**
     * On resume of the fragment, get the following data
     */
    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getFollowing(null);
    }

    public Context getContext() {
        if (getActivity() == null) {
            return MainApplication.getContext();
        }
        return getActivity();
    }
    /**
     * On stop of the fragment, edit the order
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}