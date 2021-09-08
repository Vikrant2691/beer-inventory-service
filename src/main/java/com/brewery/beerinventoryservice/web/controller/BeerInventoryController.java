package com.brewery.beerinventoryservice.web.controller;

import brewery.model.BeerInventoryDto;
import com.brewery.beerinventoryservice.repository.BeerInventoryRepository;
import com.brewery.beerinventoryservice.web.mapper.BeerInventoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * Created by jt on 2019-05-31.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerInventoryController {

    private final BeerInventoryRepository beerInventoryRepository;
    private final BeerInventoryMapper beerInventoryMapper;

    @GetMapping("api/v1/beer/{beerId}/inventory")
    List<BeerInventoryDto> listBeersById(@PathVariable UUID beerId){
        log.debug("Finding Inventory for beerId:" + beerId);

        return beerInventoryRepository.findAllBeerById(beerId)
                .stream()
                .map(beerInventoryMapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
    }

    @GetMapping("api/v1/beer/upc/{beerUPC}/inventory")
    List<BeerInventoryDto> listBeersByUPC(@PathVariable String beerUPC){
        log.debug("Finding Inventory for beerId:" + beerUPC);

        return beerInventoryRepository.findAllBeerByUpc(beerUPC)
                .stream()
                .map(beerInventoryMapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
    }
}