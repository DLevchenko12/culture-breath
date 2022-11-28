FROM maven:3.8.1-jdk-11 AS MAVEN_BUILD

COPY ./ ./

RUN mvn clean package -DskipTests

FROM openjdk:11-oracle

COPY --from=MAVEN_BUILD /target/culture-breath-exhibitions-back-0.0.1-SNAPSHOT.jar /Exhibitions.jar

CMD ["java", "-jar", "/Exhibitions.jar"]

EXPOSE 8080