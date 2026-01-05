package dev.dead.spring7reactivewebflux.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWebTestClient
class BeerControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @Test
    void getAllBeers() {
        webTestClient.get()
                .uri(BeerController.BEER_PATH)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType("application/json")
                .expectBody()
                .jsonPath("$.size()")
                .isEqualTo(3);
    }
}