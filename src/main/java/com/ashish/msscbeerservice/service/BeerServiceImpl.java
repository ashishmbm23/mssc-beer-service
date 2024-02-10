package com.ashish.msscbeerservice.service;

import com.ashish.msscbeerservice.entity.Beer;
import com.ashish.msscbeerservice.exception.BeerNotFoundException;
import com.ashish.msscbeerservice.mapper.BeerMapper;
import com.ashish.msscbeerservice.repository.BeerRepository;
import com.ashish.msscbeerservice.util.Util;
import com.ashish.msscbeerservice.web.v1.model.BeerDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<Beer> optionalBeer = beerRepository.findById(id);
        return optionalBeer.map(beer -> {
            BeerDto beerDto =  beerMapper.convertBeerToBeerDto(beer);
            util.setUrl(beerDto);
            return beerDto;
        }).orElseThrow(() -> new BeerNotFoundException("Beer not found with id: " + id));
    }

    @Override
    public BeerDto getBeerByUpc(String upc) {
        Optional<Beer> optionalBeer = beerRepository.getBeerByUpc(upc);
        return optionalBeer.map(beer -> {
            BeerDto beerDto =  beerMapper.convertBeerToBeerDto(beer);
            util.setUrl(beerDto);
            return beerDto;
        }).orElseThrow(() -> new BeerNotFoundException("Beer not found with id: " + upc));
    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        Beer beer = beerMapper.convertBeerDtoToBeer(beerDto);
        Beer savedBeer = beerRepository.save(beer);
        BeerDto savedBeerDto = beerMapper.convertBeerToBeerDto(savedBeer);
        util.setUrl(savedBeerDto);
        return savedBeerDto;
    }

    @Override
    public BeerDto updateBeer(UUID id, BeerDto beerDto) {
        Optional<Beer> optionalBeer = beerRepository.findById(id);
        if(optionalBeer.isPresent()){
            Beer beer = beerMapper.convertBeerDtoToBeer(beerDto);
            beer.setId(id);
            Beer savedBeer = beerRepository.save(beer);
            BeerDto savedBeerDto = beerMapper.convertBeerToBeerDto(savedBeer);
            util.setUrl(savedBeerDto);
            return savedBeerDto;
        }else {
            throw new BeerNotFoundException("Beer not found for id:" + id);
        }
    }

    @Override
    public void deleteBeer(UUID id) {
        beerRepository.deleteById(id);
    }
}
