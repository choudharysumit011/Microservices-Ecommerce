**E-Commerce Microservices Application**

Welcome to the E-Commerce Microservices Application! This project demonstrates a scalable, secure, and feature-rich e-commerce platform built using microservices architecture. It covers key functionalities like product management, inventory checking, order processing, and email notifications, all while employing industry best practices.

**Features**

View Products: Browse and search for products available in the inventory.

Add Products: Add new products to the inventory via the admin interface.

Inventory Management: Check if a product is in stock before placing an order.

Order Processing: Place orders for products and track their status.

Email Notifications: Receive email notifications after successfully placing an order.

JWT Security: Secure the application using JSON Web Tokens (JWT) for authentication and authorization.

Synchronous Communication: Use Feign client for inter-service communication in a synchronous manner.

Asynchronous Communication: Implement Apache Kafka for asynchronous communication between services.

Databases: Use MongoDB for NoSQL data storage and SQL for relational data.

API Gateway: Implement an API Gateway for routing and securing service calls.

Discovery Server: Use Eureka Discovery Server for service registration and discovery.

Circuit Breaker: Implement resilience patterns like Circuit Breaker to handle service failures gracefully.

Logging & Monitoring: Integrate logging and monitoring tools to track the application’s health and performance.

**Architecture Overview**

This application follows a microservices architecture, where each feature is encapsulated within its own service. The services communicate with each other either synchronously (using Feign client) or asynchronously (using Kafka). Below is a high-level overview of the architecture:

API Gateway: Routes external requests to appropriate services.

Discovery Server: Manages service registration and discovery.

Product Service: Handles product-related operations like viewing and adding products.

Inventory Service: Manages product inventory and stock availability.

Order Service: Processes orders and communicates with the Inventory and Mail services.

Mail Service: Sends email notifications upon successful order placement.

Auth Service: Manages user authentication and authorization using JWT.

Database Services: MongoDB is used for document-oriented storage, and SQL is used for relational data.

**Tech Stack**


Backend Framework: Spring Boot
Security: JWT
Communication: Feign Client (Synchronous), Apache Kafka (Asynchronous)
Databases: MongoDB, SQL (MySQL/PostgreSQL)
Service Discovery: Eureka Discovery Server
API Gateway: Spring Cloud Gateway
Resilience: Netflix Hystrix (Circuit Breaker)
Logging: Logback, Spring Boot Actuator
Monitoring: Prometheus, Grafana


Getting Started
Prerequisites
Java 17+
Maven 3.6+
Docker (for containerized deployment)
MongoDB and SQL (MySQL/PostgreSQL) databases
