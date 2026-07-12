FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN ./mvnw dependency:resolve

COPY src src

RUN ./mvnw clean package -DskipTests

EXPOSE 8086

CMD ["java", "-jar", "target/auth-service-1.0.0.jar"]