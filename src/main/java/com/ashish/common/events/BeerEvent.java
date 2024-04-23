package com.ashish.common.events;

import com.ashish.common.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1013882140282129567L;

    private BeerDto beerDto;
}
