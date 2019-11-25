FROM openjdk:8-jdk-alpine
VOLUME /tmp
# best practice is not to use root user
RUN adduser -D demo
USER demo
ADD target/apigateway-*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]