package com.rodrigo.backendwebflux.services

import com.rodrigo.backendwebflux.models.Mutant
import com.rodrigo.backendwebflux.repositories.MutantRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class MutantService(val mutantRepository: MutantRepository) {

    fun isMutant(dna: List<String>): Mono<Mutant> {
        val check = validateDna(dna)
        val mutant = Mutant(mutant = check, dna = dna)
        return mutantRepository.save(mutant)
    }

    private fun validateDna(dna: List<String>): Boolean {
        var mutantDna = 0
        for (row in dna.indices) {
            for (column in dna.indices) {

                if (column < dna.size - 3) {
                    if (compareValues(dna[row][column], dna[row][column + 1], dna[row][column + 2],
                            dna[row][column + 3])) {
                        mutantDna++
                    }
                }

                if (row < dna.size - 3) {
                    if (compareValues(dna[row][column], dna[row + 1][column], dna[row + 2][column],
                            dna[row + 3][column])) {
                        mutantDna++
                    }
                }

                if (row < dna.size - 3 && column < dna.size - 3) {
                    if (compareValues(dna[row][column], dna[row + 1][column + 1], dna[row + 2][column + 2],
                            dna[row + 3][column + 3])) {
                        mutantDna++
                    }
                    if (compareValues(dna[row][dna.size - 1 - column], dna[row + 1][dna.size - 2 - column],
                            dna[row + 2][dna.size - 3 - column], dna[row + 3][dna.size - 4 - column])) {
                        mutantDna++
                    }
                }
                if (mutantDna >= 2) {
                    return true
                }
            }
        }
        return false
    }

    private fun compareValues(a: Char, b: Char, c: Char, d: Char): Boolean {
        return a == b && b == c && c == d
    }
}