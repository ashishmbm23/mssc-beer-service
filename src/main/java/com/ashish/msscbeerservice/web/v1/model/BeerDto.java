package com.ashish.msscbeerservice.web.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
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
    @Null
    private UUID id;
    @NotEmpty
    private String beerName;
    @NotEmpty
    private String beerStyle;
    @Positive
    private String upc;
    @Positive
    private Long quantityOnHand;
    @DecimalMin("0")
    private BigDecimal price;
    @Null
    private OffsetDateTime createdDate;
    @Null
    private OffsetDateTime lastUpdatedDate;
    @Null
    private Long version;
    @JsonProperty("beer_url")
    private String beerUrl;
}
