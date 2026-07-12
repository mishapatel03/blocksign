# Use Java 21
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy maven wrapper and pom.xml first
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:resolve

# Copy source code
COPY src src

# Build the app
RUN ./mvnw clean package -DskipTests

# Run the app
EXPOSE 8888
CMD ["java", "-jar", "target/config-server-1.0.0.jar"]