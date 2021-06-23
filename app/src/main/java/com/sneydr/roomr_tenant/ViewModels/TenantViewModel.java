package com.sneydr.roomr_tenant.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sneydr.roomr_tenant.Entities.Login.Login;
import com.sneydr.roomr_tenant.Entities.Users.Tenant;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;
import com.sneydr.roomr_tenant.Repositories.TenantRepository;

import java.io.File;

public class TenantViewModel extends AndroidViewModel {

    TenantRepository repository;

    public TenantViewModel(@NonNull Application application) {
        super(application);
        this.repository = new TenantRepository(application);
    }


    private void saveTenant(Tenant tenant, NetworkObserver observer) {
        //repository.insert(tenant, observer);
    }

    private void updateTenant(Tenant tenant, NetworkObserver observer) {
        //repository.update(tenant, observer);
    }

    public void getTenant(String authToken, NetworkObserver observer) {
        repository.getTenant(authToken, observer);
    }

    private void getTenantByHouseId(int houseId, NetworkObserver observer) {
        //repository.getTenantsByHouseId(houseId, observer);
    }

    public void login(Login login, NetworkObserver observer) {
        repository.loginTenant(login, observer);
    }

    public void insertProfile(String authToken, File file, NetworkObserver networkObserver) {
        repository.insertProfile(authToken, file, networkObserver);
    }


}
