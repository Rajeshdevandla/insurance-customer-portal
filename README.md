# Insurance Customer Portal

Enterprise full-stack portal for Mutual of Omaha customers to manage policies, claims, and transactions in real time.

## Key Achievements
- Reduced page load time by **35%** via code splitting & lazy loading
- **99.9% uptime** on AWS infrastructure
- CI/CD pipelines cut release time by **40%**

## Tech Stack
| Layer | Technologies |
|---|---|
| Frontend | React.js 18, Angular 16, TypeScript, TailwindCSS |
| Backend | Java 17, Spring Boot 3.2, Spring Security, JWT |
| Database | PostgreSQL, Redis |
| Cloud | AWS (ECS, RDS, S3, CloudFront, ALB) |
| DevOps | Docker, Kubernetes, GitHub Actions |

## Architecture
```
Customer → CloudFront CDN → ALB → React/Angular SPA
                                  ↓
                            API Gateway
                                  ↓
              Spring Boot Microservices (Auth, Policy, Claims, Billing)
                                  ↓
                       PostgreSQL + Redis Cache
```

## Getting Started
```bash
docker-compose up -d
```
- Frontend: http://localhost:3000
- Backend API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
