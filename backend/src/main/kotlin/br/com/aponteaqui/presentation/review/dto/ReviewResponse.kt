package br.com.aponteaqui.presentation.review.dto

import br.com.aponteaqui.domain.review.Platform
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.util.UUID

data class ReviewResponse(
    val id: UUID,

    @JsonProperty("authorName")
    val authorName: String,
    val rating: Int,
    val comment: String,
    val platform: Platform,
    val createdAt: LocalDateTime
)