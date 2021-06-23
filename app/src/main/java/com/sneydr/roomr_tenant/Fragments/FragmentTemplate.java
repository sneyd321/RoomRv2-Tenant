package com.sneydr.roomr_tenant.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import com.sneydr.roomr_tenant.Activities.MainActivityTenant;
import com.sneydr.roomr_tenant.App.Dialog.Dialog;
import com.sneydr.roomr_tenant.App.IntentFactory;
import com.sneydr.roomr_tenant.App.Permission;
import com.sneydr.roomr_tenant.Network.Observers.InternetAvailableObserver;
import com.sneydr.roomr_tenant.Network.Observers.InternetPermissionObserver;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

public abstract class FragmentTemplate extends Fragment implements NetworkObserver, LifecycleOwner, InternetPermissionObserver, InternetAvailableObserver {


    protected Handler handler;
    protected Context context;
    protected int houseId;
    protected String authToken;
    protected Permission permission;
    protected IntentFactory intentFactory;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.permission = new Permission(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        handler = new Handler(Looper.getMainLooper());
        intentFactory = new IntentFactory();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onNoInternetPermission(Permission permission) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                permission.requestInternetPermission();
            }
        });
    }

    public FragmentTemplate setHouseId(int houseId) {
        this.houseId = houseId;
        return this;
    }

    public FragmentTemplate setAuthToken(String authToken) {
        this.authToken = authToken;
        return this;
    }


    @Override
    public void onNoInternet(String text) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Dialog dialog = new Dialog(getContext());
                dialog.setMessage(text);
                dialog.buildErrorDialog().show();
            }
        });

    }

    @Override
    public void onFailure(String tag, String response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (response.equals("Not Authenticated")){
                    getActivity().onBackPressed();
                }
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
