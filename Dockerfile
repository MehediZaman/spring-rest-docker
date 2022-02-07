FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar spring-rest-docker.jar
ENTRYPOINT ["java","-jar","/spring-rest-docker.jar"]