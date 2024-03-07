package com.inventory.ex.service;

import com.inventory.ex.dto.request.ProductPriceRequest;
import com.inventory.ex.dto.response.ProductPriceResponse;
import com.inventory.ex.persistance.Price;
import com.inventory.ex.persistance.Product;
import com.inventory.ex.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    /*
        This Test covers the happy path, where we get the expected Product information from the repository
     */
    @Test
    public void findByProductIdBrandIdAndPriceDateTestPass(){

        // Create the Product expected and a Price for the Product
        LocalDateTime startDate = LocalDateTime.parse("2024-01-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime endDate = LocalDateTime.parse("2024-06-01T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        Price expectedPrice = new Price();
        expectedPrice.setPrice(1.0d);
        expectedPrice.setPriceId(1);
        expectedPrice.setPriority(1);
        expectedPrice.setCurrency("EUR");
        expectedPrice.setStartDate(Timestamp.valueOf(startDate));
        expectedPrice.setEndDate(Timestamp.valueOf(endDate));

        List<Price> productPrices = new ArrayList<>();
        productPrices.add(expectedPrice);

        Product expectedProduct = new Product();
        expectedProduct.setProductId(1l);
        expectedProduct.setBrandId(1);
        expectedProduct.setPrices(productPrices);

        Optional<Product> optionalProduct = Optional.of(expectedProduct);

        // Create the Date as Timestamp to send to the service
        LocalDateTime dateToQuery = LocalDateTime.parse("2024-06-01T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Timestamp timestampToQuery = Timestamp.valueOf(dateToQuery);

        // Mock the behavior of the productRepository
        when(productRepository.findByProductIdBrandIdAndPriceDate(1, 1, timestampToQuery))
                .thenReturn(optionalProduct);

        // Create the request object
        ProductPriceRequest productPriceRequest = new ProductPriceRequest();
        productPriceRequest.setApplicationDate(timestampToQuery);
        productPriceRequest.setProductId(1);
        productPriceRequest.setBrandId(1);

        Optional<ProductPriceResponse> returnedOptional = productService.getProductPricesByDate(productPriceRequest);

        // Validate the response with the corresponding assertions
        assertNotNull(returnedOptional.get());
        assertEquals(optionalProduct.get().getProductId(), returnedOptional.get().getProductId());
        assertEquals(optionalProduct.get().getPrices().get(0).getPrice(), returnedOptional.get().getPrice());
        assertEquals(optionalProduct.get().getPrices().get(0).getStartDate(), returnedOptional.get().getStartDate());
        assertEquals(optionalProduct.get().getPrices().get(0).getEndDate(), returnedOptional.get().getEndDate());
    }


    /*
        This Test covers the scenario when the query has found no product.
    */
    @Test
    public void findByProductIdBrandIdAndPriceDateTestFailNotFound(){

        Optional<Product> optionalProduct = Optional.empty();

        // Create the Date as Timestamp to send to the service
        LocalDateTime dateToQuery = LocalDateTime.parse("2024-06-01T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Timestamp timestampToQuery = Timestamp.valueOf(dateToQuery);

        // Mock the behavior of the productRepository when there are no results for the specified Product
        when(productRepository.findByProductIdBrandIdAndPriceDate(1, 1, timestampToQuery))
                .thenReturn(optionalProduct);

        // Create the request object
        ProductPriceRequest productPriceRequest = new ProductPriceRequest();
        productPriceRequest.setApplicationDate(timestampToQuery);
        productPriceRequest.setProductId(1);
        productPriceRequest.setBrandId(1);

        Optional<ProductPriceResponse> returnedOptional = productService.getProductPricesByDate(productPriceRequest);

        // Validate the response with the corresponding assertion
        assertEquals(returnedOptional.get(), new ProductPriceResponse());
    }


    /*
        This Test covers the scenario when a product id has not been provided to the Repository.
    */
    @Test
    public void findByProductIdBrandIdAndPriceDateTestFailMissingProductId(){

        Optional<Product> optionalProduct = Optional.empty();

        // Create the Date as Timestamp to send to the service
        LocalDateTime dateToQuery = LocalDateTime.parse("2024-06-01T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Timestamp timestampToQuery = Timestamp.valueOf(dateToQuery);

        // Mock the behavior of the productRepository when there are no results for the specified Product
        when(productRepository.findByProductIdBrandIdAndPriceDate(0, 1, timestampToQuery))
                .thenReturn(optionalProduct);

        // Create the request object
        ProductPriceRequest productPriceRequest = new ProductPriceRequest();
        productPriceRequest.setApplicationDate(timestampToQuery);
        productPriceRequest.setProductId(1);
        productPriceRequest.setBrandId(1);

        Optional<ProductPriceResponse> returnedOptional = productService.getProductPricesByDate(productPriceRequest);

        // Validate the response with the corresponding assertions
        assertEquals(returnedOptional.get(), new ProductPriceResponse());
    }


    /*
        This Test covers the scenario when a Date is out of a valid range.
    */
    @Test
    public void findByProductIdBrandIdAndPriceDateTestFailIncorrectDate(){

        Optional<Product> optionalProduct = Optional.empty();

        // Create the Date as Timestamp to send to the service
        LocalDateTime dateToQuery = LocalDateTime.parse("0001-02-28T12:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Timestamp timestampToQuery = Timestamp.valueOf(dateToQuery);

        // Mock the behavior of the productRepository when there are no results for the specified Product
        when(productRepository.findByProductIdBrandIdAndPriceDate(0, 1, timestampToQuery))
                .thenReturn(optionalProduct);

        // Create the request object
        ProductPriceRequest productPriceRequest = new ProductPriceRequest();
        productPriceRequest.setApplicationDate(timestampToQuery);
        productPriceRequest.setProductId(1);
        productPriceRequest.setBrandId(1);

        Optional<ProductPriceResponse> returnedOptional = productService.getProductPricesByDate(productPriceRequest);

        // Validate the response with the corresponding assertion
        assertEquals(returnedOptional.get(), new ProductPriceResponse());
    }
}