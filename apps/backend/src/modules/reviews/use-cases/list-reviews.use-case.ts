import { ReviewsRepository } from '../interfaces/reviews.repository';
import { Review } from '../entities/review.entity';

export class ListReviewsUseCase {
    constructor(private readonly reviewsRepository: ReviewsRepository) { }

    async execute(): Promise<Review[]> {
        return this.reviewsRepository.findAll()
    }
}