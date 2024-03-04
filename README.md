# InventoryEx-App
Microservice designed to get the product prices for a specified application date.

## Getting Started
This is a Spring-boot Java microservice.

### Technologies
To run this project.

* Java version "17" or later.
* Gradle version "7.3" or later.

### Installing
1. Clone to your local repository.
```
$git clone https://github.com/Zantiak/InventoryEx.git
```

2. Navigate to the InventoryEx directory.

```
$cd /[my_repositories_dir]/InventoryEx/
```

3. Execute Gradle command to build the project.
```
$./gradlew build
```

4. Once the project is built successfully, you can run the Spring Boot application with the command.
```
$./gradlew bootRun
```

5. Verify the application is running by typing in your browser the URL shown below
```
http://localhost:8080/product/healthcheck
```

### Available Endpoints.
This application has one endpoint to get information from the embedded database. This service provides a product list price for the specified date.

The service can be tested using a tool for API testing like Postman.

The service is at:
```
POST http://localhost:8080/product/price
```

The endpoint receives a request object with the fields below
```
{
"productId": 35455,
"brandId": 1,
"applicationDate": "2020-06-16T21:00:00"
}
```
And if everything is properly configured, expect a response similar to:
```
{
"productId": 35455,
"brandId": 1,
"priceList": 4,
"startDate": "2020-06-15T16:00:00.000+00:00",
"endDate": "2020-12-31T23:59:59.000+00:00",
"price": 38.95
}
```

## Authors

* **Israel Santiago**