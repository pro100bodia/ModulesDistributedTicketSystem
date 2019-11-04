FROM openjdk:11
COPY services/target/services-1.1-SNAPSHOT-exec.jar /app.jar
CMD ["C:\Program Files\Java\jdk-11.0.4", "-jar", "/app.jar"]

