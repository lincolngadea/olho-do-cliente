package br.com.aponteaqui.infrastructure.review

import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.Review
import br.com.aponteaqui.domain.review.ReviewRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class JpaReviewRepository(
    private val jpa: SpringDataReviewJpaRepository
):ReviewRepository{
    override fun findAll(): List<Review> {
        return jpa.findAll()
    }

    override fun findAllPaged(pageable: Pageable): Page<Review> {
        return jpa.findAll(pageable)
    }

    override fun save(review: Review): Review {
        return jpa.save(review)
    }

    override fun findByPlatform(platform: Platform): List<Review> {
        return jpa.findByPlatform(platform)
    }

    override fun findByPlatformPaged(platform: Platform, pageable: Pageable): Page<Review> {
        return jpa.findByPlatform(platform, pageable)
    }

    override fun findById(id: UUID): Review? {
        return jpa.findById(id).orElse(null)
    }
}