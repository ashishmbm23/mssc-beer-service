package com.ashish.msscbeerservice.service.brewing;

import com.ashish.common.model.events.BrewBeerEvent;
import com.ashish.msscbeerservice.config.JMSConfig;
import com.ashish.msscbeerservice.entity.Beer;
import com.ashish.msscbeerservice.repository.BeerRepository;
import com.ashish.msscbeerservice.service.inventory.BeerInventoryService;
import com.ashish.msscbeerservice.web.v1.mapper.BeerMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Transactional
    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory(){
        List<Beer> beerList = beerRepository.findAll();
        beerList.stream()
                .forEach( (beer -> {
                    Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());
                    log.debug("Beer Min on hand:" + beer.getMinOnHand());
                    log.debug("Beer QOH:" + invQOH);
                    if( beer.getMinOnHand() >= invQOH ){
                        jmsTemplate.convertAndSend(JMSConfig.BREWING_REQUEST_QUEUE,
                                new BrewBeerEvent( beerMapper.convertBeerToBeerDto(beer) ));
                    }
                }));
    }
}
