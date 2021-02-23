package com.sneydr.roomr_tenant.RabbitMQ;

import com.sneydr.roomr_tenant.Entities.House.Lease;

import java.io.File;
import java.io.OutputStream;

public interface RabbitMQObservable {


    void registerObserver(RabbitMQObserver observer);

    void clearObserver();

    void notifyLease(byte[] bytes);


}
