package com.ashish.msscbeerservice.web.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerListDto {
    private List<BeerDto> beers;
}
