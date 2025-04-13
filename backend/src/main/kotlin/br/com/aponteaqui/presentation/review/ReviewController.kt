package br.com.aponteaqui.presentation.review

import br.com.aponteaqui.application.review.CreateReviewUseCase
import br.com.aponteaqui.application.review.GetReviewByIdUseCase
import br.com.aponteaqui.application.review.ListReviewsUseCase
import br.com.aponteaqui.application.review.UpdateReviewUseCase
import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.toResponse
import br.com.aponteaqui.presentation.review.dto.CreateReviewRequest
import br.com.aponteaqui.presentation.review.dto.ReviewResponse
import br.com.aponteaqui.presentation.review.dto.UpdateReviewRequest
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/reviews")
class ReviewController(
    private val listReviewsUseCase: ListReviewsUseCase,
    private val createReviewUseCase: CreateReviewUseCase,
    private val getReviewByIdUseCase: GetReviewByIdUseCase,
    private val updateReviewUseCase: UpdateReviewUseCase
) {
    @GetMapping
    fun getReviews(
        @RequestParam(required = false) platform: Platform?
    ): List<ReviewResponse> {
        val reviews = if (platform != null) {
            listReviewsUseCase.executeFilteredByPlatform(platform)
        } else {
            listReviewsUseCase.execute()
        }
        return reviews.map { it.toResponse() }
    }

    @GetMapping("/v2")
    fun getReviews(
        @RequestParam(required = false) platform: Platform?,
        pageable: Pageable
    ): Page<ReviewResponse> {
        val reviewsPage = if (platform != null) {
            listReviewsUseCase.executePageByPlatform(platform, pageable)
        } else {
            listReviewsUseCase.executePage(pageable)
        }
        return reviewsPage.map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun getReviewById(
        @PathVariable id: UUID
    ): ResponseEntity<ReviewResponse> {
        val review = getReviewByIdUseCase.execute(id)
        return ResponseEntity.ok(review.toResponse())
    }


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

    @PutMapping("/{id}")
    fun updateReview(
        @PathVariable id: UUID,
        @Valid @RequestBody request: UpdateReviewRequest
    ): ResponseEntity<ReviewResponse> {
        val reviewUpdated = updateReviewUseCase.execute(
            id = id,
            authorName = request.authorName,
            rating = request.rating,
            comment = request.comment,
            platform = request.platform
        )
        return ResponseEntity.ok(ReviewResponse.fromDomain(reviewUpdated))
    }
}
