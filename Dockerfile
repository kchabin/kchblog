# 빌드 스테이지
FROM gradle:8.7-jdk17-alpine AS build
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN ./gradlew clean bootJar

# 실행 스테이지
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
