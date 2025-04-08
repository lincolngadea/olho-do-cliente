package br.com.olhodocliente.presentation.review

import br.com.olhodocliente.application.review.CreateReviewUseCase
import br.com.olhodocliente.application.review.ListReviewsUseCase
import br.com.olhodocliente.domain.review.Review
import br.com.olhodocliente.presentation.review.dto.CreateReviewRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/reviews")
class ReviewController(
    private val listReviewsUseCase: ListReviewsUseCase,
    private val createReviewUseCase: CreateReviewUseCase
) {
    @GetMapping
    fun reviewsListAll(): List<Review> = listReviewsUseCase.execute()

    @PostMapping
    fun createReview(
        @Valid @RequestBody request: CreateReviewRequest
    ): ResponseEntity<Review>{
        val created = createReviewUseCase.execute(
            authorName = request.authorName,
            rating = request.rating,
            comment = request.comment,
            platform = request.platform.name
        )

        return ResponseEntity.created(URI.create("/reviews/${created.id}")).body(created)
    }
}