package com.ashish.msscbeerservice.event;

import com.ashish.msscbeerservice.web.v1.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1013882140282129567L;

    private final BeerDto beerDto;
}
