FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app               # This is OK – it's just a build directory
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app               # Runtime dir – separate from build
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
