package com.inin.dao;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.inin.model.SQSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 27/4/16.
 */
@Repository
public class QueueDao {

    @Autowired
    SQSClient sqsClient;

    public void createQueue(String queueName){
        AmazonSQS sqs = sqsClient.getSqs();

        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        String url = sqs.createQueue(createQueueRequest).getQueueUrl();
        System.out.println(url);
    }
    public void deleteQueue(String myQueueUrl){
        AmazonSQS sqs = sqsClient.getSqs();

        System.out.println("Deleting the test queue.\n");
        sqs.deleteQueue(new DeleteQueueRequest(myQueueUrl));
    }

    public boolean isQueueExist(String myQueueUrl){
        AmazonSQS sqs = sqsClient.getSqs();

        // List queues
        System.out.println("Listing all queues in your account.\n");
        //change below lines to lambda expression
        for (String queueUrl : sqs.listQueues().getQueueUrls()) {
            if(queueUrl.equals(myQueueUrl)){
                return true;
            }
        }
        return false;
    }

    public void sendMessage(String myQueueUrl, String emailId){
        AmazonSQS sqs = sqsClient.getSqs();

        System.out.println("Sending a message to SwetaQueue.\n");
        sqs.sendMessage(new SendMessageRequest(myQueueUrl, emailId));
    }

    public void receiveMessage(String myQueueUrl){
        AmazonSQS sqs = sqsClient.getSqs();

        // Receive messages
        System.out.println("Receiving messages from MyQueue.\n");
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
        List<com.amazonaws.services.sqs.model.Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        System.out.println(messages.size()+"------size-------");
        for (com.amazonaws.services.sqs.model.Message message : messages) {
            System.out.println("  Message");
            System.out.println("    MessageId:     " + message.getMessageId());
            System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
            System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("    Body:          " + message.getBody());
            for (Map.Entry<String, String> entry : message.getAttributes().entrySet()) {
                System.out.println("  Attribute");
                System.out.println("    Name:  " + entry.getKey());
                System.out.println("    Value: " + entry.getValue());
            }
        }
    }
}
