FROM gradle:8.7-jdk17-alpine as build
WORKDIR /game-backend
COPY . .
RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /game-backend

COPY --from=build /game-backend/build/libs/*.jar /build/game-backend.jar

EXPOSE 4444

ENTRYPOINT ["java", "-jar","/build/game-backend.jar"]