# JSim Estore — Backend

REST API for the JSim flight sim peripherals store. Built with Spring Boot, secured with JWT, and deployed on Railway.

---

## Tech Stack

- Java 17 + Spring Boot 3
- Spring Security with JWT authentication
- Spring Data JPA
- PostgreSQL (hosted on Railway)
- Stripe (checkout and webhooks)

---

## Running Locally

**Prerequisites:** Java 17+, Maven, PostgreSQL

```bash
git clone https://github.com/your-username/jsim-estore-backend
cd jsim-estore-backend
```

Set up your environment variables (see Configuration below), then:

```bash
./mvnw spring-boot:run
```

The API will start on `http://localhost:8080`.

---

## Configuration

Create an `application.properties` or set the following environment variables:

```
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/jsim
SPRING_DATASOURCE_USERNAME=your_db_user
SPRING_DATASOURCE_PASSWORD=your_db_password

JWT_SECRET=your_jwt_secret
JWT_EXPIRATION_MS=86400000

STRIPE_SECRET_KEY=sk_...
STRIPE_WEBHOOK_SECRET=whsec_...
```

---

## API Overview

Base URL (production): `https://jsim-estore-production.up.railway.app`

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/login` | Login, returns JWT |
| POST | `/auth/refresh` | Refresh JWT via cookie |
| GET | `/auth/me` | Get current user |

### Users
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/users` | Register new user |
| GET | `/users/{id}` | Get user by ID |
| PUT | `/users/{id}` | Update user |
| DELETE | `/users/{id}` | Delete user |
| POST | `/users/{id}/change-password` | Change password |
| GET | `/users` | Get all users (admin, requires x-auth-token header) |

### Products
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/products` | List all products |
| GET | `/products/{id}` | Get product by ID |
| POST | `/products` | Create product |
| PUT | `/products/{id}` | Update product |
| DELETE | `/products/{id}` | Delete product |

### Cart
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/carts` | Create a new cart |
| GET | `/carts/{cartId}` | Get cart by ID |
| POST | `/carts/{cartId}/items` | Add item to cart |
| PUT | `/carts/{cartId}/items/{productId}` | Update item quantity |
| DELETE | `/carts/{cartId}/items/{productId}` | Remove item from cart |
| DELETE | `/carts/{cartId}/items` | Clear cart |

### Orders
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/orders` | Get all orders |
| GET | `/orders/{orderId}` | Get order by ID |

### Checkout
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/checkout` | Create Stripe checkout session |
| POST | `/checkout/webhook` | Stripe webhook handler |

---

## Authentication

Protected endpoints require a Bearer token in the Authorization header:

```
Authorization: Bearer <token>
```

Obtain a token via `POST /auth/login` with your email and password.

---

## Project Structure

```
src/main/java/com/hoodinahi/store/
  auth/          JWT filter, login controller, security config
  user/          User entity, controller, service
  product/       Product entity, controller, service
  cart/          Cart entity, controller, service
  order/         Order entity, controller, service
  checkout/      Stripe integration
  common/        Shared interfaces (SecurityRules, etc.)
```

---

## Deployment

The application is deployed on Railway with a managed PostgreSQL instance. Pushes to the main branch trigger automatic redeployment.

To deploy your own instance:

1. Create a new Railway project
2. Add a PostgreSQL plugin
3. Connect your GitHub repository
4. Set the environment variables listed above
5. Railway will detect the Maven project and build automatically

---

## Frontend

The storefront (React + Vite) lives in a separate repository and is deployed as a static site. It communicates with this API over HTTPS.
