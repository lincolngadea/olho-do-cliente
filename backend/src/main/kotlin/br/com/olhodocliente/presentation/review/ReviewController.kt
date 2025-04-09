package br.com.olhodocliente.presentation.review

import br.com.olhodocliente.application.review.CreateReviewUseCase
import br.com.olhodocliente.application.review.ListReviewsUseCase
import br.com.olhodocliente.domain.review.toResponse
import br.com.olhodocliente.presentation.review.dto.CreateReviewRequest
import br.com.olhodocliente.presentation.review.dto.ReviewResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/reviews")
class ReviewController(
    private val listReviewsUseCase: ListReviewsUseCase,
    private val createReviewUseCase: CreateReviewUseCase
) {
    @GetMapping
    fun getReviews(): List<ReviewResponse> =
        listReviewsUseCase.execute().map { it.toResponse() }

    @PostMapping
    fun createReview(
        @Valid @RequestBody request: CreateReviewRequest
    ): ResponseEntity<ReviewResponse> {
        val created = createReviewUseCase.execute(
            authorName = request.authorName,
            rating = request.rating,
            comment = request.comment,
            platform = request.platform.name
        )

        return ResponseEntity.created(URI.create("/reviews/${created.id}")).body(created.toResponse())
    }
}