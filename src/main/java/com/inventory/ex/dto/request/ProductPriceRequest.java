package com.inventory.ex.dto.request;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Timestamp;

/**
 * ProductPriceRequest class with the necessary fields to get the Product price information.
 */
@Data
public class ProductPriceRequest {

    /**
     * Product code identifier.
     */
    @NotNull
    private int productId;

    /**
     * Product brand identifier.
     */
    @NotNull
    private int brandId;

    /**
     * Date and time to apply the product price.
     */
    @NotNull
    private Timestamp applicationDate;


}
