package com.ashish.msscbeerservice.event;

import com.ashish.msscbeerservice.web.v1.model.BeerDto;

public class NewInventoryEvent extends BeerEvent{
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
