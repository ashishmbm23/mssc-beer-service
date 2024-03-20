package com.ashish.msscbeerservice.service;

import com.ashish.msscbeerservice.web.v1.model.BeerDto;
import com.ashish.msscbeerservice.web.v1.model.BeerPagedList;
import com.ashish.msscbeerservice.web.v1.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<BeerDto> getBeers();

    BeerDto getBeerById(UUID id, Boolean showInventoryByHand);

    BeerDto getBeerByUpc(String upc, Boolean showInventoryByHand);

    BeerDto createBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID id, BeerDto beerDto);

    void deleteBeer(UUID id);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventoryOnHand);

}
