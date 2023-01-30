package com.pondoku.pondoku.sharing;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.pondoku.pondoku.Habit;
import com.pondoku.pondoku.R;
import com.pondoku.pondoku.databinding.ActivitySharingHabitBinding;
import com.pondoku.pondoku.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class SharingHabitActivity extends AppCompatActivity {


    private ActivitySharingHabitBinding binding;
    private SharingHabitViewModel mViewModel;
    private SharingHabitListAdapter mAdapter;
    private List<Habit> mHabitList;
    private FollowingEntity mFollowing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // creates the activity view
        binding = ActivitySharingHabitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        startObserve();
    }

    /**
     * initilze the basic View of following's habits
     */
    @SuppressLint("SetTextI18n")
    private void initView() {
        Intent intent = getIntent();
        mFollowing = (FollowingEntity) intent.getSerializableExtra(Constants.FOLLOWING_INFO);
        mHabitList = new ArrayList<>();
        mViewModel = new ViewModelProvider(this).get(SharingHabitViewModel.class);
        mAdapter = new SharingHabitListAdapter(this, mFollowing, mHabitList);
        binding.back.setOnClickListener(view -> {
            finish();
        });
        binding.name.setText(mFollowing.getName());
//        binding.uid.setText(getResources().getString(R.string.uid) + mFollowing.getUid());
        binding.habitsList.setAdapter(mAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void startObserve() {
        mViewModel.habits.observe(this, list -> {
            mHabitList.clear();
            mHabitList.addAll(list);
            mAdapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.getFollowerHabits(mFollowing.getUid());
    }


}