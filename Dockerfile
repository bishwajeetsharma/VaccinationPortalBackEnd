FROM adoptopenjdk/openjdk11:alpine-jre

ADD target/*.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","app.jar"]
