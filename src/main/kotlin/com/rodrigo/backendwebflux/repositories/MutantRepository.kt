package com.rodrigo.backendwebflux.repositories

import com.rodrigo.backendwebflux.models.Mutant
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MutantRepository : ReactiveMongoRepository<Mutant, String>