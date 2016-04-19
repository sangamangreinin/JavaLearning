package com.inin.kafka.producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Producer class which is responsible to create custom partitions & sending the messages on the custom partitions of the topic.
 *
 */
public class CustomPartitionerProducer {
    public static void main(String[] args) {
        Random random = new Random();

        Properties properties = new Properties();

        //kafka brokers where producer will be connected.
        properties.put("metadata.broker.list", "localhost:9092,localhost:9093");
        //,localhost:9093,localhost:9094

        //serializer class for the message key i.e default class StringEncoder
        properties.put("serializer.class", "kafka.serializer.StringEncoder");

        //To implement 'kafka.producer.Partitioner' interface. In this implementation we can write a logic that will decide which message should be sent
        // to which partition based on message key.
        properties.put("partitioner.class", "com.inin.kafka.producer.SimplePartitioner");

        //set as '1' if we want to make sure that producer will be acknowledged when message is received by brokers successfully or not
        properties.put("request.required.acks", "3");

        // Set the number of attempts to send the message
        properties.put("message.send.max.retries", "3");

        ProducerConfig producerConfig = new ProducerConfig(properties);

        // Initialize the producer with required properties
        Producer<String, String> producer = new Producer<String, String>(producerConfig);

        for(long i=0; i<10; i++){
            long runTime = new Date().getTime();
            String randomNum = String.valueOf(random.nextInt(260));
            System.out.println("randomNum : " + randomNum);
            String msg = runTime + " Hi this is producer " + randomNum;

            //Creating a KeyedMessage instance
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("newticket", randomNum, msg);
            System.out.println("data : " + data);
            // Publish the message  to brokers
            producer.send(data);
            System.out.println("Message sent successfully on topic partition " + randomNum);
            //data = null;
        }
        // Closing the connection between broker and producer
        producer.close();
    }
}
