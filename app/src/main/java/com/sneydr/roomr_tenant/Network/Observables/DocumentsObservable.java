package com.sneydr.roomr_tenant.Network.Observables;



import com.sneydr.roomr_tenant.Entities.House.Document;

import java.util.List;

public interface DocumentsObservable extends NetworkObservable {

    void notifyDocuments(List<Document> documents);

}
