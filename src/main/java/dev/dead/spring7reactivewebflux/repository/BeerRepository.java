package dev.dead.spring7reactivewebflux.repository;

import dev.dead.spring7reactivewebflux.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}
