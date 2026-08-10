FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/ms-multas-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","app.jar"]
