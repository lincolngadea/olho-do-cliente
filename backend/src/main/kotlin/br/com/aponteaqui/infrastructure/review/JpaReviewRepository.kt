package br.com.aponteaqui.infrastructure.review

import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.Review
import br.com.aponteaqui.domain.review.ReviewRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID


interface SpringDataReviewJpaRepository: JpaRepository<Review, UUID>{
    fun findByPlatform(platform: Platform): List<Review>
}

@Repository
class JpaReviewRepository(
    private val jpa: SpringDataReviewJpaRepository
):ReviewRepository{
    override fun findAll(): List<Review> {
        return jpa.findAll()
    }

    override fun save(review: Review): Review {
        return jpa.save(review)
    }

    override fun findByPlatform(platform: Platform): List<Review> {
        return jpa.findByPlatform(platform)
    }
}