package br.com.aponteaqui.presentation.review.dto

import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.Review
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.util.*

data class ReviewResponse(
    val id: UUID,

    @JsonProperty("authorName")
    val authorName: String,
    val rating: Int,
    val comment: String,
    val platform: Platform,
    val createdAt: LocalDateTime
) {
    companion object {
        fun fromDomain(review: Review) = ReviewResponse(
            id = review.id,
            authorName = review.authorName,
            rating = review.rating,
            comment = review.comment,
            platform = review.platform,
            createdAt = review.createdAt
        )
    }
}