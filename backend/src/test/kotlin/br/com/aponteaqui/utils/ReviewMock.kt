package br.com.aponteaqui.utils

import br.com.aponteaqui.domain.review.Platform
import br.com.aponteaqui.domain.review.Review
import java.time.LocalDateTime
import java.util.*

fun reviewMock(
    idMock: UUID = UUID.randomUUID(),
    authorNameMock: String = "Rafael",
    ratingMock: Int = 5,
    commentMock: String = "Excelente",
    platformMock: Platform = Platform.GOOGLE
)= Review(
    id = idMock,
    authorName = authorNameMock,
    rating = ratingMock,
    comment = commentMock,
    platform = platformMock,
    createdAt = LocalDateTime.now()
)