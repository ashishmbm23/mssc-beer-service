package com.ashish.msscbeerservice.web.v1.mapper;

import com.ashish.common.model.BeerDto;
import com.ashish.msscbeerservice.entity.Beer;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    BeerDto convertBeerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    Beer convertBeerDtoToBeer(BeerDto dto);
}
