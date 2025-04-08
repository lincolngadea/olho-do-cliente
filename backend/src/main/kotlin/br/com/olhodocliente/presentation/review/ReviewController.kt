package br.com.olhodocliente.presentation.review

import br.com.olhodocliente.application.review.ListReviewsUseCase
import br.com.olhodocliente.domain.review.Review
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reviews")
class ReviewController(
    private val listReviewsUseCase: ListReviewsUseCase
) {
    @GetMapping
    fun reviewsListAll(): List<Review> {
        return listReviewsUseCase.execute()
    }
}