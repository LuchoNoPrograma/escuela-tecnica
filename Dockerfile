# 1) Construir el jar con maven, asi obtener un jar
FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# 2) Copiar el jar y ejecutarlo
FROM openjdk:17-jdk-alpine

# Argumentos
COPY --from=build /target/*.jar escuela-tecnica.jar

#Puerto del contenedor
EXPOSE 9090
# Comandos por ejecutarse en el contenedor
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker","escuela-tecnica.jar"]