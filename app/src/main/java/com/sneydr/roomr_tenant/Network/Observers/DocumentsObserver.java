package com.sneydr.roomr_tenant.Network.Observers;


import com.sneydr.roomr_tenant.Entities.House.Document;

import java.util.List;

public interface DocumentsObserver extends NetworkObserver {

    void onDocuments(List<Document> documents);


}
