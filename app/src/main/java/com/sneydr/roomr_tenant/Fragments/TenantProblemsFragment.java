package com.sneydr.roomr_tenant.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sneydr.roomr_tenant.Adapters.ProblemsRecyclerViewAdapter;
import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Network.Observers.ProblemsObserver;
import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.ViewModels.ProblemViewModel;
import com.sneydr.roomr_tenant.databinding.FragmentTenantProblemBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TenantProblemsFragment extends FragmentTemplate implements ProblemsObserver, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    private ProblemsRecyclerViewAdapter adapter;
    private FragmentTenantProblemBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tenant_problem, container, false);
        binding.swrTenantProblems.setOnRefreshListener(this);
        binding.fabAddProblem.setOnClickListener(this);
        binding.rcyTenantProblems.setLayoutManager(new LinearLayoutManager(context));
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        ViewModelProviders.of(this).get(ProblemViewModel.class).loadProblems(houseId, authToken, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.swrTenantProblems.setRefreshing(false);
    }

    @Override
    public void onFailure(String tag, String response) {
        super.onFailure(tag, response);
        handler.post(new Runnable() {
            @Override
            public void run() {
                ProblemsRecyclerViewAdapter adapter = new ProblemsRecyclerViewAdapter(new ArrayList<>());
                binding.rcyTenantProblems.setAdapter(adapter);
                binding.swrTenantProblems.setRefreshing(false);
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onProblems(List<Problem> problems) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                binding.swrTenantProblems.setRefreshing(false);
                RecyclerView.LayoutManager layoutManager = problems.isEmpty() ? new LinearLayoutManager(context) : new GridLayoutManager(context, 2);

                binding.rcyTenantProblems.setLayoutManager(layoutManager);
                LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
                binding.rcyTenantProblems.setLayoutAnimation(animation);
                List<Problem> listToReverse = problems;
                Collections.reverse(listToReverse);
                adapter = new ProblemsRecyclerViewAdapter(listToReverse);
                binding.rcyTenantProblems.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onRefresh() {
        ViewModelProviders.of(this).get(ProblemViewModel.class).loadProblems(houseId, authToken, this);
    }

    @Override
    public void onClick(View v) {
        navigation.navigate(this, R.id.action_tenantStatePagerFragment_to_cameraFragment, authToken, houseId, email);
    }
}
