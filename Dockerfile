FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
# Download all dependencies
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copy the built jar file from the build stage
COPY --from=build /app/target/*.jar app.jar
# Copy resources that might be needed at runtime
COPY --from=build /app/src/main/resources/admins.xlsx /app/admins.xlsx

# Environment variables with defaults that can be overridden at runtime
ENV DATABASE_URL=jdbc:postgresql://postgres:5432/sst-internal-tools \
    DATABASE_USER=sst \
    DATABASE_PASSWORD=tools \
    GOOGLE_CLIENT_ID="1064457293396-05f0n109k8qj814qvuruct1vevr7at0q.apps.googleusercontent.com" \
    GOOGLE_CLIENT_SECRET="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" \
    EXCEL_FILE_PATH=/app/admins.xlsx \
    SERVER_PORT=8080 \
    JWT_SECRET_KEY="defaultSecretKeyThatShouldBeChanged" \
    JWT_ACCESS_EXPIRATION=3600000 \
    JWT_REFRESH_EXPIRATION=86400000

EXPOSE ${SERVER_PORT}




ENTRYPOINT ["java", "-jar", "/app/app.jar"]
