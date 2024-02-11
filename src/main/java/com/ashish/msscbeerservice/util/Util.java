package com.ashish.msscbeerservice.util;

import com.ashish.msscbeerservice.web.v1.model.BeerDto;
import com.ashish.msscbeerservice.web.v1.model.CustomerDto;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Util {

    @Value("/api/" + "${api.version}")
    public String apiVersion ;

    @PostConstruct
    public void init() {
        log.info("api version: " + apiVersion);
    }

    public <T> void setUrl(T t){
        if( t instanceof BeerDto ){
            ((BeerDto) t).setBeerUrl(getBeerBaseUrl());
        }
        else if( t instanceof CustomerDto){
            ((BeerDto) t).setBeerUrl(getBeerBaseUrl());
        }
    }

    public String getBeerBaseUrl() {
        return getApiVersion() + Constant.beerUrl;
    }

    public String getCustomerBaseUrl() {
        return getApiVersion() + Constant.customerUrl;
    }

    private String getApiVersion() {
        return apiVersion ;
    }
}
