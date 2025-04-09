package br.com.aponteaqui.domain.review

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ReviewRepository {
    fun findAll(): List<Review>
    fun save(review: Review): Review
    fun findByPlatform(platform: Platform): List<Review>
    fun findAllPaged(pageable: Pageable): Page<Review>
    fun findByPlatformPaged(platform: Platform, pageable: Pageable): Page<Review>
}