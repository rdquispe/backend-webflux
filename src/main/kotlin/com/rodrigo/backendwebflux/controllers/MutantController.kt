package com.rodrigo.backendwebflux.controllers

import com.rodrigo.backendwebflux.models.Mutant
import com.rodrigo.backendwebflux.services.MutantService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/mutant")
class MutantController(val mutantService: MutantService) {

    @PostMapping
    fun isMutant(@RequestBody dna: List<String>) : Mono<Mutant> =
        mutantService.isMutant(dna)
}
