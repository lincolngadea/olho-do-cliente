import { Review } from '../entities/review.entity';

export abstract class ReviewsRepository {
  abstract findAll(): Promise<Review[]>;
}