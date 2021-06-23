package com.sneydr.roomr_tenant.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.sneydr.roomr_tenant.Network.Observers.DocumentsObserver;
import com.sneydr.roomr_tenant.Repositories.DocumentRepository;


public class DocumentViewModel extends AndroidViewModel {
    DocumentRepository repository;

    public DocumentViewModel(Application application) {
        super(application);
        repository = new DocumentRepository(application);
    }

    public void getDocuments(int houseId, String authToken, DocumentsObserver observer) {
        repository.getDocuments(houseId, authToken, observer);
    }



}
