FROM openjdk:11-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} name-generator-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","-Xms256m","-Xmx2048m","/name-generator-service.jar"]

