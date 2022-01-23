package com.gorillas.order.delivery.receiving.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gorillas.order.delivery.receiving.service.GraphQLService;

import graphql.ExecutionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shahzaib Ali Khan
 * @since 22-01-2022
 * This class will be responsible to handle all requests regarding deliveries
 */

@RequestMapping("/api/deliveries")
@RestController
public class DeliveryController {

    Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    @Autowired
    GraphQLService graphQLService;

    @PostMapping
    public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
    	
    	logger.info("Executing Query:::: {} ", query);
    	try {
            ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);

            // Check if there are errors
            if(!executionResult.getErrors().isEmpty()){
                return new ResponseEntity<>(executionResult.getErrors().get(0).getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(executionResult, HttpStatus.OK);
    		
    	}catch(Exception e) {
    		logger.error(e.getMessage());
    	}
    	return null;
    }
}
