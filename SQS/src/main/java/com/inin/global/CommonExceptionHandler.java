package com.inin.global;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Common exception for queue and message controller, The AmazonServiceException and  AmazonClientException will get caught here
 */
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(AmazonServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleAmazonServiceException(AmazonServiceException ase){
        System.out.println("Caught an AmazonServiceException, which means your request made it " +
                "to Amazon SQS, but was rejected with an error response for some reason.");
        System.out.println("Error Message:    " + ase.getMessage());
        System.out.println("HTTP Status Code: " + ase.getStatusCode());
        System.out.println("AWS Error Code:   " + ase.getErrorCode());
        System.out.println("Error Type:       " + ase.getErrorType());
        System.out.println("Request ID:       " + ase.getRequestId());
    }

    @ExceptionHandler(AmazonClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleAmazonClientException(AmazonClientException ace){
        System.out.println("Caught an AmazonClientException, which means the client encountered " +
                "a serious internal problem while trying to communicate with SQS, such as not " +
                "being able to access the network.");
        System.out.println("Error Message: " + ace.getMessage());
    }
}
