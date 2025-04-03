import { ListReviewsUseCase } from '../list-reviews.use-case';
import { ReviewsRepository } from '../../interfaces/reviews.repository';
import { Review } from '../../entities/review.entity';

class InMemoryReviewsRepository implements ReviewsRepository {
    private reviews: any[] = [];

    constructor() {
        this.reviews.push(
            new Review(
                '1',
                'João Silva',
                5,
                'Excelente Serviço',
                'GOOGLE',
                new Date('2023-01-01'),
            ),
            new Review(
                '2',
                'Maria Oliveira',
                4,
                'Bom atendimento',
                'FACEBOOK',
                new Date('2023-02-01'),
            )
        );
    }
    async findAll(): Promise<Review[]> {
        return this.reviews;
    }
}

describe('ListReviewsUseCase', () => {
    it('deve alterar todas as avalicaoes disponiveis', async () => {
        const repository = new InMemoryReviewsRepository();
        const useCase = new ListReviewsUseCase(repository);

        const reviews = await useCase.execute();

        expect(reviews).toHaveLength(2);
        expect(reviews[0].authorName).toBe('João Silva');
        expect(reviews[1].platform).toBe('FACEBOOK');

    });
});