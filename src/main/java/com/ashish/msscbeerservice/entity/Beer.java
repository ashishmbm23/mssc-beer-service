package com.ashish.msscbeerservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;
    private String beerName;
    private BeerStyle beerStyle;
    private String upc;
    private Long quantityOnHand;
    private BigDecimal price;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastUpdatedDate;
}
