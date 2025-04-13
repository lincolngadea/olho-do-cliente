package br.com.aponteaqui.application.review

import br.com.aponteaqui.domain.review.Review
import java.util.UUID

interface UpdateReviewUseCase {
    fun execute(
        id: UUID,
        authorName: String,
        rating: Int,
        comment: String,
        platform: String
    ): Review
}