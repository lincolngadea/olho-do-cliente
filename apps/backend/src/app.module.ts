// apps/backend/src/app.module.ts
import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { ReviewsModule } from './modules/reviews/reviews.module';

@Module({
  imports: [
    ConfigModule.forRoot({
      isGlobal: true,             // Torna acessível sem precisar importar nos outros módulos
      envFilePath: '.env',        // (Opcional) caminho do arquivo .env
    }),
    ReviewsModule,                // Importação do módulo de avaliações
  ],
})
export class AppModule {}