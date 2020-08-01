package com.rodrigo.backendwebflux.controllers

import com.rodrigo.backendwebflux.services.MutantService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@ExtendWith(SpringExtension::class)
@WebFluxTest(controllers = [MutantController::class])
@Import(MutantService::class)
class MutantControllerTest {

    private val DNA_MUTANT = arrayOf("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG")
    private val DNA_HUMAN = arrayOf("ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG")

    @Autowired
    private lateinit var mutantService: MutantService

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `is a mutant dna`() {
        webTestClient.post()
            .uri("/mutant")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(DNA_MUTANT))
            .exchange()
            .expectStatus()
            .isOk
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.mutant").isEqualTo(true)
    }

    @Test
    fun `is a human dna`() {
        webTestClient.post()
            .uri("/mutant")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(DNA_HUMAN))
            .exchange()
            .expectStatus()
            .isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.mutant").isEqualTo(false)
    }
}