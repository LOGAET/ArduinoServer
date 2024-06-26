FROM maven:3.9.6 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
FROM openjdk:21
ENV TZ=Europe/Moscow
COPY --from=build /home/app/target/*.jar app.jar
EXPOSE 65000
ENTRYPOINT ["java","-jar","/app.jar"]
