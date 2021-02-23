package com.sneydr.roomr_tenant.Network.Observers;

import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

import java.util.List;

public interface ProblemsObserver extends NetworkObserver {

    void onProblems(List<Problem> problems);

}
