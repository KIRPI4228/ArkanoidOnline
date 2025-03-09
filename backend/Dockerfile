FROM gradle:8.7-jdk17-alpine as build
WORKDIR /backend
COPY . .
RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /backend

COPY --from=build /backend/build/libs/*.jar /build/backend.jar

EXPOSE 4444

ENTRYPOINT ["java", "-jar","/build/backend.jar"]