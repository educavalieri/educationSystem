FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/educationSystem-0.0.1-SNAPSHOT.jar educationSystem.jar
ENTRYPOINT ["java","-jar","/educationSystem.jar"]
