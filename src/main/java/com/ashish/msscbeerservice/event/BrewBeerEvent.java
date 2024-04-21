package com.ashish.msscbeerservice.event;

import com.ashish.msscbeerservice.web.v1.model.BeerDto;

public class BrewBeerEvent extends BeerEvent{
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
