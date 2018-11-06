package com.sxy.www;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by xiangyusun on 2018/11/5.
 */
public class PulsarTestCase extends BaseTestObject {

    PulsarClient client = null;

    @Value("${pulsarUrl}")
    private String pulsarUrl;

    @Before
    public void init() throws PulsarClientException {
        client = PulsarClient.builder().serviceUrl(pulsarUrl).build();

    }

    @Test
    public void testCreateMessage() throws PulsarClientException {
        try(Producer<byte[]> producer = client.newProducer().topic("my-topic").create()){

            producer.sendAsync("my-async-message".getBytes()).thenAccept(msgId -> {
                System.out.printf("Message with ID %s successfully sent", msgId);
            });

        }
    }

    @Test
    public void testCustomMessage(){

    }

    @After
    public void close() throws PulsarClientException {
        client.close();
    }


}
