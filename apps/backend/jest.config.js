// apps/backend/jest.config.js
module.exports = {
    preset: 'ts-jest',
    testEnvironment: 'node',
    roots: ['<rootDir>/src'],
    moduleFileExtensions: ['ts', 'js', 'json'],
    transform: {
      '^.+\\.ts$': 'ts-jest',
    },
    testRegex: '.*\\.spec\\.ts$',
    coverageDirectory: './coverage',
    collectCoverageFrom: ['src/**/*.ts'],
  };