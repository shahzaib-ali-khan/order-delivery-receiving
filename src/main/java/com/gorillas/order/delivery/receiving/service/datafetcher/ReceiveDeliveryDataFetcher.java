package com.gorillas.order.delivery.receiving.service.datafetcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gorillas.order.delivery.receiving.exception.DeliveryNotFoundException;
import com.gorillas.order.delivery.receiving.model.Delivery;
import com.gorillas.order.delivery.receiving.repository.DeliveryRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author Shahzaib Ali Khan
 * @since 22-01-2022
 * This class will be responsible to mark delivery as received
 */

@Component
public class ReceiveDeliveryDataFetcher implements DataFetcher<Delivery>{

    Logger logger = LoggerFactory.getLogger(ReceiveDeliveryDataFetcher.class);
	
    @Autowired
    DeliveryRepository deliveryRepository;
    
	@Override
	public Delivery get(DataFetchingEnvironment dataFetchingEnvironment) {

		String deliveryId = dataFetchingEnvironment.getArgument("deliveryId");
		logger.info("Searching for delivery {}" +deliveryId);
		
		if(deliveryRepository.findById(deliveryId) == null) {
			logger.error("Passed in delivery not found");
			throw new DeliveryNotFoundException("Passed in delivery not found", deliveryId);
		}
	    Delivery delivery = deliveryRepository.findById(deliveryId).get();
	   	
	    delivery.setIsReceived(true);

		logger.info("Delivery marked as received");
	    return  deliveryRepository.saveAndFlush(delivery);

	}
}