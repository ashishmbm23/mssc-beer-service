package com.ashish.msscbeerservice.mapper;

import com.ashish.msscbeerservice.entity.Beer;
import com.ashish.msscbeerservice.web.v1.model.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper//(componentModel = "spring")
public interface BeerMapper {
    BeerMapper BEER_MAPPER = Mappers.getMapper(BeerMapper.class);

    BeerDto convertBeerToBeerDto(Beer beer);

    Beer convertBeerDtoToBeer(BeerDto beerDto);
}
