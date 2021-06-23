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


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Toast;

import com.sneydr.roomr_tenant.Activities.MainActivityTenant;
import com.sneydr.roomr_tenant.Adapters.Listeners.ItemClickListener;
import com.sneydr.roomr_tenant.Adapters.TenantDocumentRecyclerViewAdapter;
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
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;


public class TenantLandingFragment extends FragmentTemplate implements ItemClickListener, HouseObserver, RentDetailsObserver, DocumentsObserver, TenantObserver, ActivityObserver, HomeownerObserver {




    private FragmentTenantLandingPageBinding binding;
    private TenantDocumentRecyclerViewAdapter adapter;
    private RentDetailsViewModel rentDetailsViewModel;
    private HouseViewModel houseViewModel;
    private DocumentViewModel documentViewModel;
    private TenantViewModel tenantViewModel;
    private HomeownerViewModel homeownerViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tenant_landing_page, container, false);

        rentDetailsViewModel = ViewModelProviders.of(this).get(RentDetailsViewModel.class);
        houseViewModel = ViewModelProviders.of(this).get(HouseViewModel.class);
        documentViewModel = ViewModelProviders.of(this).get(DocumentViewModel.class);
        tenantViewModel = ViewModelProviders.of(this).get(TenantViewModel.class);
        homeownerViewModel = ViewModelProviders.of(this).get(HomeownerViewModel.class);
        rentDetailsViewModel.getRentDetails(houseId, authToken, this);
        houseViewModel.getHouse(houseId, authToken,this);
        documentViewModel.getDocuments(houseId, authToken, this);
        homeownerViewModel.loadHomeowner(authToken, houseId, this);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        tenantViewModel.getTenant(authToken, this);

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onHouse(House house) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (house != null) {
                    binding.viewRentalUnitLocation.crdRentalUnitLocation.setVisibility(View.VISIBLE);
                    binding.viewRentalUnitLocation.txtTenantLandingAddress.setText(house.getFullAddress());
                    binding.viewRentalUnitLocation.btnViewLease.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onClick(View view) {
                            ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                            cm.setText(house.getFullAddress());
                            Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    binding.viewRentalUnitLocation.crdRentalUnitLocation.setVisibility(View.GONE);
                }
            }
        });


    }



    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onRentDetails(final RentDetails rentDetails) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (rentDetails != null) {
                    binding.viewRentDetails.crdRentDetails.setVisibility(View.VISIBLE);
                    Animation anim = new CircularProgressBarAnimation(binding.viewRentDetails.circularProgressBar, 0, rentDetails.getDayInMonth(), rentDetails.getTotalDaysInMonth());
                    anim.setDuration(1500);
                    binding.viewRentDetails.circularProgressBar.startAnimation(anim);
                    binding.viewRentDetails.txtDaysLeft.setText(rentDetails.getFormattedDaysLeft());
                    binding.viewRentDetails.txtAmountDue.setText(rentDetails.getTotalRent());
                    binding.viewRentDetails.txtTenantRentDueDate.setText(rentDetails.getFormattedDueDate());

                    binding.viewRentDetails.btnPayRent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
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
                else {
                    binding.viewRentDetails.crdRentDetails.setVisibility(View.GONE);
                }


            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onDocuments(List<Document> documents) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (documents != null) {
                    binding.viewLease.rcyTenantDocuments.setVisibility(View.VISIBLE);
                    adapter = new TenantDocumentRecyclerViewAdapter(documents);
                    binding.viewLease.rcyTenantDocuments.setLayoutManager(new LinearLayoutManager(context));
                    adapter.setItemClickListener(TenantLandingFragment.this);
                    binding.viewLease.rcyTenantDocuments.setAdapter(adapter);
                }
                else {
                    binding.viewLease.rcyTenantDocuments.setVisibility(View.GONE);
                }

            }
        });
    }


    @Override
    public void onItemClick(View view, int position) {
        if (!permission.doesHaveWritePermission()) {
            permission.requestWriteExternalStoragePermission();
        }
        else {
            Document document = adapter.getItemAtPosition(position);
            Intent intent = intentFactory.getDocumentIntent(document);
            getActivity().startActivityForResult(intent, Constants.DOCUMENT_INTENT);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onTenant(Tenant tenant) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (tenant != null){



                    binding.tenantProfile.crdTenantProfile.setVisibility(View.VISIBLE);
                    Picasso.get().load(tenant.getImageURL())
                            .transform(new CircleTransform(context))
                            .fit()
                            .error(R.drawable.ic_baseline_account_circle_24)
                            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                            .centerCrop()
                            .into(binding.tenantProfile.imageView2);


                    binding.tenantProfile.textView3.setText(tenant.getFullName());
                    binding.tenantProfile.btnViewLease.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (tenant.getImageURL() != null) {
                                Toast.makeText(context, "Update profile not implemented for beta release.", Toast.LENGTH_LONG).show();
                                return;
                            }
                            ((MainActivityTenant)getActivity()).registerObserver(TenantLandingFragment.this);
                            Intent intent = intentFactory.getGalleryIntent();
                            getActivity().startActivityForResult(intent, Constants.IMAGE_INTENT);
                        }
                    });
                }
                else {
                    binding.tenantProfile.crdTenantProfile.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    public void onFile(File file) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                tenantViewModel.insertProfile(authToken, file, TenantLandingFragment.this);
                Toast.makeText(context, "Image updating, please refresh in 5-10 seconds", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onHomeowner(Homeowner homeowner) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (homeowner != null){
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
                else {
                    binding.viewChequeDetails.crdChequeDetail.setVisibility(View.GONE);
                }
            }
        });
    }
}