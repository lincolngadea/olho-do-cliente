package br.com.aponteaqui.application.review

import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.Review
import br.com.aponteaqui.domain.review.ReviewRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

    fun executePage(pageable: Pageable): Page<Review> {
        return reviewRepository.findAllPaged(pageable)
    }

    fun executePageByPlatform(platform: Platform, pageable: Pageable): Page<Review> {
        return reviewRepository.findByPlatformPaged(platform, pageable)
    }
}