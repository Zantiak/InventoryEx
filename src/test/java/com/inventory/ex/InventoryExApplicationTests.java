package com.inventory.ex;

import com.inventory.ex.dto.request.ProductPriceRequest;
import com.inventory.ex.dto.response.ProductPriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class to run the integration tests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryExApplicationTests {

	/**
	 * HTTP server port allocated at runtime to run the tests.
	 */
	@LocalServerPort
	private int localPort;

	/**
	 * Synchronous client to perform HTTP requests on integration tests.
	 */
	@Autowired
	private TestRestTemplate testRestTemplate;

	/**
	 * Integration test to verify that the application is up and running.
	 */
	@Test
	public void testHealthCheck(){
		assertEquals("Service is running",
				testRestTemplate.getForObject("http://localhost:" + localPort + "/product/healthcheck",
						String.class));

	}

	/**
	 * Integration test to validate the 1st scenario.
	 * Input request:
	 * 	ProductPriceRequest[productId:35455, brandId:1, applicationDate:"2020-06-14T10:00:00"]
	 * Output response:
	 * 	ProductPriceResponse[productId:35455, brandId:1, priceList:1, startDate:"2020-06-14T00:00:00"
	 * 							,endDate:"2020-12-31T23:59:59", price:35.5]
	 */
	@Test
	public void testProductPriceScenario1() {

		// Create the expected object
		LocalDateTime startDate = LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		LocalDateTime endDate = LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		ProductPriceResponse expectedResponse = new ProductPriceResponse();
		expectedResponse.setProductId(35455);
		expectedResponse.setPrice(35.5d);
		expectedResponse.setBrandId(1);
		expectedResponse.setPriceList(1);
		expectedResponse.setStartDate(Timestamp.valueOf(startDate));
		expectedResponse.setEndDate(Timestamp.valueOf(endDate));

		// Parse the string date into a LocalDateTime object
		LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		ProductPriceRequest productPriceRequest = new ProductPriceRequest();
		productPriceRequest.setProductId(35455);
		productPriceRequest.setBrandId(1);
		productPriceRequest.setApplicationDate(Timestamp.valueOf(applicationDate));

		// Create request entity
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ProductPriceRequest> requestEntity = new HttpEntity<>(productPriceRequest, headers);

		// Make HTTP request
		ResponseEntity<ProductPriceResponse> response = testRestTemplate.exchange("http://localhost:" + localPort + "/product/price",
				HttpMethod.POST, requestEntity, ProductPriceResponse.class);

		// Assert response
		assertEquals(expectedResponse, response.getBody(), "Error in test: testProductPriceScenario1");
	}


	/**
	 * Integration test to validate the 2nd scenario.
	 * Input request:
	 * 	ProductPriceRequest[productId:35455, brandId:1, applicationDate:"2020-06-14T16:00:00"]
	 * Output response:
	 * 	ProductPriceResponse[productId:35455, brandId:1, priceList:2, startDate:"2020-06-14T15:00:00"
	 * 							,endDate:"2020-06-14T18:30:00", price:25.45]
	 */
	@Test
	public void testProductPriceScenario2() {

		// Create the expected object
		LocalDateTime startDate = LocalDateTime.parse("2020-06-14T15:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		LocalDateTime endDate = LocalDateTime.parse("2020-06-14T18:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		ProductPriceResponse expectedResponse = new ProductPriceResponse();
		expectedResponse.setProductId(35455);
		expectedResponse.setPrice(25.45d);
		expectedResponse.setBrandId(1);
		expectedResponse.setPriceList(2);
		expectedResponse.setStartDate(Timestamp.valueOf(startDate));
		expectedResponse.setEndDate(Timestamp.valueOf(endDate));

		// Parse the string date into a LocalDateTime object
		LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T16:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		ProductPriceRequest productPriceRequest = new ProductPriceRequest();
		productPriceRequest.setProductId(35455);
		productPriceRequest.setBrandId(1);
		productPriceRequest.setApplicationDate(Timestamp.valueOf(applicationDate));

		// Create request entity
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ProductPriceRequest> requestEntity = new HttpEntity<>(productPriceRequest, headers);

		// Make HTTP request
		ResponseEntity<ProductPriceResponse> response = testRestTemplate.exchange("http://localhost:" + localPort + "/product/price",
				HttpMethod.POST, requestEntity, ProductPriceResponse.class);

		// Assert response
		assertEquals(expectedResponse, response.getBody(), "Error in test: testProductPriceScenario2");
	}


	/**
	 * Integration test to validate the 3rd scenario.
	 * Input request:
	 * 	ProductPriceRequest[productId:35455, brandId:1, applicationDate:"2020-06-14T21:00:00"]
	 * Output response:
	 * 	ProductPriceResponse[productId:35455, brandId:1, priceList:1, startDate:"2020-06-14T00:00:00"
	 * 							,endDate:"2020-12-31T23:59:59", price:35.5]
	 */
	@Test
	public void testProductPriceScenario3() {

		// Create the expected object
		LocalDateTime startDate = LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		LocalDateTime endDate = LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		ProductPriceResponse expectedResponse = new ProductPriceResponse();
		expectedResponse.setProductId(35455);
		expectedResponse.setPrice(35.5d);
		expectedResponse.setBrandId(1);
		expectedResponse.setPriceList(1);
		expectedResponse.setStartDate(Timestamp.valueOf(startDate));
		expectedResponse.setEndDate(Timestamp.valueOf(endDate));

		// Parse the string date into a LocalDateTime object
		LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T21:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		ProductPriceRequest productPriceRequest = new ProductPriceRequest();
		productPriceRequest.setProductId(35455);
		productPriceRequest.setBrandId(1);
		productPriceRequest.setApplicationDate(Timestamp.valueOf(applicationDate));

		// Create request entity
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ProductPriceRequest> requestEntity = new HttpEntity<>(productPriceRequest, headers);

		// Make HTTP request
		ResponseEntity<ProductPriceResponse> response = testRestTemplate.exchange("http://localhost:" + localPort + "/product/price",
				HttpMethod.POST, requestEntity, ProductPriceResponse.class);

		// Assert response
		assertEquals(expectedResponse, response.getBody(), "Error in test: testProductPriceScenario3");
	}


	/**
	 * Integration test to validate the 4th scenario.
	 * Input request:
	 * 	ProductPriceRequest[productId:35455, brandId:1, applicationDate:"2020-06-15T10:00:00"]
	 * Output response:
	 * 	ProductPriceResponse[productId:35455, brandId:1, priceList:3, startDate:"2020-06-15T00:00:00"
	 * 							,endDate:"2020-06-15T11:00:00", price:30.5]
	 */
	@Test
	public void testProductPriceScenario4() {

		// Create the expected object
		LocalDateTime startDate = LocalDateTime.parse("2020-06-15T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		LocalDateTime endDate = LocalDateTime.parse("2020-06-15T11:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		ProductPriceResponse expectedResponse = new ProductPriceResponse();
		expectedResponse.setProductId(35455);
		expectedResponse.setPrice(30.5d);
		expectedResponse.setBrandId(1);
		expectedResponse.setPriceList(3);
		expectedResponse.setStartDate(Timestamp.valueOf(startDate));
		expectedResponse.setEndDate(Timestamp.valueOf(endDate));

		// Parse the string date into a LocalDateTime object
		LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		ProductPriceRequest productPriceRequest = new ProductPriceRequest();
		productPriceRequest.setProductId(35455);
		productPriceRequest.setBrandId(1);
		productPriceRequest.setApplicationDate(Timestamp.valueOf(applicationDate));

		// Create request entity
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ProductPriceRequest> requestEntity = new HttpEntity<>(productPriceRequest, headers);

		// Make HTTP request
		ResponseEntity<ProductPriceResponse> response = testRestTemplate.exchange("http://localhost:" + localPort + "/product/price",
				HttpMethod.POST, requestEntity, ProductPriceResponse.class);

		// Assert response
		assertEquals(expectedResponse, response.getBody(), "Error in test: testProductPriceScenario4");
	}

	/**
	 * Integration test to validate the 5th scenario.
	 * Input request:
	 * 	ProductPriceRequest[productId:35455, brandId:1, applicationDate:"2020-06-16T21:00:00"]
	 * Output response:
	 * 	ProductPriceResponse[productId:35455, brandId:1, priceList:4, startDate:"2020-06-15T16:00:00"
	 * 							,endDate:"2020-12-31T23:59:59", price:38.95]
	 */
	@Test
	public void testProductPriceScenario5() {

		// Create the expected object
		LocalDateTime startDate = LocalDateTime.parse("2020-06-15T16:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		LocalDateTime endDate = LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		ProductPriceResponse expectedResponse = new ProductPriceResponse();
		expectedResponse.setProductId(35455);
		expectedResponse.setPrice(38.95d);
		expectedResponse.setBrandId(1);
		expectedResponse.setPriceList(4);
		expectedResponse.setStartDate(Timestamp.valueOf(startDate));
		expectedResponse.setEndDate(Timestamp.valueOf(endDate));

		// Parse the string date into a LocalDateTime object
		LocalDateTime applicationDate = LocalDateTime.parse("2020-06-16T21:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		ProductPriceRequest productPriceRequest = new ProductPriceRequest();
		productPriceRequest.setProductId(35455);
		productPriceRequest.setBrandId(1);
		productPriceRequest.setApplicationDate(Timestamp.valueOf(applicationDate));

		// Create request entity
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ProductPriceRequest> requestEntity = new HttpEntity<>(productPriceRequest, headers);

		// Make HTTP request
		ResponseEntity<ProductPriceResponse> response = testRestTemplate.exchange("http://localhost:" + localPort + "/product/price",
				HttpMethod.POST, requestEntity, ProductPriceResponse.class);

		// Assert response
		assertEquals(expectedResponse, response.getBody(), "Error in test: testProductPriceScenario5");
	}

}
