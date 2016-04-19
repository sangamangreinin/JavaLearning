package com.inin.kafka.producer;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * Created by root on 18/4/16.
 *
 */
public class SimplePartitioner implements Partitioner {

    public SimplePartitioner(VerifiableProperties props) {

    }

    /**
     * The method takes the key, which	in this	case is	the	random number, and does	a modulo operation on the number of	partitions	defined	within
     * Kafka for the topic.
     * @param key Random number
     * @param a_numPartitions the number of available partitions for given topic to return a partition id.
     * @return Partition id
     */
    @Override
    public int partition(Object key, int a_numPartitions) {
        int partition = 0;
        String stringKey = (String) key;
        Integer intKey = Integer.parseInt(stringKey);
        if (intKey > 0) {
            partition = intKey % a_numPartitions;
        }
        System.out.println("Returning partition number [" + partition + "] " + "for key ["+key+"]");
        return partition;
    }
}
