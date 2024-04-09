package com.ashish.msscbeerservice.bootstrap;

import com.ashish.msscbeerservice.entity.Beer;
import com.ashish.msscbeerservice.repository.BeerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

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
        UUID lagerUUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
        UUID aleUUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
        UUID porterUUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");
        Beer lager = Beer.builder().beerName("Mango Bobs")
                .beerStyle("IPA")
                .id(lagerUUID)
                .upc("0631234200036")
                .quantityToBrew(100l)
                .price(new BigDecimal(12.95))
                .version(1L)
                .build();

        Beer ale = Beer.builder().beerName("Galaxy Cat")
                .id(aleUUID)
                .beerStyle("PALE_ALE")
                .upc("0631234300019")
                .minOnHand(200L)
                .price(new BigDecimal(10.95))
                .version(1L)
                .build();

        Beer porter = Beer.builder().beerName("Pinball Porter")
                .id(porterUUID)
                .beerStyle("PORTER")
                .upc("0083783375213")
                .minOnHand(300L)
                .price(new BigDecimal(15.95))
                .version(1L)
                .build();

        beerRepository.save(lager);
        beerRepository.save(ale);
        beerRepository.save(porter);
        log.info("exiting save beer");
    }
}
