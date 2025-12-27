# -------- Build Stage --------
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy only pom.xml first to download dependencies
COPY pom.xml .

RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# -------- Run Stage --------
FROM eclipse-temurin:21-jdk AS runner

WORKDIR /app

# Copy the jar from builder stage
COPY --from=builder /app/target/warehouse-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4007

ENTRYPOINT ["java","-jar","app.jar"]
