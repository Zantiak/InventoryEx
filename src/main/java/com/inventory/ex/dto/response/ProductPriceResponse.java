package com.inventory.ex.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductPriceResponse {

    /**
     * Product code identifier.
     */
    private long productId;

    /**
     * Product brand identifier.
     */
    private int brandId;

    /**
     * Applicable price schedule identifier.
     */
    private long priceList;

    /**
     * Start date on which the indicated rate price applies.
     */
    private Timestamp startDate;

    /**
     * End date on which the indicated rate price applies.
     */
    private Timestamp endDate;

    /**
     * Final sale price.
     */
    private double price;

}
