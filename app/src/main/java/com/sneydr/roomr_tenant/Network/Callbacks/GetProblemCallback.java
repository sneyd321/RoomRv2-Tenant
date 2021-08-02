package com.sneydr.roomr_tenant.Network.Callbacks;

import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Network.Observables.ProblemObservable;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;
import com.sneydr.roomr_tenant.Network.Observers.ProblemObserver;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetProblemCallback extends NetworkCallback implements ProblemObservable {
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            notifyFailure("Problem", "Error: Empty Response");
            return;
        }
        if (response.isSuccessful()){
            Problem problem = jsonParser.parseProblem(responseBody.byteStream());
            notifyProblem(problem);
        }
        else {
            notifyFailure("Problem",responseBody.string());
        }
        response.close();
    }

    @Override
    public void notifyProblem(Problem problem) {
        ProblemObserver problemObserver = (ProblemObserver) observer;
        problemObserver.onProblem(problem);
    }
}
