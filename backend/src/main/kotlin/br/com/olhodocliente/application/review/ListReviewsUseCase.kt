package br.com.olhodocliente.application.review

import br.com.olhodocliente.domain.review.Review
import br.com.olhodocliente.domain.review.ReviewRepository
import org.springframework.stereotype.Service

@Service
class ListReviewsUseCase(
    private val reviewRepository: ReviewRepository
){
    fun execute(): List<Review>{
        return reviewRepository.findAll()
    }
}