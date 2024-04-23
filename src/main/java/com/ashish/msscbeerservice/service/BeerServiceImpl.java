package com.ashish.msscbeerservice.service;

import com.ashish.common.BeerDto;
import com.ashish.common.BeerPagedList;
import com.ashish.common.BeerStyleEnum;
import com.ashish.msscbeerservice.entity.Beer;
import com.ashish.msscbeerservice.exception.BeerNotFoundException;
import com.ashish.msscbeerservice.repository.BeerRepository;
import com.ashish.msscbeerservice.util.Util;
import com.ashish.msscbeerservice.web.v1.mapper.BeerMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
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
    @Cacheable(cacheNames = "beerCache", key = "#id", condition = "#showInventoryOnHand==false")
    public BeerDto getBeerById(UUID id, Boolean showInventoryOnHand) {
        log.info("Inside getBeer By Id");
        Optional<Beer> optionalBeer = beerRepository.findById(id);
        if( showInventoryOnHand ){
            return optionalBeer.map(beer -> {
                BeerDto beerDto =  beerMapper.beerToBeerDtoWithInventory(beer);
                util.setUrl(beerDto);
                return beerDto;
            }).orElseThrow(() -> new BeerNotFoundException("Beer not found with id: " + id));

        }else{
            return optionalBeer.map(beer -> {
                BeerDto beerDto =  beerMapper.convertBeerToBeerDto(beer);
                util.setUrl(beerDto);
                return beerDto;
            }).orElseThrow(() -> new BeerNotFoundException("Beer not found with id: " + id));
        }

    }

    @Override
    @Cacheable(cacheNames = "beerUpcCache", key="#upc", condition = "#showInventoryOnHand==false")
    public BeerDto getBeerByUpc(String upc, Boolean showInventoryOnHand) {
        Optional<Beer> optionalBeer = beerRepository.getBeerByUpc(upc);
        if( showInventoryOnHand ){
            return optionalBeer.map(beer -> {
                BeerDto beerDto =  beerMapper.beerToBeerDtoWithInventory(beer);
                util.setUrl(beerDto);
                return beerDto;
            }).orElseThrow(() -> new BeerNotFoundException("Beer not found with id: " + upc));
        }else{
            return optionalBeer.map(beer -> {
                BeerDto beerDto =  beerMapper.convertBeerToBeerDto(beer);
                util.setUrl(beerDto);
                return beerDto;
            }).orElseThrow(() -> new BeerNotFoundException("Beer not found with id: " + upc));
        }
    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        Beer beer = beerMapper.convertBeerDtoToBeer(beerDto);
        beer.setLastModifiedDate(OffsetDateTime.now());
        beer.setCreatedDate(OffsetDateTime.now());
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
            beer.setLastModifiedDate(OffsetDateTime.now());
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

    @Override
    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand==false")
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,
                                   Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;
        log.info("Inside getBeers");
        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }


        if( showInventoryOnHand ){
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());

        }else{
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::convertBeerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());

        }


        return beerPagedList;
    }

}
