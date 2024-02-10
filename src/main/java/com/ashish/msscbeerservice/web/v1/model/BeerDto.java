package com.ashish.msscbeerservice.web.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Beer model")
public class BeerDto {
    private UUID id;
    private String beerName;
    private String beerStyle;
    private String upc;
    private Long quantityOnHand;
    private BigDecimal price;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastUpdatedDate;
    private Long version;
    @JsonProperty("beer_url")
    private String beerUrl;
}
