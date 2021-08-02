package com.sneydr.roomr_tenant.Fragments;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Toast;

import com.sneydr.roomr_tenant.Activities.MainActivityTenant;
import com.sneydr.roomr_tenant.Adapters.Listeners.ItemClickListener;
import com.sneydr.roomr_tenant.Adapters.TenantDocumentRecyclerViewAdapter;
import com.sneydr.roomr_tenant.App.Button.AddPhotoButton;
import com.sneydr.roomr_tenant.App.Button.OpenCalendarButton;
import com.sneydr.roomr_tenant.App.Button.SaveClipboardButton;
import com.sneydr.roomr_tenant.App.UI.CircleTransform;
import com.sneydr.roomr_tenant.App.Constants;
import com.sneydr.roomr_tenant.App.UI.CircularProgressBarAnimation;
import com.sneydr.roomr_tenant.Entities.House.Document;
import com.sneydr.roomr_tenant.Entities.RentDetails.RentDetails;
import com.sneydr.roomr_tenant.Entities.Users.Homeowner;
import com.sneydr.roomr_tenant.Entities.Users.Tenant;
import com.sneydr.roomr_tenant.Network.Observers.ActivityObserver;
import com.sneydr.roomr_tenant.Network.Observers.DocumentsObserver;
import com.sneydr.roomr_tenant.Network.Observers.HomeownerObserver;
import com.sneydr.roomr_tenant.Network.Observers.HouseObserver;
import com.sneydr.roomr_tenant.Entities.House.House;
import com.sneydr.roomr_tenant.Network.Observers.RentDetailsObserver;
import com.sneydr.roomr_tenant.Network.Observers.TenantObserver;
import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.ViewModels.DocumentViewModel;
import com.sneydr.roomr_tenant.ViewModels.HomeownerViewModel;
import com.sneydr.roomr_tenant.ViewModels.HouseViewModel;
import com.sneydr.roomr_tenant.ViewModels.RentDetailsViewModel;
import com.sneydr.roomr_tenant.ViewModels.TenantViewModel;
import com.sneydr.roomr_tenant.databinding.FragmentTenantLandingPageBinding;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;


public class TenantLandingFragment extends FragmentTemplate implements ItemClickListener, HouseObserver, RentDetailsObserver, DocumentsObserver, TenantObserver, ActivityObserver, HomeownerObserver, SwipeRefreshLayout.OnRefreshListener {




