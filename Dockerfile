FROM eclipse-temurin:21-jdk-alpine
COPY ./src/main/resources/libs/*.jar /APP/libs/
COPY ./src/main/resources/static/scalajs/*.scala /APP/src/
COPY target/scala-stack-spring-boot-3-1.0-SNAPSHOT.war /APP/war/app.war
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/APP/war/app.war"]
