package com.ashish.msscbeerservice.web.v1.mapper;

import com.ashish.msscbeerservice.entity.Beer;
import com.ashish.msscbeerservice.service.inventory.BeerInventoryService;
import com.ashish.msscbeerservice.web.v1.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper{

    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setMapper(BeerMapper mapper) {

        this.mapper = mapper;
    }

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService){
        this.beerInventoryService = beerInventoryService;
    }

    @Override
    public BeerDto convertBeerToBeerDto(Beer beer) {

        return mapper.convertBeerToBeerDto(beer);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto dto = mapper.convertBeerToBeerDto(beer);
        dto.setMinOnHand(beerInventoryService.getOnhandInventory(beer.getId()) + 0L);
        return dto;
    }

}
