FROM openjdk:8-jre-alpine

ARG JAR_FILE

ENV JAR_FILE=${JAR_FILE}

RUN mkdir /maven

COPY target/${JAR_FILE} /maven
COPY target/lib/byteman.jar /maven

ENTRYPOINT java $JAVA_OPTS -jar /maven/$JAR_FILE
EXPOSE 8080
