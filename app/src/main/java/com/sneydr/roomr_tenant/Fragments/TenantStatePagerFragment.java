package com.sneydr.roomr_tenant.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sneydr.roomr_tenant.Activities.LoginActivity;
import com.sneydr.roomrv2.Adapters.ViewPager2FragmentStateAdapter;
import com.sneydr.roomr_tenant.App.UI.DepthPageTransformer;
import com.sneydr.roomr_tenant.Entities.Users.Tenant;
import com.sneydr.roomr_tenant.Network.Observers.TenantObserver;
import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.ViewModels.TenantViewModel;
import com.sneydr.roomr_tenant.databinding.StatePagerTenantBinding;

public class TenantStatePagerFragment extends FragmentTemplate {




    private StatePagerTenantBinding binding;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.state_pager_tenant, container, false);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("authToken") && bundle.containsKey("houseId") && bundle.containsKey("email")){

            String authToken = bundle.getString("authToken");
            int houseId = bundle.getInt("houseId");
            String email = bundle.getString("email");
            FragmentStateAdapter adapter = setupViewPager(authToken, houseId, email);
            binding.tenantStatePager.setAdapter(adapter);
            binding.tenantStatePager.setPageTransformer(new DepthPageTransformer());
            new TabLayoutMediator(binding.tenantTabLayout, binding.tenantStatePager, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    binding.tenantStatePager.setCurrentItem(1);
                }
            }).attach();
            binding.tenantStatePager.setOffscreenPageLimit(adapter.getItemCount());
            binding.tenantTabLayout.getTabAt(1).setText("Home");
            binding.tenantTabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.ic_home_black_24dp));
            binding.tenantTabLayout.getTabAt(0).setText("Problems");
            binding.tenantTabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.ic_build_black_24dp));
            binding.tenantTabLayout.getTabAt(2).setText("Messages");
            binding.tenantTabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.ic_message_black_24dp));
        }
        else {
            NavHostFragment.findNavController(this).popBackStack();
        }
        return binding.getRoot();
    }


    private FragmentStateAdapter setupViewPager(String authToken, int houseId, String email){
        ViewPager2FragmentStateAdapter adapter = new ViewPager2FragmentStateAdapter(this);
        adapter.addFragment(new TenantProblemsFragment().setHouseId(houseId).setAuthToken(authToken).setEmail(email));
        adapter.addFragment(new TenantLandingFragment().setHouseId(houseId).setAuthToken(authToken).setEmail(email));
        adapter.addFragment(new TenantMessageFragment().setHouseId(houseId).setAuthToken(authToken));
        return adapter;
    }


}
