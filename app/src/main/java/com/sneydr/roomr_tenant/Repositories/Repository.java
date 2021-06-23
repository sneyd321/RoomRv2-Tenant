package com.sneydr.roomr_tenant.Repositories;

import android.app.Application;
import android.content.Context;

import com.sneydr.roomr_tenant.App.Permission;
import com.sneydr.roomr_tenant.Network.Network;
import com.sneydr.roomr_tenant.Network.Observables.InternetAvailableObservable;
import com.sneydr.roomr_tenant.Network.Observables.InternetPermissionObservable;
import com.sneydr.roomr_tenant.Network.Observers.InternetAvailableObserver;
import com.sneydr.roomr_tenant.Network.Observers.InternetPermissionObserver;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

public abstract class Repository implements InternetAvailableObservable, InternetPermissionObservable {


    protected Network network;
    protected Application application;
    protected Permission permission;
    private NetworkObserver observer;


    public Repository(Application application) {
        this.network = Network.getInstance();
        this.application = application;
        this.permission = new Permission(application);
    }

    public boolean doesHaveInternetPermission(NetworkObserver observer) {
        if (!permission.doesHaveInternetPermission()){
            registerObserver(observer);
            notifyNoInternetPermission(permission);
            return false;
        }
        return true;
    }

    public boolean doesHaveInternet(NetworkObserver observer) {
        if (!network.isNetworkAvailable(application)) {
            registerObserver(observer);
            notifyInternetNotAvailable("Could not connect to the internet.");
            return false;
        }
        return true;
    }

    @Override
    public void notifyFailure(String tag, String response) {
        observer.onFailure(tag, response);
    }

    @Override
    public void notifyInternetNotAvailable(String text) {
        InternetAvailableObserver internetAvailableObserver = (InternetAvailableObserver) observer;
        internetAvailableObserver.onNoInternet(text);
    }

    @Override
    public void notifyNoInternetPermission(Permission permission) {
        InternetPermissionObserver internetPermissionObserver = (InternetPermissionObserver) observer;
        internetPermissionObserver.onNoInternetPermission(permission);
    }

    @Override
    public void registerObserver(NetworkObserver networkObserver) {
        this.observer = networkObserver;
    }

    @Override
    public void clearObserver() {
        this.observer = null;
    }
}
