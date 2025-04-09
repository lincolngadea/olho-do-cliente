package br.com.aponteaqui.presentation.review

import br.com.aponteaqui.application.review.CreateReviewUseCase
import br.com.aponteaqui.application.review.ListReviewsUseCase
import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.Review
import br.com.aponteaqui.presentation.review.dto.CreateReviewRequest
import br.com.aponteaqui.utils.reviewMock
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
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
        val review = reviewMock()

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

    fun `deve retornar uma lista de reviews filtrados por platform`(){
        val review = reviewMock()

        Mockito.`when`(listReviewsUseCase.executeFilteredByPlatform(Platform.GOOGLE))
            .thenReturn(listOf(review))

        mockMvc.get("/reviews?platform=GOOGLE")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].platform"){value("GOOGLE")}
                jsonPath("$[0].authorName"){ value("Rafael")}
            }
    }
}