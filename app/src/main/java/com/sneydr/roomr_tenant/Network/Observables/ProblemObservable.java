package com.sneydr.roomr_tenant.Network.Observables;

import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

import java.util.List;

public interface ProblemObservable extends NetworkObservable {

    void notifyProblem(Problem problem);
}
