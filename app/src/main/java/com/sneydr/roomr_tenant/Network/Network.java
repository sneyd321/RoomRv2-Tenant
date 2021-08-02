package com.sneydr.roomr_tenant.Network;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import com.google.gson.JsonParser;
import com.sneydr.roomr_tenant.Entities.Login.Login;
import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Network.Callbacks.NetworkCallback;
import com.sneydr.roomr_tenant.Network.Callbacks.NetworkCallbackFactory;
import com.sneydr.roomr_tenant.Network.Callbacks.NetworkCallbackType;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Network {


    //private final String SERVER_URL = "http://34.107.132.144/tenant-gateway/v1/";
    private final String SERVER_URL = "http://192.168.100.109:8079/tenant-gateway/v1/";
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final MediaType JPG = MediaType.parse("image/jpg");
    private OkHttpClient client;
    private NetworkCallbackFactory factory;
    private JSONParser jsonParser;
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
        jsonParser = JSONParser.getInstance();
    }

    public boolean isNetworkAvailable(Application application) {
        final ConnectivityManager cm = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public String getServerUrl() {
        return SERVER_URL;
    }


    private String toBase64(String input) {
        return Base64.encodeToString(input.getBytes(), Base64.NO_WRAP);
    }


    public void send(Request request, NetworkCallbackType type, NetworkObserver observer) {
        NetworkCallback callback = factory.getNetworkCallback(type, observer);
        client.newCall(request).enqueue(callback);
    }



    public Request getURL(String url, String authToken) {
        return new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Accept-Encoding", "identity")
                .addHeader("Connection", "close")
                .build();
    }



    public Request getSignInURL() {
        return new Request.Builder()
                .url(SERVER_URL)
                .addHeader("Connection", "close")
                .addHeader("Accept-Encoding", "identity")
                .build();
    }
    public Request login(Login login) {
        RequestBody body = RequestBody.create(JSON, jsonParser.loginToJson(login));
        return new Request.Builder()
                .url(SERVER_URL + "Login")
                .post(body)
                .addHeader("Accept-Encoding", "identity")
                .addHeader("Connection", "close")
                .addHeader("Authorization", "Basic " + toBase64(login.getEmail() + ":" + login.getPassword()))
                .build();
    }

    public Request getHouse(int houseId, String authToken) {
        return new Request.Builder()
                .url(SERVER_URL + "House/" + houseId)
                //.cacheControl(new CacheControl.Builder().maxStale(600, TimeUnit.SECONDS).build())
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Accept-Encoding", "identity")
                .addHeader("Connection", "close")
                .build();
    }

    public Request getTenant(String authToken) {
        return new Request.Builder()
                .url(SERVER_URL + "Tenant")
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Accept-Encoding", "identity")
                .addHeader("Connection", "close")
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
                .addHeader("Accept-Encoding", "identity")
                .addHeader("Connection", "close")
                .build();
    }


    public Request getProblemsByHouseId(int houseId, String authToken) {
        return new Request.Builder()
                .url(SERVER_URL + "House/" + houseId + "/Problem")
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Accept-Encoding", "identity")
                .addHeader("Connection", "close")
                .build();
    }

    public Request getProblem(int problemId, String authToken) {
        return new Request.Builder()
                .url(SERVER_URL + "Problem/" + problemId)
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Accept-Encoding", "identity")
                .addHeader("Connection", "close")
                .build();
    }

    public Request getDocumentsByHouseId(int houseId, String authToken) {
        return new Request.Builder()
                .url(SERVER_URL + "Document/" + houseId + "/Tenant")
                //.cacheControl(new CacheControl.Builder().maxStale(600, TimeUnit.SECONDS).build())
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Accept-Encoding", "identity")
                .addHeader("Connection", "close")
                .build();
    }

    public Request getRentDetails(int houseId, String authToken) {
        return new Request.Builder()
                .url(SERVER_URL + "RentDetails/" + houseId)
                //.cacheControl(new CacheControl.Builder().maxStale(600, TimeUnit.SECONDS).build())
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Accept-Encoding", "identity")
                .addHeader("Connection", "close")
                .build();
    }


    public Request uploadProfilePicture(String authToken, File file){
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getName(), RequestBody.create(JPG, file))
                .build();
        return new Request.Builder()
                .url(SERVER_URL + "Tenant/Profile")
                .post(body)
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Accept-Encoding", "identity")
                .addHeader("Connection", "close")
                .build();
    }

    public Request getHomeowner(String authToken, int houseId) {
        Request request = new Request.Builder()
                .url(SERVER_URL + "Homeowner/" + houseId)
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Accept-Encoding", "identity")
                .addHeader("Connection", "close")
                .build();
        return request;
    }

}
