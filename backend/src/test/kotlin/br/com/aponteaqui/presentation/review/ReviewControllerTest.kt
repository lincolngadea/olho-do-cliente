package br.com.aponteaqui.presentation.review

import br.com.aponteaqui.application.review.CreateReviewUseCase
import br.com.aponteaqui.application.review.ListReviewsUseCase
import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.Review
import br.com.aponteaqui.presentation.review.dto.CreateReviewRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.time.LocalDateTime
import java.util.*

@WebMvcTest(ReviewController::class)
class ReviewControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockitoBean
    lateinit var listReviewsUseCase: ListReviewsUseCase

    @MockitoBean
    lateinit var createReviewUseCase: CreateReviewUseCase

    @Test
    fun `deve criar uma nova review e retornar 201 created`() {
        val review = Review(
            id = UUID.randomUUID(),
            authorName = "Rafael",
            rating = 5,
            comment = "Excelente",
            platform = Platform.GOOGLE,
            createdAt = LocalDateTime.now()
        )

        val mapper = jacksonObjectMapper()

        val json = mapper.writeValueAsString(CreateReviewRequest("Rafael", 5,"Excelente", Platform.GOOGLE))

        Mockito.`when`(
            createReviewUseCase.execute(
                authorName = "Rafael",
                rating = 5,
                comment = "Excelente",
                platform = "GOOGLE"
            )
        ).thenReturn(review)

        mockMvc.post("/reviews") {
            contentType = MediaType.APPLICATION_JSON
            content = json
        }.andDo{
            println()
        }.andExpect {
            status { isCreated() }

        }
    }
}