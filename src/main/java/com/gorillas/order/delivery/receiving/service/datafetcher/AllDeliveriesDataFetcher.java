package com.gorillas.order.delivery.receiving.service.datafetcher;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gorillas.order.delivery.receiving.model.Delivery;
import com.gorillas.order.delivery.receiving.repository.DeliveryRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author Shahzaib Ali Khan
 * @since 22-01-2022
 * This class will be responsible to retrieve all the received deliveries
 */

@Component
public class AllDeliveriesDataFetcher implements DataFetcher<List<Delivery>>{
    
	Logger logger = LoggerFactory.getLogger(ReceiveDeliveryDataFetcher.class);

    @Autowired
    DeliveryRepository deliveryRepository;

    @Override
    public List<Delivery> get(DataFetchingEnvironment dataFetchingEnvironment) {
        
    	logger.info("Retrieving the received deliveries");
    	return deliveryRepository.findByisReceivedTrue();
    }
}
