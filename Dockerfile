########## Etapa 1: Build ##########
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# 1. Copiamos el pom para cachear dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 2. Copiamos el código fuente
COPY src ./src

# 3. Empaquetamos el jar SIN correr pruebas
RUN mvn clean package -DskipTests -B

########## Etapa 2: Runtime ##########
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Perfil Spring que se usará en runtime si no se define otro
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-prod}

# Copiamos el JAR construido en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Puerto donde expone tu API Spring Boot
EXPOSE 8080

# Comando de inicio del contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]
