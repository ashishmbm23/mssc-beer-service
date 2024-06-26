package com.ashish.msscbeerservice.web.v1.controller;

import com.ashish.common.model.BeerDto;
import com.ashish.common.model.BeerListDto;
import com.ashish.common.model.BeerPagedList;
import com.ashish.common.model.BeerStyleEnum;
import com.ashish.msscbeerservice.service.BeerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

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
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("id") UUID id,
                                               @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){
        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }
        return new ResponseEntity<>( beerService.getBeerById(id, showInventoryOnHand), HttpStatus.OK);
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
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("upc") String upc,
            @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){
        if( showInventoryOnHand == null){
            showInventoryOnHand = false;
        }
        return new ResponseEntity<>( beerService.getBeerByUpc(upc, showInventoryOnHand), HttpStatus.OK);
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
    public ResponseEntity<BeerPagedList> getAllBeers(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "beerName", required = false) String beerName,
            @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
            @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand
    ){
        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        BeerPagedList beerList = beerService.listBeers
                (beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);
        return new ResponseEntity<>(beerList, HttpStatus.OK);
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
                            schema = @Schema(implementation = ResponseEntity.class)
                    )
            )
    }
    )
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto){
        BeerDto savedBeerDto = beerService.createBeer(beerDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("location", "/api/v1/beer/" + savedBeerDto.getId());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
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
    public ResponseEntity<Void> updateBeer(@Valid @PathVariable("id") UUID id, @RequestBody BeerDto beerDto){
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

    @ExceptionHandler(ConstraintViolationException.class)
    public List<String> handleViolationException(ConstraintViolationException constraintViolationException){
        List<String> errors = new ArrayList<>();
        constraintViolationException.getConstraintViolations().forEach( constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
        });
        return errors;
    }


}
