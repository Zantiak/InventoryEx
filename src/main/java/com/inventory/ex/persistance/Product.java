package com.inventory.ex.persistance;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Entity class for the PRODUCT table.
 */
@Data
@Entity
@Table(name="PRODUCT")
public class Product {

    /**
     * Product code identifier, table PRIMARY KEY.
     */
    @Id
    @Column(name = "PRODUCT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    /**
     * Product brand identifier.
     */
    @Column(name = "BRAND_ID", nullable = false)
    private int brandId;

    /**
     * List of Prices associated with the product.
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Price> prices;
}
