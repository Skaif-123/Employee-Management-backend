# 🛠️ Build Stage
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn -B dependency:resolve dependency:resolve-plugins

# Copy source code and build the JAR
COPY src ./src
RUN mvn -B clean package -DskipTests

# 🏗️ Runtime Stage (Optimized JRE Image)
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/ems-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Define default command
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
