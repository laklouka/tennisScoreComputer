# Use Java 21 official image
FROM openjdk:21-ea
VOLUME /tmp
EXPOSE 8080
COPY target/tennisGameBackend-1.0-SNAPSHOT.jar tennisGameBackend.jar
ENTRYPOINT ["java", "-jar", "tennisGameBackend.jar"]