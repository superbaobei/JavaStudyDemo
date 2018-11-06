package com.sxy.www.pulsar;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by xiangyusun on 2018/11/5.
 */
public class UserMessageProducer {

    public boolean createMessage(String msgStr){


        return  true;
    }

    public void createConnection() throws PulsarClientException {

        PulsarClient client = PulsarClient.builder().serviceUrl(pulsarUrl).build();
        Producer<byte[]> producer = client.newProducer().topic("my-topic").create();
    }


    @Value("${pulsarUrl}")
    private String pulsarUrl;
}
