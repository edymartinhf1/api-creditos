FROM openjdk:17.0.2
WORKDIR /app
COPY ./target/api-creditos-0.0.1-SNAPSHOT.jar .
EXPOSE 8082
ENTRYPOINT ["java","-jar","api-creditos-0.0.1-SNAPSHOT.jar"]


