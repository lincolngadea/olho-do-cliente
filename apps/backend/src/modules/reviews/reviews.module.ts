import { Module } from '@nestjs/common';
import { ReviewsController } from './controllers/reviews.controller';
import { ListReviewsUseCase } from './use-cases/list-reviews.use-case';
import { PrismaReviewsRepository } from './infra/prisma-reviews.repository';

@Module({
  controllers: [ReviewsController],
  providers: [
    ListReviewsUseCase,
    {
      provide: 'ReviewsRepository',
      useClass: PrismaReviewsRepository,
    },
  ],
})
export class ReviewsModule {}