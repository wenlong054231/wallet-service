# Wallet Service API

> Portfolio project by Aaron 
> skills in Java and Spring BooT

RESTful wallet service featuring stateless JWT authentication,
core wallet operations, and concurrency-safe transactions.

## Tech Stack

- Java 23
- Spring Boot 4.0.6
- Spring Security + JWT (stateless authentication)
- Spring Data JPA + Hibernate
- MySQL
- Maven

## Key Highlights
- **Pessimistic locking** — prevents race conditions when multiple users 
  transact simultaneously
- **JWT stateless auth** — custom filter chain with Spring Security
- **Global exception handling** — consistent API response format across 
  all endpoints
- **Input validation** — DTO-level validation with meaningful error messages
- **Unit testing** — service layer tested with Mockito, no DB required
- **Pagination** — transaction history with Spring Data pageable
- **Secure configuration** — secrets externalized via environment variables

## Features

- User registration and login
- Deposit funds into wallet
- Withdraw funds from wallet
- Transfer funds between users
- Paginated transaction history

## API Documentation
Swagger UI: https://wallet-service-production-0fb0.up.railway.app/swagger-ui/index.html

## API Endpoints
Require token from /user/register

Authorization: Bearer <token>
