package com.brewery.beerinventoryservice.services;

import brewery.model.BeerOrderDto;


public interface AllocationService {

    Boolean allocateOrder(BeerOrderDto beerOrderDto);

    void deallocateOrder(BeerOrderDto beerOrderDto);

}
