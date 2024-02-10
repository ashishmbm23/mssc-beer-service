package com.ashish.msscbeerservice.repository;

import com.ashish.msscbeerservice.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

    Optional<Beer> getBeerByUpc(String upc);
}
