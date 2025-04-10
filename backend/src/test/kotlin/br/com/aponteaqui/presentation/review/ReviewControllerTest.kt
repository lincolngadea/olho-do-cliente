package br.com.aponteaqui.presentation.review

import br.com.aponteaqui.application.exceptions.ReviewNotfoundException
import br.com.aponteaqui.application.review.CreateReviewUseCase
import br.com.aponteaqui.application.review.GetReviewByIdUseCase
import br.com.aponteaqui.application.review.ListReviewsUseCase
import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.Review
import br.com.aponteaqui.presentation.review.dto.CreateReviewRequest
import br.com.aponteaqui.utils.reviewMock
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.util.*

@WebMvcTest(ReviewController::class)
class ReviewControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockitoBean
    lateinit var listReviewsUseCase: ListReviewsUseCase

    @MockitoBean
    lateinit var getReviewByIdUseCase: GetReviewByIdUseCase

    @MockitoBean
    lateinit var createReviewUseCase: CreateReviewUseCase

    @Test
    fun `deve criar uma nova review e retornar 201 created`() {
        val review = reviewMock()

        val mapper = jacksonObjectMapper()

        val json = mapper.writeValueAsString(CreateReviewRequest("Rafael", 5, "Excelente", Platform.GOOGLE))

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
        }.andDo {
            println()
        }.andExpect {
            status { isCreated() }

        }
    }

    @Test
    fun `deve retornar uma lista de reviews filtrados por platform`() {
        val review = reviewMock()

        Mockito.`when`(listReviewsUseCase.executeFilteredByPlatform(Platform.GOOGLE))
            .thenReturn(listOf(review))

        mockMvc.get("/reviews?platform=GOOGLE")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].platform") { value("GOOGLE") }
                jsonPath("$[0].authorName") { value("Rafael") }
            }
    }

    @Test
    fun `should return available paginated reviews`() {
        val review = reviewMock(
            authorNameMock = "Carlos",
            ratingMock = 4,
            commentMock = "Razoavel"
        )

        val page: Page<Review> = PageImpl(listOf(review))

        Mockito.`when`(listReviewsUseCase.executePage(any()))
            .thenReturn(page)

        mockMvc.get("/reviews/v2?page=0&size=1&sort=createdAt,desc")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.content.length()") { value(1) }
                jsonPath("$.content[0].authorName") { value("Carlos") }
            }
    }

    @Test
    fun `should return an existent review by id`() {
        val id = UUID.randomUUID()
        val review = reviewMock(idMock = id)

        Mockito.`when`(getReviewByIdUseCase.execute(id))
            .thenReturn(review)

        mockMvc.get("/reviews/$id")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.id") { value(id.toString()) }
                jsonPath("$.authorName") { value("Rafael") }
            }
    }

    @Test
    fun `should return 404 when the review is not found`() {
        val id = UUID.randomUUID()

        Mockito.`when`(getReviewByIdUseCase.execute(id))
            .thenThrow(ReviewNotfoundException(id))

        mockMvc.get("/reviews/{id}", id)
            .andExpect {
                status { isNotFound() }
                jsonPath("$.message") { value("Avaliação do id: $id não encontrada") }
            }
    }

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