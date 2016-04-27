package com.inin.controllers;

import com.inin.controllers.dto.ConsumerRequest;
import com.inin.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by root on 27/4/16.
 */
@RestController
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @RequestMapping(method = RequestMethod.POST, path = "/sqs/consumers", consumes = "application/json")
    public ResponseEntity receiveMessage(@RequestBody ConsumerRequest consumerRequest){
        consumerService.receiveMessage(consumerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
}
