FROM openjdk:11-jre-slim
EXPOSE 8080
COPY ./target/irunninglog.jar irunninglog.jar
ENTRYPOINT ["java","-jar","irunninglog.jar"]