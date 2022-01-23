package com.gorillas.order.delivery.receiving.exception;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

/**
 * @author Shahzaib Ali Khan
 * @since 22-01-2022
 * This class will be responsible to handle delivery not found exception
 */

public class DeliveryNotFoundException extends RuntimeException implements GraphQLError {

    private String deliveryId;
    
    public DeliveryNotFoundException(String message, String deliveryId) {
        super(message);
        this.deliveryId = deliveryId;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
	@Override
	public List<SourceLocation> getLocations() {

		return null;
	}

	@Override
	public ErrorType getErrorType() {
		
		return null;
	}
	
    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("deliveryId", deliveryId);
    }

}
