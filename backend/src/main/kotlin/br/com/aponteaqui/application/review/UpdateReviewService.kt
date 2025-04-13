package br.com.aponteaqui.application.review

import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.Review
import br.com.aponteaqui.domain.review.ReviewRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UpdateReviewService(
    private val repository: ReviewRepository
) : UpdateReviewUseCase {
    override fun execute(
        id: UUID,
        authorName: String,
        rating: Int,
        comment: String,
        platform: String
    ): Review {
        val reviewExisting = repository.findById(id)
            ?: throw RuntimeException("Review not found!")

        val reviewUpdated = reviewExisting.copy(
            authorName = authorName,
            rating = rating,
            comment = comment,
            platform = Platform.valueOf(platform)
        )
        return repository.save(reviewUpdated)
    }
}