FROM openjdk:17-jdk-alpine
MAINTAINER yohanes.cahya
COPY target/springboot-blog-rest-api-0.1.jar springboot-blog-rest-api-0.1.jar
ENTRYPOINT ["java","-jar","/springboot-blog-rest-api-0.1.jar"]