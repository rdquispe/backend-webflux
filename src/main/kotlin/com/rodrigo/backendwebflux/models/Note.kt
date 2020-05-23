package com.rodrigo.backendwebflux.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Note(
    @Id
    val id: Long,
    val detail: String,
    val status: String,
    val tags: String,
    val created_at: Date = Date()
) {
    override fun toString(): String =
        "Note: [id=$id, detail=$detail, status=$status, tags=$tags, created_at=$created_at]"
}