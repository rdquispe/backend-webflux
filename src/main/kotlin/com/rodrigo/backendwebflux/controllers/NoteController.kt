package com.rodrigo.backendwebflux.controllers

import com.rodrigo.backendwebflux.models.Note
import com.rodrigo.backendwebflux.services.NoteService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/notes")
class NoteController(val noteService: NoteService) {

    @PostMapping("/create")
    fun add(@RequestBody note: Note): Mono<Note> = noteService.save(note)

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): Mono<Note> = noteService.findById(id)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): Mono<Void> = noteService.delete(id)

    @GetMapping("/all")
    fun findAll(): Flux<Note> = noteService.findAll()

}