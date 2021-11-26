FROM openjdk:11-bullseye
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} task.jar
ENTRYPOINT ["java","-jar","/task.jar"]