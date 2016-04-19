package com.inin.kafka.consumer;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Consumer class receiving the messages from producer
 *
 */
public class Consumer {
    private ConsumerConnector consumerConnector = null;
    private final String topic = "newticket";

    public void initialize() {
        Properties props = new Properties();

        //Specifies	the	ZooKeeper <node:port> connection detail	that is	used to	find the Zookeeper running instance in the cluster.
        props.put("zookeeper.connect", "localhost:2181");

        //Name	for	the	consumer group shared by all the consumers within the group, also used by zookeeper to store the offset.
        props.put("group.id", "testgroup");

        //The amount of	time in ms Kafka will	wait for Zookeeper to respond to a request before giving up	and	continuing to consume messages.
        props.put("zookeeper.session.timeout.ms", "400");

        //ZooKeeper	sync time in milliseconds between the ooKeeper leader and the followers.
        props.put("zookeeper.sync.time.ms", "300");

        //the frequency	in milliseconds	at which consumer offsets get committed	to Zookeeper.
        props.put("auto.commit.interval.ms", "1000");

        ConsumerConfig conConfig = new ConsumerConfig(props);

        // Initialize the consumer with required properties
        consumerConnector = kafka.consumer.Consumer.createJavaConsumerConnector(conConfig);
    }

    public void consume() {
        //Key = topic name, Value = No. of threads for topic
        Map<String, Integer> topicCount = new HashMap<String, Integer>();
        topicCount.put(topic, new Integer(1));

        //ConsumerConnector creates the message stream for each topic
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams = consumerConnector.createMessageStreams(topicCount);

        // Get Kafka stream for topic 'newticket'
        List<KafkaStream<byte[], byte[]>> kStreamList = consumerStreams.get(topic);

        // Iterate stream using ConsumerIterator
        for (final KafkaStream<byte[], byte[]> kStreams : kStreamList) {
            ConsumerIterator<byte[], byte[]> consumerIte = kStreams.iterator();

            while (consumerIte.hasNext())
                System.out.println("Message consumed from topic [" + topic + "] : " + new String(consumerIte.next().message()));
        }
        //Shutdown the consumer connector
        if (consumerConnector != null)   consumerConnector.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        Consumer kafkaConsumer = new Consumer();
        // Configure Kafka consumer
        kafkaConsumer.initialize();
        // Start consumption
        kafkaConsumer.consume();
    }
}
