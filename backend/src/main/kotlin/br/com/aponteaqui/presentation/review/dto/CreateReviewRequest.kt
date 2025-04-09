package br.com.aponteaqui.presentation.review.dto

import br.com.aponteaqui.domain.review.Platform
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateReviewRequest(
    @field:NotBlank(message = "Nome do Author é Obrigatório!")
    val authorName: String,

    @field:Min(1)
    @field:Max(5)
    val rating: Int,

    @field:NotBlank(message = "Comentário é obrigatório")
    val comment: String,

    @field:NotNull
    val platform: Platform
)