package com.ashish.msscbeerservice.web.v1.controller;

import com.ashish.msscbeerservice.service.BeerService;
import com.ashish.msscbeerservice.web.v1.model.BeerDto;
import com.ashish.msscbeerservice.web.v1.model.BeerListDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(BeerController.BASE_URL)
@Tag(
        name = "Beer Service",
        description = "CRUD REST APIs for Beer"
)
public class BeerController {
    private final BeerService beerService;
    public final static String BASE_URL = "/api/v1/beer";

    @GetMapping("/{id}")
    @Operation(description = "Get beer by id")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Beer by id success response",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BeerDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "301",
                    description = "Beer id not found"
            )}
    )
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("id") UUID id){
        return new ResponseEntity<>( beerService.getBeerById(id), HttpStatus.OK);
    }

    @GetMapping("/upc/{upc}")
    @Operation(description = "Get beer by upc")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Beer by id success response",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BeerDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "301",
                    description = "Beer upc not found"
            )}
    )
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("upc") String upc){
        return new ResponseEntity<>( beerService.getBeerByUpc(upc), HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Get All Beers")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Find all beers success response",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BeerListDto.class)
                    )
            )
        }
    )
    public ResponseEntity<BeerListDto> getAllBeers(){
        return new ResponseEntity<>(new BeerListDto(beerService.getBeers()), HttpStatus.OK);
    }

    @PostMapping
    @Operation(description = "Create new beer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Create new beer success response",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BeerDto.class)
                    )
            )
    }
    )
    public ResponseEntity<BeerDto> saveNewBeer(@RequestBody BeerDto beerDto){
        return new ResponseEntity<>(beerService.createBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update Beer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Update beer success response",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BeerDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "301",
                    description = "Beer id not found"
            )
    }
    )
    public ResponseEntity<Void> updateBeer(@PathVariable("id") UUID id, @RequestBody BeerDto beerDto){
        beerService.updateBeer(id, beerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete Beer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Delete beer success response",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BeerDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "301",
                    description = "beer id not found"
            )
    }
    )
    public ResponseEntity<Void> deleteBeer(@PathVariable("id") UUID id){
        beerService.deleteBeer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
