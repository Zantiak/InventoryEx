package com.inventory.ex.service;

import com.inventory.ex.dto.request.ProductPriceRequest;
import com.inventory.ex.dto.response.ProductPriceResponse;
import com.inventory.ex.persistance.Price;
import com.inventory.ex.persistance.Product;
import com.inventory.ex.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

/**
 * This is the Service class supporting the Product endpoint.
 */
@Service
public class ProductService {

    /**
     * The class retrieving the information from the Data Base.
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * This method returns the product's price information for the specified date.
     *
     * @param productPriceRequest The request object with the required parameters.
     * @return An Optional of ProductPriceResponse with the Product price information, or an empty object if not found.
     */
    public Optional<ProductPriceResponse> getProductPricesByDate(ProductPriceRequest productPriceRequest){

        Optional<Product> optionalProduct =
                productRepository.findByProductIdBrandIdAndPriceDate(productPriceRequest.getProductId(),
                        productPriceRequest.getBrandId(), productPriceRequest.getApplicationDate());
        ProductPriceResponse productPriceResponse = new ProductPriceResponse();

        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            // Set the values in ProductPriceResponse DTO
            productPriceResponse.setProductId(product.getProductId());
            productPriceResponse.setBrandId(product.getBrandId());

            // In case we have more than 1 Price, get the price with the highest priority
            Optional<Price> optionalPrice = product.getPrices().stream()
                    .max(Comparator.comparingInt(Price::getPriority));

            productPriceResponse.setPriceList(optionalPrice.isPresent() ? optionalPrice.get().getPriceId() : 0);
            productPriceResponse.setPrice(optionalPrice.isPresent() ? optionalPrice.get().getPrice() : 0.0);
            productPriceResponse.setStartDate(optionalPrice.isPresent() ? optionalPrice.get().getStartDate() : null);
            productPriceResponse.setEndDate(optionalPrice.isPresent() ? optionalPrice.get().getEndDate() : null);
        }

        return Optional.of(productPriceResponse);
    }
}
