package dev.dead.spring7reactivewebflux.controller;

import dev.dead.spring7reactivewebflux.model.BeerDTO;
import dev.dead.spring7reactivewebflux.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BeerController {
    public static final String BEER_PATH = "/api/v2/beer";
    public static final String BEER__PATH_ID = "/{beerId}";
    private final BeerService beerService;

    @DeleteMapping(BEER__PATH_ID)
    Mono<ResponseEntity<Void>> deleteBeerById(@PathVariable Integer beerId) {
        beerService.deleteBeerById(beerId)
                .subscribe();
        return Mono.just(ResponseEntity.ok()
                .build());
    }

    @PutMapping(BEER__PATH_ID)
    Mono<ResponseEntity<Void>> updateBeer(@PathVariable Integer beerId,
                                          @RequestBody BeerDTO beerDTO) {
        beerService.updateBeer(beerId, beerDTO)
                .subscribe();
        return Mono.just(ResponseEntity.ok()
                .build());
    }

    @GetMapping(BEER_PATH)
    Flux<BeerDTO> getAllBeers() {
        return beerService.getAllBeers();
    }

    @GetMapping(BEER__PATH_ID)
    Mono<BeerDTO> getBeerById(Integer beerId) {
        return beerService.getBeerById(beerId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping(BEER_PATH)
    Mono<ResponseEntity<Void>> saveBeer(
            @Validated @RequestBody BeerDTO beerDTO) {
        return beerService.saveBeer(beerDTO)
                .map(savedBeer -> ResponseEntity.created(UriComponentsBuilder.fromPath(
                                        "http://localhost:8080/" + BEER_PATH + "/" + savedBeer.getId()
                                )
                                .build()
                                .toUri())
                        .build());
    }
}
