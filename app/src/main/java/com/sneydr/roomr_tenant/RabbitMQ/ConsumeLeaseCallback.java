package com.sneydr.roomr_tenant.RabbitMQ;
import com.rabbitmq.client.Delivery;

import java.io.IOException;



public class ConsumeLeaseCallback extends RabbitCallback {
    @Override
    public void handle(String consumerTag, Delivery delivery)  {
        notifyLease(delivery.getBody());
    }
}