    private FragmentTenantLandingPageBinding binding;
    private TenantDocumentRecyclerViewAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tenant_landing_page, container, false);
        binding.swrTenantLanding.setOnRefreshListener(this);
        binding.viewLease.rcyTenantDocuments.setLayoutManager(new LinearLayoutManager(context));
        binding.tenantProfile.btnViewLease.setOnClickListener(new AddPhotoButton(getActivity(), TenantLandingFragment.this));
        binding.viewRentalUnitLocation.crdRentalUnitLocation.setVisibility(View.GONE);
        binding.viewLease.rcyTenantDocuments.setVisibility(View.GONE);
        binding.viewChequeDetails.crdChequeDetail.setVisibility(View.GONE);
        binding.viewRentalUnitLocation.crdRentalUnitLocation.setVisibility(View.GONE);
        ViewModelProviders.of(this).get(RentDetailsViewModel.class).getRentDetails(houseId, authToken, this);
        ViewModelProviders.of(this).get(HouseViewModel.class).getHouse(houseId, authToken, this);
        ViewModelProviders.of(this).get(DocumentViewModel.class).getDocuments(houseId, authToken, this);
        ViewModelProviders.of(this).get(HomeownerViewModel.class).loadHomeowner(authToken, houseId, this);
        ViewModelProviders.of(this).get(TenantViewModel.class).getTenant(authToken, this);
        return binding.getRoot();
    }



    @Override
    public void onPause() {
        super.onPause();
        binding.swrTenantLanding.setRefreshing(false);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onHouse(House house) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                binding.viewRentalUnitLocation.crdRentalUnitLocation.setVisibility(View.VISIBLE);
                binding.swrTenantLanding.setRefreshing(false);
                binding.viewRentalUnitLocation.txtTenantLandingAddress.setText(house.getFullAddress());
                binding.viewRentalUnitLocation.btnViewLease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setText(house.getFullAddress());
                        Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onRentDetails(final RentDetails rentDetails) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                binding.viewRentDetails.crdRentDetails.setVisibility(View.VISIBLE);
                Animation anim = new CircularProgressBarAnimation(binding.viewRentDetails.circularProgressBar, 0, rentDetails.getDayInMonth(), rentDetails.getTotalDaysInMonth());
                anim.setDuration(1500);
                binding.viewRentDetails.circularProgressBar.startAnimation(anim);
                binding.viewRentDetails.txtDaysLeft.setText(rentDetails.getFormattedDaysLeft());
                binding.viewRentDetails.txtAmountDue.setText(rentDetails.getTotalRent());
                binding.viewRentDetails.txtTenantRentDueDate.setText(rentDetails.getFormattedDueDate());

                binding.viewRentDetails.btnPayRent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!permission.doesHaveWriteCalendarPermission()) {
                            permission.requestWriteCalendarPermission();
                            return;
                        }
                        if (!permission.doesHaveReadCalendarPermission()) {
                            permission.requestReadCalendarPermission();
                            return;
                        }
                        Intent intent = intentFactory.getCalendarIntent();
                        if(intent.resolveActivity(context.getPackageManager()) != null) {
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(context, "Please download a calendar app", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onFailure(String tag, String response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                binding.swrTenantLanding.setRefreshing(false);
                if (response.equals("Not Authorized")){
                    binding.viewRentalUnitLocation.crdRentalUnitLocation.setVisibility(View.GONE);
                    binding.viewRentDetails.crdRentDetails.setVisibility(View.GONE);
                    binding.viewLease.rcyTenantDocuments.setVisibility(View.GONE);
                    binding.viewChequeDetails.crdChequeDetail.setVisibility(View.GONE);
                }
                if (tag.equals("House") && response.equals("Error: House Service Currently Unavailable") || response.equals("Error: Service currently unavailable.")) {
                    binding.viewRentalUnitLocation.crdRentalUnitLocation.setVisibility(View.GONE);
                }

                if (tag.equals("Documents") && response.equals("Error: Document Service Currently Unavailable") || response.equals("Error: Service currently unavailable.")) {
                    binding.viewLease.rcyTenantDocuments.setVisibility(View.GONE);
                }
                if (tag.equals("Tenant") && response.equals("Error: Tenant Service Currently Unavailable") || response.equals("Error: Service currently unavailable.")) {
                    navigation.navigateBack(TenantLandingFragment.this);
                    navigation.navigateBack(TenantLandingFragment.this);
                    Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                }
                if (tag.equals("Homeowner") && response.equals("Error: Homeowner Service Currently Unavailable") || response.equals("Error: House Service Currently Unavailable") || response.equals("Error: Service currently unavailable.")) {
                    binding.viewChequeDetails.crdChequeDetail.setVisibility(View.GONE);
                }
                if (tag.equals("RentDetails") && response.equals("Error: Rent Service Currently Unavailable") || response.equals("Error: Service currently unavailable.")) {
                    binding.viewRentDetails.crdRentDetails.setVisibility(View.GONE);
                }

                Toast.makeText(context, response, Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onDocuments(List<Document> documents) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                binding.viewLease.rcyTenantDocuments.setVisibility(View.VISIBLE);
                binding.swrTenantLanding.setRefreshing(false);
                adapter = new TenantDocumentRecyclerViewAdapter(documents);

                adapter.setItemClickListener(TenantLandingFragment.this);
                binding.viewLease.rcyTenantDocuments.setAdapter(adapter);
            }
        });
    }


    @Override
    public void onItemClick(View view, int position) {
        if (!permission.doesHaveWritePermission()) {
            permission.requestWriteExternalStoragePermission();
            return;
        }
        Document document = adapter.getItemAtPosition(position);
        Intent intent = intentFactory.getDocumentIntent(document);
        MainActivityTenant mainActivityTenant = (MainActivityTenant) getActivity();
        if (mainActivityTenant != null)
            mainActivityTenant.startActivityForResult(intent, Constants.DOCUMENT_INTENT);

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onTenant(Tenant tenant) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                binding.tenantProfile.crdTenantProfile.setVisibility(View.VISIBLE);
                binding.swrTenantLanding.setRefreshing(false);
                if (tenant.getImageURL() == null || tenant.getImageURL().isEmpty()) {
                    binding.tenantProfile.imageView2.setImageResource(R.drawable.ic_baseline_account_circle_24);
                }
                else {
                    Picasso.get().load(tenant.getImageURL())
                            .transform(new CircleTransform(binding.getRoot().getContext())).fit()
                            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                            .error(R.drawable.ic_baseline_account_circle_24)
                            .centerCrop()
                            .into(binding.tenantProfile.imageView2);
                }
                binding.tenantProfile.textView3.setText(tenant.getFullName());
            }
        });
    }

    @Override
    public void onFile(File file) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                ViewModelProviders.of(TenantLandingFragment.this).get(TenantViewModel.class).insertProfile(authToken, file, TenantLandingFragment.this);
                scheduleJob(email, "ProfilePicture");
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onHomeowner(Homeowner homeowner) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                binding.swrTenantLanding.setRefreshing(false);
                binding.viewChequeDetails.crdChequeDetail.setVisibility(View.VISIBLE);
                binding.viewChequeDetails.txtHomeownerName.setText(homeowner.getFullName());
                binding.viewChequeDetails.btnViewLease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = intentFactory.getContactsIntent(homeowner);
                        startActivity(intent);
                    }
                });
            }

        });
    }

    @Override
    public void onRefresh() {
        ViewModelProviders.of(this).get(RentDetailsViewModel.class).getRentDetails(houseId, authToken, this);
        ViewModelProviders.of(this).get(HouseViewModel.class).getHouse(houseId, authToken, this);
        ViewModelProviders.of(this).get(DocumentViewModel.class).getDocuments(houseId, authToken, this);
        ViewModelProviders.of(this).get(HomeownerViewModel.class).loadHomeowner(authToken, houseId, this);
        ViewModelProviders.of(this).get(TenantViewModel.class).getTenant(authToken, this);
    }
}