FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
RUN mkdir -p /app/
COPY target/api-0.0.1-SNAPSHOT.jar /app/api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "/app/api.jar"]