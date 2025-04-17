package br.com.aponteaqui.applicadion.review

import br.com.aponteaqui.application.review.UpdateReviewService
import br.com.aponteaqui.application.review.UpdateReviewUseCase
import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.Review
import br.com.aponteaqui.domain.review.ReviewRepository
import br.com.aponteaqui.presentation.review.dto.UpdateReviewRequest
import br.com.aponteaqui.utils.reviewMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import java.time.LocalDateTime
import java.util.UUID
import kotlin.test.Test
import br.com.aponteaqui.utils.any
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.put

@ActiveProfiles("test")
class UpdateReviewServiceTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    private lateinit var repository: ReviewRepository
    private lateinit var service: UpdateReviewService

    private val existingReviewId = UUID.randomUUID()

    @MockitoBean
    lateinit var updateReviewUseCase: UpdateReviewUseCase

    private val existingReview = reviewMock(
        authorNameMock = "Maria",
        ratingMock = 4,
        commentMock = "boa plataforma"
    )

    @BeforeEach
    fun setup(){
        repository = Mockito.mock(ReviewRepository::class.java)
        service = UpdateReviewService(repository)
    }

    @Test
    fun `deve atualizar uma avaliacao existente`() {
        // Arrange
        val id = UUID.randomUUID()
        val reviewOriginal = Review(
            id = id,
            authorName = "João",
            rating = 4,
            comment = "Bom",
            platform = Platform.GOOGLE,
            createdAt = LocalDateTime.now()
        )

        val request = UpdateReviewRequest(
            authorName = "João Atualizado",
            rating = 5,
            comment = "Excelente",
            platform = "GOOGLE"
        )

        val reviewAtualizado = reviewOriginal.copy(
            authorName = request.authorName,
            rating = request.rating,
            comment = request.comment,
            platform = Platform.valueOf(request.platform)
        )

        Mockito.`when`(repository.findById(id)).thenReturn(reviewOriginal)
        Mockito.`when`(repository.save(any())).thenReturn(reviewAtualizado)

        // Act
        val result = service.execute(
            id,
            reviewAtualizado.authorName,
            reviewAtualizado.rating,
            reviewAtualizado.comment,
            reviewAtualizado.platform.toString()
        )

        // Assert
        assertEquals("João Atualizado", result.authorName)
        assertEquals(5, result.rating)
        assertEquals("Excelente", result.comment)
        assertEquals(Platform.GOOGLE, result.platform)
    }

    @Test
    fun `should throw an exception when the review is not found `() {
        // Arrange
        val id = UUID.randomUUID()
        val request = UpdateReviewRequest(
            authorName = "João Atualizado",
            rating = 5,
            comment = "Excelente",
            platform = "GOOGLE"
        )

        Mockito.`when`(repository.findById(id)).thenReturn(null)

        // Act & Assert
        val exception = assertThrows(RuntimeException::class.java) {
            service.execute(
                id,
                request.authorName,
                request.rating,
                request.comment,
                request.platform
            )
        }

        assertEquals("Review not found!", exception.message)
    }

    fun `should update an existent review and return 200 ok`(){
        val id = UUID.randomUUID()
        val request = UpdateReviewRequest(
            authorName = "João Paulo",
            rating = 5,
            comment = "Excelente",
            platform = "GOOGLE"
        )

        val reviewUpdate = reviewMock(
            idMock = id,
            ratingMock = request.rating,
            commentMock = request.comment,
            platformMock = Platform.valueOf(request.platform)
        )

        Mockito.`when`(
            updateReviewUseCase.execute(
                id = id,
                authorName = request.authorName,
                rating = request.rating,
                comment = request.comment,
                platform = request.platform
            )
        ).thenReturn(reviewUpdate)

        val json = jacksonObjectMapper().writeValueAsString(request)

        mockMvc.put("/reviews/$id"){
            contentType = MediaType.APPLICATION_JSON
            content = json
        }.andExpect {
            status { isOk() }
            jsonPath("$.id") {value(id.toString())}
            jsonPath("$.authorname") {value("João Atual")}
        }
    }
}