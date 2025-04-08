package br.com.olhodocliente.application.review

import br.com.olhodocliente.domain.review.Review
import br.com.olhodocliente.domain.review.ReviewRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class CreateReviewUseCase(
    private val repository: ReviewRepository
) {
    fun execute(
        authorName: String,
        rating: Int,
        comment: String,
        platform: String
    ) = Review(
        id = UUID.randomUUID(),
        authorName = authorName,
        rating = rating,
        comment = comment,
        platform = enumValueOf(platform.uppercase()),
        createdAt = LocalDateTime.now()
    )
}