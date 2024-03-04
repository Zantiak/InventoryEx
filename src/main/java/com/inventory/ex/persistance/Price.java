package com.inventory.ex.persistance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Entity class for PRICE table.
 */
@Getter
@Setter
@Entity
@Table(name="PRICE")
public class Price {

    /**
     * Applicable price schedule identifier, table PRIMARY KEY.
     * Originally named as PRICE_LIST.
     */
    @Id
    @Column(name = "PRICE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long priceId;

    /**
     * Final sale price.
     */
    @Column(name = "PRICE", nullable = false)
    private double price;

    /**
     * Price rate application priority. The higher value has a higher priority.
     */
    @Column(name = "PRIORITY", nullable = false)
    private int priority;

    /**
     * Start date on which the indicated rate price applies.
     */
    @Column(name = "START_DATE", nullable = false)
    private Timestamp startDate;

    /**
     * End date on which the indicated rate price applies.
     */
    @Column(name = "END_DATE", nullable = false)
    private Timestamp endDate;

    /**
     * Currency code, ISO standardized.
     */
    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    /**
     * Product code identifier.
     */
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

}
