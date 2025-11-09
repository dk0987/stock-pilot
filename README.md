

🚀 Stock-Pilot Backend Service

Overview

Welcome to the backend repository for Stock-Pilot, an Advanced Inventory Management System. This service is designed to be the robust and intelligent core of the application, providing not just transactional integrity but also predictive analytics to optimize inventory operations.

Built on Spring Boot and leveraging GraphQL for flexible API communication, Stock-Pilot adheres to a clean Resolver (Controller) -> Service -> Repository (JPA) architecture.
<img width="2564" height="1325" alt="image" src="https://github.com/user-attachments/assets/501c6e3d-b6cd-46af-b68a-faa92a717b56" />



✨ Core Features

Stock-Pilot is engineered to maximize efficiency and intelligence across your entire inventory workflow.

🛡️ 1. Role-Defined User Access (RBAC)

Authentication & Authorization: Implements robust security protocols (e.g., OAuth2/JWT) to secure all GraphQL endpoints.

Role-Based Access Control (RBAC): Access to critical operations is strictly controlled by defined roles:

ADMIN: Full system access, security configuration, and all user/role management.

INVENTORY_MANAGER: Oversight of all stock levels, managing product attributes, creating purchase orders, and reviewing deficiency alerts.

SALES_MANAGER: Access to sales history, current stock views, forecasting data, and sales-related reports.

APPROVAL: Dedicated authority for approving stock adjustments, large purchase orders, and major inter-warehouse transfers.

USER: Basic read access, viewing stock levels, and executing assigned transactions (e.g., scanning items).

Dedicated Services: Services are implemented to manage users and ensure all data access respects the defined role permissions.

📦 2. Comprehensive Inventory & Warehouse Management

Unified Data Model: Manages Products, Warehouses, Authors, and Books (as placeholder entities), all connected via UUID for global uniqueness.

Custom Product Attributes: GraphQL mutations support the dynamic addition of custom key-value pairs to product entities, allowing for enhanced, future-proof categorization (e.g., hazardous_level, shelf_life_days).

Warehouse CRUD: Full capabilities for creating, reading, updating, and deleting warehouse records.

📈 3. Warehouse Analytics Service & Live Dashboard Support

Summary Endpoints: Dedicated GraphQL queries are optimized to fetch aggregate data points necessary for the live dashboard.

Examples: Total Stock Value, Inventory Turnover Rate (ITR), Top 10 Most/Least Moved SKUs.

Real-Time Data Feeds: Utilizes Spring's reactive capabilities (or WebSockets via the GraphQL subscription specification) to push summarized inventory changes to the live dashboard, minimizing polling overhead.

Data Aggregation: The Service layer performs complex joins and calculations to provide actionable data, not just raw transactional records.

🧠 4. Machine Learning for Stock Deficiency Prediction

Predictive Service: Integrates with an internal ML model (e.g., a dedicated Python/TensorFlow service communicating via REST or gRPC).

Data Ingestion: The service feeds historical inventory, sales, seasonality, and lead-time data to the ML model.

Deficiency Alerts: Provides dedicated GraphQL queries to retrieve predicted stock deficiencies or overstock warnings, enabling proactive ordering and logistical planning.

ML Metrics: Tracks and exposes model performance metrics (accuracy, precision) for administrative review.

🛠 Technology Stack

Component

Technology

Role

Framework

Spring Boot 3.x

Application foundation and REST/HTTP handling.

API

Spring for GraphQL

Flexible, strongly-typed API layer.

Database

Spring Data JPA / Hibernate

ORM and persistence layer.

Data Store

H2 (in-memory dev) / PostgreSQL (prod)

Transactional data storage.

Data Types

Custom Scalars (UUID, DateTime)

Handles complex types natively across the GraphQL boundary.

Build Tool

Maven (pom.xml)

Dependency management and build lifecycle.

🏁 Getting Started

Prerequisites

Java 17+

Maven 3.6+

Building the Project

# Clone the repository
git clone [https://github.com/dk0987/stock-pilot/](https://github.com/dk0987/stock-pilot/)
cd stock-pilot

# Build the project
./mvnw clean package


Running the Application

The application uses an in-memory H2 database for development and loads sample data on startup (see DataLoaderConfig.java).

# Run the application
java -jar target/stock-pilot-0.0.1-SNAPSHOT.jar


The service will start on http://localhost:4000.
