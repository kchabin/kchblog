# 빌드 스테이지
FROM gradle:8.7.0-jdk17 AS build
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN ./gradlew clean bootJar

# 실행 스테이지
FROM eclipse-temurin:17.0.15_6-jre-noble
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
