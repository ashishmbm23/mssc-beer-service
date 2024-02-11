package com.ashish.msscbeerservice.mapper;

import com.ashish.msscbeerservice.entity.Beer;
import com.ashish.msscbeerservice.web.v1.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper//(componentModel = "spring")
public interface BeerMapper {

    BeerDto convertBeerToBeerDto(Beer beer);

    Beer convertBeerDtoToBeer(BeerDto beerDto);
}
