package dev.dead.spring7reactivewebflux.repository;

import dev.dead.spring7reactivewebflux.Config.DatabaseConfig;
import dev.dead.spring7reactivewebflux.domain.Beer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.r2dbc.test.autoconfigure.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@Slf4j
@DataR2dbcTest
@Import(DatabaseConfig.class)
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveNewBeer() {
        AtomicBoolean flag = new AtomicBoolean(false);
        beerRepository.save(getTestBeer())
                .subscribe(beer -> {
                    assert beer.getId() != null;
                    log.debug("Saved beer: {}", beer);
                    flag.set(true);
                });
        await().untilTrue(flag);
        StepVerifier.create(beerRepository.findAll())
                .expectNextCount(1)
                .verifyComplete();


    }

    Beer getTestBeer() {
        return Beer.builder()
                .beerName("TestBeer")
                .upc("1234567890")
                .quantityOnHand(10)
                .price(BigDecimal.valueOf(1.99))
                .beerStyle("TestStyle")
                .build();
    }
}