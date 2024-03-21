package com.ashish.msscbeerservice.bootstrap;

import com.ashish.msscbeerservice.entity.Beer;
import com.ashish.msscbeerservice.repository.BeerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
//@Service
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
        UUID lagerUUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
        UUID aleUUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
        UUID porterUUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");
        Beer lager = Beer.builder().beerName("My Lager")
                .beerStyle("LAGER")
                .id(lagerUUID)
                .upc("012345678901")
                .minOnHand(100L)
                .price(new BigDecimal(500))
                .build();

        Beer ale = Beer.builder().beerName("My Ale")
                .id(aleUUID)
                .beerStyle("ALE")
                .upc("012345678911")
                .minOnHand(200L)
                .price(new BigDecimal(1000))
                .build();

        beerRepository.save(lager);
        beerRepository.save(ale);
        log.info("exiting save beer");
    }
}
