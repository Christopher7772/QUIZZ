# Étape 1 : Build avec Maven
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Image finale légère pour lancer l'app
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/QUIZZ-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
