package com.rodrigo.backendwebflux.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Mutant(
    @Id
    var id: String = "",
    var mutant: Boolean,
    var dna: List<String>,
    var created_at: Date = Date()
) {
    override fun toString(): String {
        return "Mutant [id=$id mutant=$mutant, dna=$dna, created_at=$created_at]"
    }
}