FROM maven:3.9.9-eclipse-temurin-23 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -Dmaven.compiler.source=23 -Dmaven.compiler.target=23

FROM eclipse-temurin:23-jre
WORKDIR /app
COPY --from=build /app/target/wallet-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]