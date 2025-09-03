

FROM openjdk:26-jdk-slim

WORKDIR /app

COPY target/toDoList.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java","-jar","app.jar"]