FROM openjdk:8
EXPOSE 8080
ADD /build/libs/pin-manager-0.0.1-SNAPSHOT.jar pin-manager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "pin-manager-0.0.1-SNAPSHOT.jar"]

