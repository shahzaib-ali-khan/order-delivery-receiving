# Order delivery receiving
This application implemented marking the delivery as received and listing all the received deliveries by using GraphQL and Spring boot framework. JSOn data file is saved in resource folder of the application and defined in properties file. For storing the provide JSON data, H2 database is used. Stored data can be viewed in H2 console by browsing

```sh
localhost:8080/h2-console
```

### Database credentials

| Field | Value |
| ------ | ------ |
| Driver Class | org.h2.Driver |
| JDBC URL | jdbc:h2:mem:orders |
| User Name | sa |
| Password | password |

Libraries and their version used for building the project:

## Libraries

| Name | Version |
| ------ | ------ |
| Java | "11.0.7" 2020-04-14 LTS.|
| Gradle | 7.3.2. |
| Spring boot framework | 2.6.2 |

## How to use

Open any IDE that can call APIs. One API endpoint is developed for both fetching all the received deliveries and marking the delivery as received. API endpoint is below and the method type is POST

```sh
http://localhost:8080/api/deliveries
```

## Query and mutation

Fields that can be retrieved are

| Name | Type |
| ------ | ------ |
| deliveryId | String|
| product | String |
| supplier | String |
| quantity | int |
| expectedDate | String |
| product | String |
| expectedWarehouse | String |

### Example query for retreiving the received delivery

```sh
query allDeliveries{
	allDeliveries{
		deliveryId
		product
		supplier
		quantity
	}
}
```

### Example Mutation for marking a delivery as received

```sh
mutation receiveDelivery{
	receiveDelivery(deliveryId: "101"){
		deliveryId
		product
		supplier
		quantity
	}
}
```

Whichever field you will pass will be returned in the response of execution of the query.
