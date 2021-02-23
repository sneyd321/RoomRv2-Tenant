package com.sneydr.roomr_tenant.App.Camera;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;

public interface CameraObservable {

    void registerObserver(CameraObserver observer);

    void clearObserver();

    void notifySuccess(@NonNull ImageCapture.OutputFileResults outputFileResults);

    void notifyFailure(@NonNull ImageCaptureException exception);

}
