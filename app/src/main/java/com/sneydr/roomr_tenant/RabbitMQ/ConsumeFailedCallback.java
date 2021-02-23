package com.sneydr.roomr_tenant.RabbitMQ;

import com.rabbitmq.client.CancelCallback;

import java.io.IOException;

public class ConsumeFailedCallback implements CancelCallback {
    @Override
    public void handle(String consumerTag) throws IOException {

    }
}
