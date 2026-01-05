package dev.dead.spring7reactivewebflux.mappers;

import dev.dead.spring7reactivewebflux.domain.Beer;
import dev.dead.spring7reactivewebflux.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    BeerDTO beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDTO beerDTO);

}
