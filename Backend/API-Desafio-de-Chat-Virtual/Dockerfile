FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

# COPY /API-Desafio-de-Chat-Virtual/target/API-Desafio-de-Chat-Virtual-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
                                                                                                                                                                                                                                        