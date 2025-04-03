export class Review {
    constructor(
      public readonly id: string,
      public readonly authorName: string,
      public readonly rating: number,
      public readonly comment: string,
      public readonly platform: 'GOOGLE' | 'FACEBOOK',
      public readonly createdAt: Date,
    ) {}
  }