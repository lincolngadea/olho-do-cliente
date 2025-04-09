package br.com.aponteaqui.applicadion.review

import br.com.aponteaqui.application.review.ListReviewsUseCase
import br.com.aponteaqui.presentation.review.ReviewController
import br.com.aponteaqui.utils.reviewMock
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(ReviewController::class)
class ReviewControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockitoBean
    lateinit var listReviewsUseCase: ListReviewsUseCase

    @Test
    fun `deve retornar lista de avaliacoes em JSON`() {
        val review = reviewMock(
            authorNameMock = "João"
        )

        Mockito.`when`(listReviewsUseCase.execute()).thenReturn(listOf(review))

        mockMvc.get("/reviews")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].authorName") { value("João") }
                jsonPath("$[0].rating") { value(5) }
                jsonPath("$[0].comment") { value("Excelente") }
                jsonPath("$[0].platform") { value("GOOGLE") }
            }
    }
}