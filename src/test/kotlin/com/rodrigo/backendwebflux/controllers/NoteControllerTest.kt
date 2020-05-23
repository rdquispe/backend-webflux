package com.rodrigo.backendwebflux.controllers

import com.rodrigo.backendwebflux.models.Note
import com.rodrigo.backendwebflux.repositories.NoteRepository
import com.rodrigo.backendwebflux.services.NoteService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@ExtendWith(SpringExtension::class)
@WebFluxTest(controllers = [NoteController::class])
@Import(NoteService::class)
class NoteControllerTest {

    @MockBean
    private lateinit var noteRepository: NoteRepository

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `add note`() {
        val note = Note(id = 1, detail = "some detail", status = "pending", tags = "kotlin, webflux")
        Mockito.`when`(noteRepository.save(note)).thenReturn(Mono.just(note))

        webTestClient.post()
            .uri("/notes/create")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(note))
            .exchange()
            .expectStatus()
            .isOk

        Mockito.verify(noteRepository, times(1)).save(note)
    }

    @Test
    fun `get note by id`() {
        val note = Note(id = 1, detail = "some detail", status = "pending", tags = "kotlin, webflux")
        Mockito.`when`(noteRepository.findById(1)).thenReturn(Mono.just(note))

        webTestClient.get()
            .uri("/notes/{id}", 1)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.id").isEqualTo(1)
            .jsonPath("$.status").isEqualTo("pending")
            .jsonPath("$.tags").isEqualTo("kotlin, webflux")
            .jsonPath("$.detail").isNotEmpty

        Mockito.verify(noteRepository, times(1)).findById(1)
    }

    @Test
    fun `get all notes`() {
        val note = listOf(
            Note(id = 1, detail = "detail one", status = "pending", tags = "kotlin, webflux"),
            Note(id = 2, detail = "detail two", status = "success", tags = "python, flask"))

        Mockito.`when`(noteRepository.findAll()).thenReturn(Flux.fromIterable(note))

        webTestClient.get()
            .uri("/notes/all")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$[0].id").isEqualTo(1)
            .jsonPath("$[0].status").isEqualTo("pending")
            .jsonPath("$[1].id").isEqualTo(2)
            .jsonPath("$[1].status").isEqualTo("success")

        Mockito.verify(noteRepository, times(1)).findAll()
    }

    @Test
    fun `delete by id`() {
        val voidReturn = Mono.empty<Void>()
        Mockito.`when`(noteRepository.deleteById(1)).thenReturn(voidReturn)

        webTestClient.delete()
            .uri("/notes/{id}", 1)
            .exchange()
            .expectStatus().isOk
    }
}