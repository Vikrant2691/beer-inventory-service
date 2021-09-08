package com.brewery.beerinventoryservice.web.mapper;


import brewery.model.BeerInventoryDto;
import com.brewery.beerinventoryservice.domain.BeerInventory;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerInventoryMapper {

    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO);

    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);
}