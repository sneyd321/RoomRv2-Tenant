package com.sneydr.roomr_tenant.Network;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import com.sneydr.roomr_tenant.Entities.Login.Login;
import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Network.Callbacks.NetworkCallback;
import com.sneydr.roomr_tenant.Network.Callbacks.NetworkCallbackFactory;
import com.sneydr.roomr_tenant.Network.Callbacks.NetworkCallbackType;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Network {


    private final String SERVER_URL = "http://34.107.132.144/tenant-gateway/v1/";
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final MediaType JPG = MediaType.parse("image/jpg");
    private OkHttpClient client;
    private NetworkCallbackFactory factory;

    private static Network instance;

    public static Network getInstance() {
        if (instance == null){
            instance = new Network();
        }
        return instance;
    }

    private Network() {
        client = new OkHttpClient();
        factory = new NetworkCallbackFactory();
    }

    public boolean isNetworkAvailable(Application application) {
        final ConnectivityManager cm = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private String toBase64(String input) {
        return Base64.encodeToString(input.getBytes(), Base64.NO_WRAP);
    }


    public void send(Request request, NetworkCallbackType type, NetworkObserver observer) {
        NetworkCallback callback = factory.getNetworkCallback(type, observer);
        client.newCall(request).enqueue(callback);
    }



    public Request getSignInURL() {
        return new Request.Builder()
                .url(SERVER_URL)
                .build();
    }
    public Request login(Login login) {
        return new Request.Builder()
                .url(SERVER_URL + "Login")
                .addHeader("Authorization", "Basic " + toBase64(login.getEmail() + ":" + login.getPassword()))
                .build();
    }

    public Request getHouse(int houseId, String authToken) {
        return new Request.Builder()
                .url(SERVER_URL + "House/" + houseId)
                .addHeader("Authorization", "Bearer " + authToken)
                .build();
    }

    public Request getTenant(String authToken) {
        return new Request.Builder()
                .url(SERVER_URL + "Tenant")
                .addHeader("Authorization", "Bearer " + authToken)
                .build();
    }


    public Request postProblem(Problem problem, File file, String authToken) {
        JSONParser jsonParser = JSONParser.getInstance();
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getName(), RequestBody.create(JPG, file))
                .addFormDataPart("data", jsonParser.problemToJson(problem))
                .build();
        return new Request.Builder()
                .url(SERVER_URL + "Problem")
                .post(body)
                .addHeader("Authorization", "Bearer " + authToken)
                .build();
    }


    public Request getProblemsByHouseId(int houseId, String authToken) {
        return new Request.Builder()
                .url(SERVER_URL + "House/" + houseId + "/Problem")
                .addHeader("Authorization", "Bearer " + authToken)
                .build();
    }

    public Request getProblem(int problemId, String authToken) {
        return new Request.Builder()
                .url(SERVER_URL + "Problem/" + problemId)
                .addHeader("Authorization", "Bearer " + authToken)
                .build();
    }


}
