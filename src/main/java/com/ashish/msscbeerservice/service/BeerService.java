package com.ashish.msscbeerservice.service;

import com.ashish.msscbeerservice.web.v1.model.BeerDto;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<BeerDto> getBeers();

    BeerDto getBeerById(UUID id);

    BeerDto getBeerByUpc(String upc);

    BeerDto createBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID id, BeerDto beerDto);

    void deleteBeer(UUID id);
}
