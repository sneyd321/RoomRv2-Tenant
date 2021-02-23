package com.sneydr.roomr_tenant.Network.Callbacks;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class GetLeaseCallback extends NetworkCallback  {
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.isSuccessful()) {
            return;
        }
        notifyFailure(response.body().string());
    }

}
