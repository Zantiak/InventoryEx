package com.inventory.ex.controller;

import com.inventory.ex.dto.request.ProductPriceRequest;
import com.inventory.ex.dto.response.ProductPriceResponse;
import com.inventory.ex.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * This is the Controller class for the Product endpoint.
 */
@RequestMapping(value = "product")
@RestController
@Validated
public class ProductController {

    /**
     * This is the business logic class supporting Product endpoint.
     */
    @Autowired
    private ProductService productService;

    /**
     * This method retrieves a message to verify that the application is running.
     *
     * @return String message indicating that the service is running.
     */
    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "Service is running";
    }


    /**
     * This method returns the product's price to apply to the specified date.
     *
     * @param productPriceRequest The request object with the required parameters.
     * @return The retrieved ResponseEntity with the information requested, or an empty object if not found.
     */
    @PostMapping("/price")
    public ResponseEntity<ProductPriceResponse> getProductPrice(@RequestBody ProductPriceRequest productPriceRequest){

        Optional<ProductPriceResponse> optionalProductPrice =
                productService.getProductPricesByDate(productPriceRequest);
        ProductPriceResponse productPriceResponse = optionalProductPrice.get();

        return new ResponseEntity<>(productPriceResponse, HttpStatus.OK);
    }
}
