FROM openjdk:21-jdk

COPY target/javachallenge-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8081

CMD ["java", "-jar", "app/app.jar"]