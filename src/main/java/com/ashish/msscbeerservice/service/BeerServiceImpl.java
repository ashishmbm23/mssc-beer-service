package com.ashish.msscbeerservice.service;

import com.ashish.msscbeerservice.mapper.BeerMapper;
import com.ashish.msscbeerservice.repository.BeerRepository;
import com.ashish.msscbeerservice.util.Constant;
import com.ashish.msscbeerservice.util.Util;
import com.ashish.msscbeerservice.web.v1.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    private final Util util;
    @Override
    public List<BeerDto> getBeers() {
        return beerRepository.findAll()
                .stream()
                .map( beer -> {
                    BeerDto beerDto = beerMapper.convertBeerToBeerDto(beer);
                    util.setUrl(beerDto);
                    return beerDto;
                }).collect(Collectors.toList());
    }

    @Override
    public BeerDto getBeerById(UUID id) {
        return null;
    }

    @Override
    public BeerDto getBeerByUpc(String upc) {
        return null;
    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        return null;
    }

    @Override
    public BeerDto updateBeer(UUID id, BeerDto beerDto) {
        return null;
    }

    @Override
    public void deleteBeer(UUID id) {

    }
}
