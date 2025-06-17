FROM eclipse-temurin:11-jre

# Set the working directory
WORKDIR /app

# Copy the packaged application
COPY target/moedaestudantil-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the entry point for the application
ENTRYPOINT ["java", "-jar", "app.jar"]