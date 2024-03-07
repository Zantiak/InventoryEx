package com.inventory.ex.repository;

import com.inventory.ex.persistance.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * Repository class to store, retrieve and search information for Product entity.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * This method returns the product's price information for the specified date.
     *
     * @param productId Product code identifier.
     * @param brandId Product brand identifier.
     * @param date and time to apply the product price.
     * @return Optional of Product with the Product price information, or an empty object if not found.
     */
    @Query("SELECT p FROM Product p JOIN FETCH p.prices pr " +
            "WHERE p.productId = :productId AND p.brandId = :brandId AND :date BETWEEN pr.startDate AND pr.endDate")
    Optional<Product> findByProductIdBrandIdAndPriceDate(@Param("productId") long productId,
                                                         @Param("brandId") int brandId, @Param("date") Timestamp date);

}
