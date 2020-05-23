package com.rodrigo.backendwebflux.services

import com.rodrigo.backendwebflux.models.Note
import com.rodrigo.backendwebflux.repositories.NoteRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class NoteService(val noteRepository: NoteRepository) {

    fun save(note: Note) : Mono<Note> = noteRepository.save(note)

    fun findById(id: Long): Mono<Note> = noteRepository.findById(id)

    fun delete(id: Long): Mono<Void> = noteRepository.deleteById(id)

    fun findAll(): Flux<Note> = noteRepository.findAll()
}