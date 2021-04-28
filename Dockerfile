FROM adoptopenjdk/openjdk11:alpine-jre

ADD target/demo*.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java","-jar","app.jar"]
