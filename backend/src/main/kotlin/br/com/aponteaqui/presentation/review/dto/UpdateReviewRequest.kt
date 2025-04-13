package br.com.aponteaqui.presentation.review.dto

import br.com.aponteaqui.domain.review.Platform

data class UpdateReviewRequest (
    val authorName: String,
    val rating: Int,
    val comment: String,
    val platform: String
)
