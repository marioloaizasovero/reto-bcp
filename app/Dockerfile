FROM gradle:7.4.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8-jdk-alpine
EXPOSE 8080:8080
RUN mkdir /bcp
COPY --from=build /home/gradle/src/build/libs/*.jar /bcp/app-bcp-challenge-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/bcp/app-bcp-challenge-0.0.1-SNAPSHOT.jar"]