FROM openjdk:8-jre
MAINTAINER xin
LABEL authors="zturn"


RUN mkdir -p /volume/log
WORKDIR /
ARG JAR_FILE
ADD target/${JAR_FILE} /backend.jar

EXPOSE 9901
ENTRYPOINT ["sh","-c","java -XX:+UseG1GC -XX:+ExitOnOutOfMemoryError -DJava.awt.headless=true -jar /backend.jar  --spring.profiles.active=prod --spring.config.location=classpath:/"]
