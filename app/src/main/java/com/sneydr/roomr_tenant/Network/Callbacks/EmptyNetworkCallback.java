package com.sneydr.roomr_tenant.Network.Callbacks;

import com.sneydr.roomr_tenant.Entities.House.Document;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class EmptyNetworkCallback extends NetworkCallback {

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        response.close();
    }
}
