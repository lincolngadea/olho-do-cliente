package br.com.olhodocliente.infrastructure.review

import br.com.olhodocliente.domain.review.Review
import br.com.olhodocliente.domain.review.ReviewRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID


interface SpringDataReviewJpaRepository: JpaRepository<Review, UUID>

@Repository
class JpaReviewRepository(
    private val jpa: SpringDataReviewJpaRepository
):ReviewRepository{
    override fun findAll(): List<Review> {
        return jpa.findAll()
    }
}