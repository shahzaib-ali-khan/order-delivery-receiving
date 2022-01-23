package com.gorillas.order.delivery.receiving.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gorillas.order.delivery.receiving.controller.DeliveryController;
import com.gorillas.order.delivery.receiving.model.Delivery;
import com.gorillas.order.delivery.receiving.repository.DeliveryRepository;
import com.gorillas.order.delivery.receiving.service.datafetcher.AllDeliveriesDataFetcher;
import com.gorillas.order.delivery.receiving.service.datafetcher.ReceiveDeliveryDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.core.io.Resource;

/**
 * @author Shahzaib Ali Khan
 * @since 22-01-2022
 * This class will be responsible to handle all stuff related to GraphQL
 */

@Service
public class GraphQLService {
	
    Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    @Autowired
    DeliveryRepository deliveryRepository;

    @Value("classpath:schema.graphql")
    Resource resource;

    @Value("${file.to.load}")
    String fileToLoad;
    
    private GraphQL graphQL;
    
    @Autowired
    private AllDeliveriesDataFetcher allDeliveriesDataFetcher;

    @Autowired
    private ReceiveDeliveryDataFetcher receiveDeliveryDataFetcher;

    // load schema and data at application start up
    @PostConstruct
    private void loadSchema() throws IOException {

        //Load Books into the Book Repository
        loadDataIntoH2();

    	logger.info("Loading schema file");

    	// get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoH2() throws IOException {

    	logger.info("Loading data into H2 database ");

        ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Delivery>> typeReference = new TypeReference<List<Delivery>>(){};
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileToLoad);
		List<Delivery> deliveries = mapper.readValue(inputStream,typeReference);
		
		deliveries.forEach(delivery -> {
			deliveryRepository.save(delivery);
        });
        
    }

    // Method for binding schema's query and mutation to the datafetcher classes
    private RuntimeWiring buildRuntimeWiring(){
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allDeliveries", allDeliveriesDataFetcher))
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("receiveDelivery", receiveDeliveryDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}