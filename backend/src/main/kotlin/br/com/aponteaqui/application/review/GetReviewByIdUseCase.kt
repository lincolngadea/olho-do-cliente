package br.com.aponteaqui.application.review

import br.com.aponteaqui.application.exceptions.ReviewNotfoundException
import br.com.aponteaqui.domain.review.Review
import br.com.aponteaqui.domain.review.ReviewRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetReviewByIdUseCase(
    private val repository: ReviewRepository
) {
    fun execute(id: UUID): Review{
        return repository.findById(id)
            ?: throw ReviewNotfoundException(id)
    }
}