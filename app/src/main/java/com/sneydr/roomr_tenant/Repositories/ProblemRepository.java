package com.sneydr.roomr_tenant.Repositories;



import android.app.Application;

import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Network.Callbacks.NetworkCallbackType;
import com.sneydr.roomr_tenant.Network.Network;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

import java.io.File;

import okhttp3.Request;

public class ProblemRepository extends Repository{


    public ProblemRepository(Application application) {
        super(application);
    }

    public void insert(Problem problem, File file, String authToken, NetworkObserver observer) {
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)) {
            Request request = network.postProblem(problem, file, authToken);
            network.send(request, NetworkCallbackType.GetProblem, observer);
        }

    }
    private void update(Problem problem, NetworkObserver observer) {
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)) {
            //Request request = network.putProblem(problem);
            //network.send(request, NetworkCallbackType.GetProblem, observer);
        }
    }

    public void getProblemByHouseId(int houseId, String authToken, NetworkObserver observer) {
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)){
            Request request = network.getProblemsByHouseId(houseId, authToken);
            network.send(request, NetworkCallbackType.GetProblems, observer);
        }
    }

    public void getProblemById(int id, String authToken, NetworkObserver observer){
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)) {
            Request request = network.getProblem(id, authToken);
            network.send(request, NetworkCallbackType.GetProblem, observer);
        }


    }




}
