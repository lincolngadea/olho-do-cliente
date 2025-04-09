package br.com.olhodocliente.domain.review

import br.com.olhodocliente.presentation.review.dto.ReviewResponse
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "reviews")
data class Review(
    @Id
    val id: UUID,
    val authorName: String,
    val rating: Int,
    val comment: String,
    @Enumerated(EnumType.STRING)
    val platform: Platform,
    val createdAt: LocalDateTime
)

enum class Platform() {
    GOOGLE,
    FACEBOOK
}

fun Review.toResponse(): ReviewResponse {
    return ReviewResponse(
        this.id,
        this.authorName,
        this.rating,
        this.comment,
        this.platform,
        this.createdAt
    )
}
