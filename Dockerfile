# Etapa 1: Construcción
FROM maven:3.9.9-eclipse-temurin-17 AS builder

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el archivo pom y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el resto del código y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera para producción
FROM eclipse-temurin:17-jdk

# Establecer variables de entorno
ENV SPRING_PROFILES_ACTIVE=prod \
    JAVA_OPTS=""

# Crear directorio para la aplicación
WORKDIR /app

# Copiar el JAR construido desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto que usará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]