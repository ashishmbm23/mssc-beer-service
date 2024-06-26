package com.ashish.msscbeerservice.service.brewing;

import com.ashish.common.model.BeerDto;
import com.ashish.common.model.events.BrewBeerEvent;
import com.ashish.common.model.events.NewInventoryEvent;
import com.ashish.msscbeerservice.config.JMSConfig;
import com.ashish.msscbeerservice.entity.Beer;
import com.ashish.msscbeerservice.repository.BeerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrewingListener {
    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JMSConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent brewBeerEvent){
        BeerDto beerDto = brewBeerEvent.getBeerDto();
        Beer beer = beerRepository.getReferenceById(beerDto.getId());
        beerDto.setQuantityToBrew(beer.getQuantityToBrew());
        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);
        jmsTemplate.convertAndSend(JMSConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
