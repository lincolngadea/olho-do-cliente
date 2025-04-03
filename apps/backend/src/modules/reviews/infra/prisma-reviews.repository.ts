import { Injectable } from '@nestjs/common';
import { ReviewsRepository } from '../interfaces/reviews.repository';
import { Review } from '../entities/review.entity';

@Injectable()
export class PrismaReviewsRepository implements ReviewsRepository {
  async findAll(): Promise<Review[]> {
    // Mock de retorno – depois substituímos por consulta real com Prisma
    return [
      new Review('1', 'João Silva', 5, 'Excelente serviço', 'GOOGLE', new Date('2023-01-01')),
      new Review('2', 'Maria Oliveira', 4, 'Bom atendimento', 'FACEBOOK', new Date('2023-02-01')),
    ];
  }
}