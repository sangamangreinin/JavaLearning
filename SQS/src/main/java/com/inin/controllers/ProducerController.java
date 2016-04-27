package com.inin.controllers;

import com.inin.controllers.dto.ProducerRequest;
import com.inin.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by root on 26/4/16.
 */
@RestController
public class ProducerController {

    @Autowired
    ProducerService producerService;

    @RequestMapping(method = RequestMethod.POST, path = "/sqs/producers", consumes = "application/json")
    public ResponseEntity sendMessage(@RequestBody ProducerRequest producerRequest){
        producerService.sendMessage(producerRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
