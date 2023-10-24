FROM openjdk:17

ADD target/test-0.0.1-SNAPSHOT.jar spring-boot-test.jar

ENTRYPOINT ["java", "-jar", "spring-boot-test.jar"]