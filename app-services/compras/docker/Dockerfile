## 1 - Build Stage
#FROM gradle:6.3-jdk8 as builder
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle build
#
## 2 - Running Stage
#FROM openjdk:8-jre-alpine
#RUN apk update && apk add --no-cache tzdata
#ENV TZ="America/Sao_Paulo"
#RUN addgroup -S spring && adduser -S spring -G spring
#RUN mkdir /app
#RUN chown -R spring:spring /app
#USER spring:spring
#COPY --from=builder --chown=spring:spring /home/gradle/src/build/libs/*.jar /app/app.jar
#EXPOSE 8080
#WORKDIR /app
#CMD ["java","-jar","app.jar"]


FROM openjdk:8-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]