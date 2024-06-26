FROM maven:3.8.6-amazoncorretto-8 AS build

COPY . /app

WORKDIR /app

RUN --mount=type=cache,target=/root/.m2 mvn clean install

FROM amazoncorretto:8

COPY --from=build /app/target /app

COPY .env /app

WORKDIR /app

EXPOSE 8081

CMD java -cp SOAP_Service-1.0-SNAPSHOT.jar org.example.Main
