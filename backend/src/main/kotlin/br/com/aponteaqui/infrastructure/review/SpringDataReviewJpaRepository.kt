package br.com.aponteaqui.infrastructure.review

import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.Review
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SpringDataReviewJpaRepository: JpaRepository<Review, UUID> {
    fun findByPlatform(platform: Platform): List<Review>
    fun findByPlatform(platform: Platform, pageable: Pageable): Page<Review>
}