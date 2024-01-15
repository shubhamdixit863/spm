# Stage 1: Build the application with Maven and JDK 18
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn clean package

# Stage 2: Create the Docker container with the compiled JAR using JDK 18
FROM openjdk:18-jdk
WORKDIR /app
COPY --from=build /app/target/arealinterpolation-0.0.1-SNAPSHOT.jar /app/my-app.jar
ENTRYPOINT ["java", "-jar", "/app/my-app.jar"]
