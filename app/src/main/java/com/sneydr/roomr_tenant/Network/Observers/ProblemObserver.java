package com.sneydr.roomr_tenant.Network.Observers;

import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Network.Observables.NetworkObservable;

import java.util.List;

public interface ProblemObserver extends NetworkObserver {

    void onProblem(Problem problem);

}
