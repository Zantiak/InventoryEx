package com.inventory.ex.repository;

import com.inventory.ex.persistance.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository class to store, retrieve and search information for Price entity.
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

}
