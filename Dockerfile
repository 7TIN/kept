# Stage 1: Build the application with Maven using Java 24
FROM maven:3.5.3-eclipse-temurin-24 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create a slim final image with Java 24
FROM eclipse-temurin:24-jre-alpine
WORKDIR /app
COPY --from=build /app/target/kept-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]