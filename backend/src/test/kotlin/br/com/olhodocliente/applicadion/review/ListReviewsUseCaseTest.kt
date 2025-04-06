package br.com.olhodocliente.infrastructure.review

import br.com.olhodocliente.domain.review.Platform
import br.com.olhodocliente.domain.review.Review
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime
import java.util.*

@DataJpaTest
@ActiveProfiles("test")
class SpringDataReviewJpaRepositoryTest {

    @Autowired
    lateinit var repository: SpringDataReviewJpaRepository

    @Test
    fun `deve persistir e buscar avaliações corretamente`() {
        // Arrange
        val review = Review(
            id = UUID.randomUUID(),
            authorName = "João Teste",
            rating = 5,
            comment = "Muito bom!",
            platform = Platform.GOOGLE,
            createdAt = LocalDateTime.now()
        )

        repository.save(review)

        // Act
        val reviews = repository.findAll()

        // Assert
        assertEquals(1, reviews.size)
        assertEquals("João Teste", reviews.first().authorName)
    }
}