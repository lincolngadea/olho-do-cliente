package br.com.olhodocliente.applicadion.review

import br.com.olhodocliente.application.review.CreateReviewUseCase
import br.com.olhodocliente.domain.review.Platform
import br.com.olhodocliente.domain.review.Review
import br.com.olhodocliente.domain.review.ReviewRepository
import org.mockito.Mockito
import org.mockito.kotlin.any
import java.time.LocalDateTime
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class CreateReviewUseCaseTest {
    private val repository = Mockito.mock(ReviewRepository::class.java)
    private val useCase = CreateReviewUseCase(repository)

    @Test
    fun `deve criar e retornar uma nova review`() {
        val review = Review(
            id = UUID.randomUUID(),
            authorName = "Rafael",
            rating = 5,
            comment = "Excelente",
            platform = Platform.GOOGLE,
            createdAt = LocalDateTime.now()
        )

        Mockito.`when`(repository.save(any<Review>())).thenReturn(review)

        val result = useCase.execute("Rafael", 5, "Excelente", "GOOGLE")

        assertEquals("Rafael", result.authorName)
        assertEquals(5, result.rating)
        assertEquals(Platform.GOOGLE, result.platform)
    }
}