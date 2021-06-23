package com.sneydr.roomr_tenant.Network.Callbacks;



import com.sneydr.roomr_tenant.Entities.House.Document;
import com.sneydr.roomr_tenant.Entities.Users.Homeowner;
import com.sneydr.roomr_tenant.Network.Observables.DocumentsObservable;
import com.sneydr.roomr_tenant.Network.Observers.DocumentsObserver;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetDocumentsCallback extends NetworkCallback implements DocumentsObservable {
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.isSuccessful()){
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                InputStream input = responseBody.byteStream();
                InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
                List<Document> documents = jsonParser.parseDocuments(reader);
                notifyDocuments(documents);
            }
            else {
                notifyFailure("Document", "Error: Empty Response");
            }
        }
        else {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                notifyFailure("Document", responseBody.string());
            }
            else {
                notifyFailure("Document","Error: Unknown Error Occurred");
            }
        }
        response.close();
    }

    @Override
    public void notifyDocuments(List<Document> documents) {
        DocumentsObserver documentObserver = (DocumentsObserver) this.observer;
        documentObserver.onDocuments(documents);
    }


}
