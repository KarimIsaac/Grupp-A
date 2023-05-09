FROM maven:3.8.5-openjdk-17-slim as build
COPY ./ /src
RUN mvn -f /src/pom.xml clean package

FROM eclipse-temurin:19-jre-alpine
COPY --from=build /src/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# WHEN RUNNING LOCAL !!!
# FROM openjdk:19-jdk-alpine
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]
