package dev.dead.spring7reactivewebflux.bootstrap;

import dev.dead.spring7reactivewebflux.domain.Beer;
import dev.dead.spring7reactivewebflux.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BootstrapData {

    private final BeerRepository beerRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        beerRepository.count()
                .flatMapMany(count -> count == 0
                        ? beerRepository.saveAll(initialBeers())
                        : Flux.empty()
                )
                .then(beerRepository.count())
                .doOnNext(count -> log.debug("Loaded {} beers", count))
                .subscribe();
    }

    private List<Beer> initialBeers() {
        LocalDateTime now = LocalDateTime.now();

        return List.of(
                beer("Galaxy Cat", "Pale Ale", "12356", "12.99", 122, now),
                beer("Crank", "Pale Ale", "12356222", "11.99", 392, now),
                beer("Sunshine City", "IPA", "12356", "13.99", 144, now)
        );
    }

    private Beer beer(
            String name,
            String style,
            String upc,
            String price,
            int quantity,
            LocalDateTime now
    ) {
        return Beer.builder()
                .beerName(name)
                .beerStyle(style)
                .upc(upc)
                .price(new BigDecimal(price))
                .quantityOnHand(quantity)
                .createdDate(now)
                .lastModifiedDate(now)
                .build();
    }
}
