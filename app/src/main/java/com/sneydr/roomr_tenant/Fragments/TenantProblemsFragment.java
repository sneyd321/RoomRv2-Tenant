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
    private int houseId;
    private String authToken;
    private ProblemViewModel problemViewModel;
    private FragmentTenantProblemBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tenant_problem, container, false);
        binding.swrTenantProblems.setOnRefreshListener(this);
        binding.fabAddProblem.setOnClickListener(this);
        binding.rcyTenantProblems.setLayoutManager(new LinearLayoutManager(getActivity()));

        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        problemViewModel = ViewModelProviders.of(this).get(ProblemViewModel.class);
        problemViewModel.loadProblems(houseId, authToken, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        adapter = new ProblemsRecyclerViewAdapter(new ArrayList<>());
        binding.rcyTenantProblems.setAdapter(adapter);
    }

    @Override
    public void onFailure(String tag, String response) {
        super.onFailure(tag, response);
        binding.swrTenantProblems.setRefreshing(false);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onProblems(List<Problem> problems) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (binding.swrTenantProblems.isRefreshing()){
                    binding.swrTenantProblems.setRefreshing(false);
                }
                if (problems.isEmpty()){
                    binding.rcyTenantProblems.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
                else {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
                    binding.rcyTenantProblems.setLayoutManager(gridLayoutManager);
                }
                LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
                binding.rcyTenantProblems.setLayoutAnimation(animation);
                Collections.reverse(problems);
                adapter = new ProblemsRecyclerViewAdapter(problems);
                binding.rcyTenantProblems.swapAdapter(adapter, false);
            }
        });
    }


    public TenantProblemsFragment setAuthToken(String authToken) {
        this.authToken = authToken;
        return this;
    }

    public TenantProblemsFragment setHouseId(int id) {
        this.houseId = id;
        return this;
    }

    @Override
    public void onRefresh() {
        problemViewModel.loadProblems(houseId, authToken, this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("authToken", authToken);
        bundle.putInt("houseId", houseId);
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_tenantStatePagerFragment_to_cameraFragment, bundle);
    }
}
