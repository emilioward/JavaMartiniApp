FROM openjdk:11-slim
COPY ./target/SpringBootDemo11-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "SpringBootDemo11-0.0.1-SNAPSHOT.jar"]