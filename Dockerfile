FROM maven:3.9.1-amazoncorretto-19-debian AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM amazoncorretto:19
COPY --from=build /usr/src/app/target/most-popular-words-1.0-SNAPSHOT.jar /usr/src/app/most-popular-words.jar
COPY README.md /usr/src/app/
WORKDIR /usr/src/app/
CMD ["java", "-jar", "/usr/src/app/most-popular-words.jar"]