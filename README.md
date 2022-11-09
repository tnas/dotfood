# Dotfood
Sample application developed following an architecture based on Microservices.

### Microservices
The microservices were developed with **Spring Boot**
- Payments
- Orders

### Databases
- Both microservices have a MySQL database.
- They used **Flyway** for database migration.

### Service Registration and Discovery
It was employed **Netflix Eureka Server**

### Gateway
It was used **Netflix Eureka Client**

### Synchronous Communication
It was used **Open Feign**

## Circuit Breaker
It was used **Resilience4j**