FROM openjdk:8-jdk-alpine
ARG JAR_FILE=hackathon-backend.jar
RUN mkdir -p /app
COPY ${JAR_FILE} /app/winner-app.jar
ENTRYPOINT ["java","-jar","/app/winner-app.jar"]