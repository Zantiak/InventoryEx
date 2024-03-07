package com.inventory.ex.controller;

import com.inventory.ex.dto.request.ProductPriceRequest;
import com.inventory.ex.dto.response.ProductPriceResponse;
import com.inventory.ex.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @MockBean
    ProductService productService;

    @Autowired
    ProductController productController;

    /*
        This Test covers the happy path, when we request the ProductController a Product Price
     */
    @Test
    public void getProductPricePass(){

        // Create the Date as Timestamp to send to the controller
        LocalDateTime dateToQuery = LocalDateTime.parse("2024-06-14T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Timestamp timestampToQuery = Timestamp.valueOf(dateToQuery);

        // Generate the object request
        ProductPriceRequest productPriceRequest = new ProductPriceRequest();
        productPriceRequest.setProductId(35455l);
        productPriceRequest.setBrandId(1);
        productPriceRequest.setApplicationDate(timestampToQuery);

        // Generate the mock response
        ProductPriceResponse productPriceResponse = new ProductPriceResponse();
        productPriceResponse.setPriceList(1);
        productPriceResponse.setPrice(10.0);
        productPriceResponse.setProductId(1l);
        productPriceResponse.setBrandId(1);
        productPriceResponse.setStartDate(timestampToQuery);
        productPriceResponse.setEndDate(timestampToQuery);

        // Mock the response of ProductService
        when(productService.getProductPricesByDate(any(ProductPriceRequest.class))).thenReturn(Optional.of(productPriceResponse));

        ResponseEntity<ProductPriceResponse> productPriceResponseEntity = productController.getProductPrice(productPriceRequest);

        // Validate the response with the corresponding assertion
        assertEquals(productPriceResponseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(productPriceResponseEntity.getBody(), productPriceResponse);
    }


    /*
        This Test covers the scenario when the query has found no product price.
    */
    @Test
    public void getProductPricePassEmptyResponse(){

        // Create the Date as Timestamp to send to the controller
        LocalDateTime dateToQuery = LocalDateTime.parse("2024-06-14T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Timestamp timestampToQuery = Timestamp.valueOf(dateToQuery);

        // Generate the object request
        ProductPriceRequest productPriceRequest = new ProductPriceRequest();
        productPriceRequest.setProductId(35455l);
        productPriceRequest.setBrandId(1);
        productPriceRequest.setApplicationDate(timestampToQuery);

        // Generate the mock response
        ProductPriceResponse productPriceResponse = new ProductPriceResponse();

        // Mock the response of ProductService
        when(productService.getProductPricesByDate(any(ProductPriceRequest.class))).thenReturn(Optional.of(productPriceResponse));

        ResponseEntity<ProductPriceResponse> productPriceResponseEntity = productController.getProductPrice(productPriceRequest);

        // Validate the response with the corresponding assertion
        assertEquals(productPriceResponseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(productPriceResponseEntity.getBody(), productPriceResponse);
    }
}
