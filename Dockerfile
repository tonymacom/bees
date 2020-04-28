FROM openjdk:8-jre-alpine

LABEL maintainer="ittony.ma@gmail.com"

ARG JAR_FILE=target/bees*.jar

ADD ${JAR_FILE} /app.jar

CMD ["java", "-Xmx512m", "-XX:-UseGCOverheadLimit", "-jar", "/app.jar"]

EXPOSE 8080
