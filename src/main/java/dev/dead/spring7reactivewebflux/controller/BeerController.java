package dev.dead.spring7reactivewebflux.controller;

import dev.dead.spring7reactivewebflux.domain.Beer;
import dev.dead.spring7reactivewebflux.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class BeerController {
    public static final String BEER_PATH = "/api/v2/beer";
    private final BeerRepository beerRepository;

    @GetMapping(BEER_PATH)
    Flux<Beer> getAllBeers() {
        return beerRepository.findAll();
    }
}
