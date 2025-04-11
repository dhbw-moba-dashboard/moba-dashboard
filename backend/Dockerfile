FROM gradle:8.10-jdk21 AS BUILD

ENV APP_HOME=/app/build/
WORKDIR $APP_HOME

COPY . .
RUN gradle build

FROM openjdk:23-jdk

ENV ARTIFACT_NAME=backend-0.0.1.jar
ENV APP_HOME=/app/

WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME/build/build/libs/$ARTIFACT_NAME .

EXPOSE 8080
ENTRYPOINT java -jar $ARTIFACT_NAME
