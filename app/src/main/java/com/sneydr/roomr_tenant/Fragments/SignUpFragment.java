package com.sneydr.roomr_tenant.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.navigation.fragment.NavHostFragment;

import com.sneydr.roomr_tenant.App.Dialog.Dialog;
import com.sneydr.roomr_tenant.Network.Callbacks.NetworkCallbackType;
import com.sneydr.roomr_tenant.Network.Network;
import com.sneydr.roomr_tenant.Network.Observers.SignUpRequestObserver;
import com.sneydr.roomr_tenant.R;

import okhttp3.Request;

public class SignUpFragment extends FragmentTemplate implements SignUpRequestObserver {

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.sign_up_web, container, false);
        webView = view.findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Network network = Network.getInstance();
        if (network.isNetworkAvailable(getActivity().getApplication())){
            Request request = network.getSignInURL();
            network.send(request, NetworkCallbackType.GetSignInURL, this);
        }

        return view;
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void onSignUpRequest(String url) {
        handler.post(new Runnable() {
            @Override
            public void run() {

                webView.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    @Override
    public void onFailure(String response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                webView.stopLoading();
                Dialog dialog = new Dialog(context);
                dialog.setMessage("Error: Sign up currently down for maintenance.");
                dialog.buildErrorDialog().show();
                NavHostFragment.findNavController(SignUpFragment.this).popBackStack();
            }
        });

    }
}
