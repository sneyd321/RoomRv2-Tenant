package com.sneydr.roomr_tenant.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sneydr.roomr_tenant.Adapters.TenantLandingUtilitiesRecyclerViewAdapter;
import com.sneydr.roomr_tenant.App.UI.CircularProgressBarAnimation;
import com.sneydr.roomr_tenant.Network.Observers.HouseObserver;
import com.sneydr.roomr_tenant.Entities.House.House;
import com.sneydr.roomr_tenant.Entities.RentDetails.CalendarHandler;
import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.ViewModels.HouseViewModel;


public class TenantLandingFragment extends FragmentTemplate implements HouseObserver {



    ProgressBar circularProgressBar;
    TextView txtDueDate, txtRentAmount, txtRentalUnitPrimaryAddress, txtHomeownerName, txtDaysLeft;
    CalendarHandler calendarHandler;
    Button btnPayRent;
    CardView crdRentDetails, crdRentalUnitLocation, crdChequeDetails, crdNoData;
    private int houseId;
    private String authToken;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_tenant_landing_page, container, false);
        txtDueDate = view.findViewById(R.id.txtTenantRentDueDate);
        txtDaysLeft = view.findViewById(R.id.txtDaysLeft);
        txtRentAmount = view.findViewById(R.id.txtAmountDue);
        btnPayRent = view.findViewById(R.id.btnPayRent);
        btnPayRent.setOnClickListener(onPayRent);
        txtRentalUnitPrimaryAddress = view.findViewById(R.id.txtTenantLandingAddress);
        circularProgressBar = view.findViewById(R.id.circularProgressBar);
        txtHomeownerName = view.findViewById(R.id.txtHomeownerName);
        crdRentDetails = view.findViewById(R.id.crdRentDetails);
        crdRentalUnitLocation = view.findViewById(R.id.crdRentalUnitLocation);
        crdChequeDetails = view.findViewById(R.id.crdChequeDetail);
        crdNoData = view.findViewById(R.id.crdNotApproved);
        calendarHandler = new CalendarHandler();
        toggleHouseVisibility(false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        HouseViewModel houseViewModel = ViewModelProviders.of(this).get(HouseViewModel.class);

        houseViewModel.getHouse(houseId, authToken,this);
    }


    private void toggleHouseVisibility(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            crdChequeDetails.setVisibility(View.VISIBLE);
            crdRentalUnitLocation.setVisibility(View.VISIBLE);
            crdRentDetails.setVisibility(View.VISIBLE);
            crdNoData.setVisibility(View.GONE);
        }
        else {
            crdChequeDetails.setVisibility(View.GONE);
            crdRentalUnitLocation.setVisibility(View.GONE);
            crdRentDetails.setVisibility(View.GONE);
            crdNoData.setVisibility(View.VISIBLE);
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onHouse(House house) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (house != null) {
                    toggleHouseVisibility(true);
                    Animation anim = new CircularProgressBarAnimation(circularProgressBar, 0, calendarHandler.getDayInMonth(), calendarHandler.getTotalDaysInMonth());
                    anim.setDuration(1500);
                    String rentDueDate = house.getRentDetails().getRentDueDate();
                    circularProgressBar.startAnimation(anim);
                    int daysLeft = calendarHandler.getDaysUntilDueDate(rentDueDate) - calendarHandler.getDayInMonth();
                    txtDaysLeft.setText(daysLeft + "\ndays");
                    txtRentAmount.setText("$" + house.getRentDetails().getBaseRent());
                    txtDueDate.setText(calendarHandler.getFormattedDueDate(rentDueDate));
                    txtRentalUnitPrimaryAddress.setText(house.getRentalUnitLocation().getFormattedPrimaryAddress() + "\n" + house.getRentalUnitLocation().getFormattedSecondaryAddress());
                    String rentMadePayable = house.getRentDetails().getRentMadePayableTo();
                    if (rentMadePayable == null) {
                        txtHomeownerName.setText("Cheque option not enabled");
                    } else {
                        txtHomeownerName.setText(rentMadePayable);
                    }
                }
            }
        });


    }

    private View.OnClickListener onPayRent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), "Not implemented for release: 1", Toast.LENGTH_SHORT).show();
        }
    };


    public TenantLandingFragment setHouseId(int houseId) {
        this.houseId = houseId;
        return this;
    }

    public TenantLandingFragment setAuthToken(String authToken) {
        this.authToken = authToken;
        return this;
    }








}