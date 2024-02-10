package com.ashish.msscbeerservice.bootstrap;

import com.ashish.msscbeerservice.entity.Beer;
import com.ashish.msscbeerservice.entity.BeerStyle;
import com.ashish.msscbeerservice.repository.BeerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Slf4j
@Service
public class DataLoader implements CommandLineRunner {
    private final BeerRepository beerRepository;
    @Override
    public void run(String... args) throws Exception {
        checkAndLoadData();
    }

    private void checkAndLoadData() {
        long count = beerRepository.count();
        if( count == 0 ){
            loadData();
            count = beerRepository.count();
        }
        log.info("Total number of beer:" + count);
    }

    private void loadData() {
        log.info("saving beer");
        Beer lager = Beer.builder().beerName("My Lager")
                .beerStyle(BeerStyle.LAGER)
                .upc("012345678901")
                .quantityOnHand(100L)
                .price(new BigDecimal(500))
                .build();

        Beer ale = Beer.builder().beerName("My Ale")
                .beerStyle(BeerStyle.ALE)
                .upc("012345678911")
                .quantityOnHand(200L)
                .price(new BigDecimal(1000))
                .build();

        beerRepository.save(lager);
        beerRepository.save(ale);
        log.info("exiting save beer");
    }
}
