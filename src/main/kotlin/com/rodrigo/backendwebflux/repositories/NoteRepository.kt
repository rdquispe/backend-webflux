package com.rodrigo.backendwebflux.repositories

import com.rodrigo.backendwebflux.models.Note
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository : ReactiveMongoRepository<Note, Long>