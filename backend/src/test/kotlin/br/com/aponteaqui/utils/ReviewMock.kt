package br.com.aponteaqui.utils

import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.Review
import java.time.LocalDateTime
import java.util.*

fun reviewMock(
    authorNameMock: String = "Rafael",
    ratingMock: Int = 5,
    commentMock: String = "Excelente",
    platformMock: Platform = Platform.GOOGLE
)= Review(
    id = UUID.randomUUID(),
    authorName = authorNameMock,
    rating = ratingMock,
    comment = commentMock,
    platform = platformMock,
    createdAt = LocalDateTime.now()
)