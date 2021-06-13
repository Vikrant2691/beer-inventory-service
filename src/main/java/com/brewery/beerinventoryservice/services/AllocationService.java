package com.brewery.beerinventoryservice.services;

import com.brewery.beerinventoryservice.brewery.model.BeerOrderDto;


public interface AllocationService {

    Boolean allocateOrder(BeerOrderDto beerOrderDto);

}
