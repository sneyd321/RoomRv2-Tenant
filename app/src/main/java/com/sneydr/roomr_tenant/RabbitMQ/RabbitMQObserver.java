package com.sneydr.roomr_tenant.RabbitMQ;


import java.io.File;
import java.io.OutputStream;

public interface RabbitMQObserver {


    void onLease(byte[] bytes);


}
