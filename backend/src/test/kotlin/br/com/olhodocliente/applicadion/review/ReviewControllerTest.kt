package br.com.olhodocliente.applicadion.review

import br.com.olhodocliente.application.review.ListReviewsUseCase
import br.com.olhodocliente.domain.review.Platform
import br.com.olhodocliente.domain.review.Review
import br.com.olhodocliente.presentation.review.ReviewController
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.LocalDateTime
import java.util.*

@WebMvcTest(ReviewController::class)
class ReviewControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var listReviewsUseCase: ListReviewsUseCase

    @Test
    fun `deve retornar lista de avaliações em JSON`() {
        // Arrange
        val review = Review(
            id = UUID.randomUUID(),
            authorName = "João",
            rating = 5,
            comment = "Excelente",
            platform = Platform.GOOGLE,
            createdAt = LocalDateTime.now()
        )

        Mockito.`when`(listReviewsUseCase.execute()).thenReturn(listOf(review))

        // Act & Assert
        mockMvc.get("/reviews")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].authorName") { value("João") }
                jsonPath("$[0].rating") { value(5) }
            }
    }
}