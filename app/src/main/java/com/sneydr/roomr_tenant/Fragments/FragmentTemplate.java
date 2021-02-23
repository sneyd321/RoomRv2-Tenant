package com.sneydr.roomr_tenant.Fragments;

import android.content.Context;
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

import com.sneydr.roomr_tenant.App.Dialog.Dialog;
import com.sneydr.roomr_tenant.App.Permission;
import com.sneydr.roomr_tenant.Network.Observers.InternetAvailableObserver;
import com.sneydr.roomr_tenant.Network.Observers.InternetPermissionObserver;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

public abstract class FragmentTemplate extends Fragment implements NetworkObserver, LifecycleOwner, InternetPermissionObserver, InternetAvailableObserver {


    protected Handler handler;
    protected Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        handler = new Handler(Looper.getMainLooper());
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
    public void onFailure(String response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
