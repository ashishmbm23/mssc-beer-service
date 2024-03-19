package com.ashish.msscbeerservice.web.v1.mapper;

import com.ashish.msscbeerservice.entity.Beer;
import com.ashish.msscbeerservice.web.v1.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    BeerDto convertBeerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    Beer convertBeerDtoToBeer(BeerDto dto);
}
