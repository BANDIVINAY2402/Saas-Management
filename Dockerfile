# Use Maven image as the build stage
FROM maven:3.8.5-openjdk-17 AS build

# Copy the source code into the container
COPY . /app
WORKDIR /app

# Build the application
RUN mvn clean package -DskipTests

# Use a smaller image for the final stage
FROM openjdk:17.0.1-jdk-slim

# Copy the JAR file from the build stage
COPY --from=build /app/target/SaasManagementApllication-0.0.1-SNAPSHOT.jar /app/demo.jar
#COPY --from=build /app/target/collegeApplicationDeployment-0.0.1-SNAPSHOT.jar /app/demo.jar

# Copy the external file to the container


# Expose the application port
EXPOSE 8081

# Set the entry point for the application
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]
