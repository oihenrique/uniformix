FROM maven:4.0.0 as build
WORKDIR /app
COPY . .
RUN mvn clean package -X -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build ./app/target/*.jar ./springapi.jar
ENTRYPOINT java -jar springapi.jar