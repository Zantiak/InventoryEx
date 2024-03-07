package com.inventory.ex.repository;

import com.inventory.ex.persistance.Price;
import com.inventory.ex.persistance.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.properties.hibernate.jdbc.time_zone=UTC"
})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceRepository priceRepository;

    /*
        This Test covers the happy path, where we get the expected Product information from the repository
    */
    @Test
    public void findByProductIdBrandIdAndPriceDateTestPass(){

        // Get the Product entity through the PriceId
        Optional<Price> returnedPrice = priceRepository.findById(1l);

        // Create the Date as Timestamp to send to the service
        LocalDateTime dateToQuery = LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Timestamp timestampToQuery = Timestamp.valueOf(dateToQuery);

        // Execute the method to get Product Price by date
        Optional<Product> returnedOptional = productRepository.findByProductIdBrandIdAndPriceDate(35455, 1,
                timestampToQuery);

        assertNotNull(returnedOptional.get());
        assertEquals(returnedPrice.get().getProduct(), returnedOptional.get());
    }


    /*
        This Test covers the scenario where the ProductId exists, the PriceId exists but the queried Date is not
        between start and end date of the Price.
    */
    @Test
    public void findByProductIdBrandIdAndPriceDateTestFailIncorrectDate(){

        // Verify that the Product exists
        Optional<Product> returnedProduct = productRepository.findById(35455l);

        assertNotNull(returnedProduct.get());

        // Verify that the Price exists
        Optional<Price> returnedPrice = priceRepository.findById(1l);

        assertNotNull(returnedPrice.get());

        // Create the Date as Timestamp to send to the service
        LocalDateTime dateToQuery = LocalDateTime.parse("2020-06-13T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Timestamp timestampToQuery = Timestamp.valueOf(dateToQuery);

        Optional<Product> returnedOptional = productRepository.findByProductIdBrandIdAndPriceDate(35455l, 1,
                timestampToQuery);

        assertEquals(true, returnedOptional.isEmpty());
    }

}
