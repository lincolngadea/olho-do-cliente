package br.com.aponteaqui.application.review

import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.Review
import br.com.aponteaqui.domain.review.ReviewRepository
import org.springframework.stereotype.Service

@Service
class ListReviewsUseCase(
    private val reviewRepository: ReviewRepository
) {
    fun execute(): List<Review> {
        return reviewRepository.findAll()
    }

    fun executeFilteredByPlatform(platform: Platform): List<Review> {
        return reviewRepository.findByPlatform(platform)
    }
}