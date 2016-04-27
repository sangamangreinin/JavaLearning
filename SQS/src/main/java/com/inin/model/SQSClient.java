package com.inin.model;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.stereotype.Component;

/**
 * Created by root on 27/4/16.
 */
@Component
public class SQSClient {
    private AmazonSQS sqs;

    public SQSClient() {
        this.sqs = createSQSClient();
    }

    public AmazonSQS getSqs() {
        return sqs;
    }

    private AmazonSQS  createSQSClient(){
        AWSCredentials awsCredentials = new ProfileCredentialsProvider().getCredentials();
        AmazonSQS sqs = new AmazonSQSClient(awsCredentials);
        Region usEast1 = Region.getRegion(Regions.US_EAST_1);
        sqs.setRegion(usEast1);
        return sqs;
    }
}
