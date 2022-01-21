FROM adoptopenjdk/openjdk11:jre-11.0.13_8-alpine

EXPOSE 8081

COPY target/Homework-jclo-5-0.0.1-SNAPSHOT.jar myprod.jar

CMD ["java", "-jar", "/myprod.jar"]