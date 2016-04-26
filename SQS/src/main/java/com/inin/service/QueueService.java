package com.inin.service;

import com.amazonaws.services.sqs.model.*;
import com.inin.SqsAwsConfiguration;
import com.inin.controllers.dto.MessageRequest;
import com.inin.controllers.dto.QueueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 26/4/16.
 */
@Service
public class QueueService {

    @Autowired
    SqsAwsConfiguration sqsAwsConfiguration;

    public String createQueue(QueueRequest queueRequest){
        //create a queue
        System.out.println("Creating a new SQS queue called Sweta Queue.\n");
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueRequest.queueName);
        String myQueueUrl = sqsAwsConfiguration.config().createQueue(createQueueRequest).getQueueUrl();
        System.out.println("Your queue url is "+myQueueUrl);
        System.out.println();
        return myQueueUrl;
    }

   /* public void deleteQueue(QueueRequest queueRequest){
        // Delete a queue
        System.out.println("Deleting the test queue.\n");
        sqsAwsConfiguration.config().deleteQueue(new DeleteQueueRequest(myQueueUrl));

    }*/

    public void sendMessage(MessageRequest messageRequest){
        System.out.println("Sending a message to SwetaQueue.\n");
        sqsAwsConfiguration.config().sendMessage(new SendMessageRequest(messageRequest.queueUrl, messageRequest.emailId));
        System.out.println();

        // Receive messages
        System.out.println("Receiving messages from MyQueue.\n");
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(messageRequest.queueUrl);
        List<Message> messages = sqsAwsConfiguration.config().receiveMessage(receiveMessageRequest).getMessages();
        System.out.println(messages.size()+"------size-------");
        for (Message message : messages) {
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
        System.out.println();
    }
}
