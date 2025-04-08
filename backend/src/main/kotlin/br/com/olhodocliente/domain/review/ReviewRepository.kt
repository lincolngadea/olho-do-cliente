package br.com.olhodocliente.domain.review

interface ReviewRepository {
    fun findAll(): List<Review>
    fun save(review: Review): Review
}