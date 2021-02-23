package com.sneydr.roomr_tenant.Network.Observables;

import com.sneydr.roomr_tenant.Entities.Problem.Problem;

import java.util.List;

public interface ProblemsObservable extends NetworkObservable {

    void notifyProblems(List<Problem> problems);

}
