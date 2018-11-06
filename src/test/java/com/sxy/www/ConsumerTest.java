package com.sxy.www;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * Created by xiangyusun on 2018/11/6.
 */
public class ConsumerTest {

    private final String pulsarUrl = "pulsar://localhost:6650";

//    private final Logger logger = LoggerFactory.getLogger(ConsumerTest.class);

        PulsarClient client = null;
    public void init() throws PulsarClientException {
        client = PulsarClient.builder().serviceUrl(pulsarUrl).build();

        System.out.println(" class init");
    }

    public static void main(String[] args) throws PulsarClientException {
        ConsumerTest consumerTest = new ConsumerTest();
        consumerTest.consume();
    }
    public void consume() throws PulsarClientException {
        init();
        Consumer<byte[]> consumer = null;
        try {
            System.out.println("create consumer");
            consumer = client.newConsumer()
                    .topic("my-topic")
                    .subscriptionName("my-subscription")
                    .subscribe();
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }
        System.out.println("subscribe------");

        do {
            CompletableFuture<Message<byte[]>> asyncMessage = consumer.receiveAsync();
            // Wait for a message
            Message msg = consumer.receive();

            System.out.printf("Message received: %s", new String(msg.getData()));

            // 确认消息，以便broker删除消息
            consumer.acknowledge(msg);
        } while (true);

    }
}
