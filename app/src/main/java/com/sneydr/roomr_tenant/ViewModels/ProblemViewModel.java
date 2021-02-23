package com.sneydr.roomr_tenant.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Network.Network;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;
import com.sneydr.roomr_tenant.Repositories.ProblemRepository;


import java.io.File;

public class ProblemViewModel extends AndroidViewModel {
    private ProblemRepository repository;

    public ProblemViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ProblemRepository(application);
    }

    public void getProblemById(int problemId, String authToken, NetworkObserver observer) {
        repository.getProblemById(problemId, authToken, observer);
    }


    public void saveProblem(Problem problem, File file, String authToken, NetworkObserver observer) {
        repository.insert(problem, file, authToken, observer);
    }

    private void updateProblem(Problem problem, NetworkObserver observer) {
        //repository.update(problem, observer);
    }

    public void loadProblems(int houseId, String authToken, NetworkObserver observer) {
        repository.getProblemByHouseId(houseId, authToken, observer);
    }


}
