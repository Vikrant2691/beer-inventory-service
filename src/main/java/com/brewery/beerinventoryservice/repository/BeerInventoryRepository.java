package com.brewery.beerinventoryservice.repository;


import com.brewery.beerinventoryservice.domain.BeerInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BeerInventoryRepository extends JpaRepository<BeerInventory, UUID> {

    List<BeerInventory> findAllBeerById(UUID beerId);

    List<BeerInventory> findAllBeerByUpc(String upc);
}
