package com.sneydr.roomr_tenant.Network.Callbacks;

import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

public class NetworkCallbackFactory {


    public NetworkCallback getNetworkCallback(NetworkCallbackType type, NetworkObserver observer) {
        NetworkCallback callback = null;
        switch (type) {
            case GetHouses:
                callback = new GetHousesCallback();
                break;
            case GetTenant:
                callback = new GetTenantCallback();
                break;
            case GetHouse:
                callback = new GetHouseCallback();
                break;
            case GetTenants:
                callback = new GetTenantsCallback();
                break;
            case GetProblems:
                callback = new GetProblemsCallback();
                break;
            case GetProblem:
                callback = new GetProblemCallback();
                break;
            case GetLease:
                callback = new GetLeaseCallback();
                break;
            case GetSignInURL:
                callback = new GetSignUpURLCallback();
                break;
            case GetAddHouseURL:
                callback = new GetAddHouseURLCallback();
                break;
            case GetDocuments:
                callback = new GetDocumentsCallback();
                break;
            case GetRentDetails:
                callback = new GetRentDetailsCallback();
                break;
            case Empty:
                callback = new EmptyNetworkCallback();
            case GetHomeowner:
                callback = new GetHomeownerCallback();

    }
        if (callback != null) {
            callback.registerObserver(observer);
        }
        return callback;

    }

}
