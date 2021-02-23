package com.sneydr.roomr_tenant.Network.Callbacks;

import com.sneydr.roomr_tenant.Network.Observables.SignUpRequestObservable;
import com.sneydr.roomr_tenant.Network.Observers.SignUpRequestObserver;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class GetSignUpURLCallback extends NetworkCallback implements SignUpRequestObservable {
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.isSuccessful()) {
            notifyObserver(response.body().string());
        }
        else {
            notifyFailure("Failed to connect to server");
        }
        response.close();
    }

    @Override
    public void notifyObserver(String url) {
        SignUpRequestObserver signUpRequestObserver = (SignUpRequestObserver) this.observer;
        signUpRequestObserver.onSignUpRequest(url);
    }
}

