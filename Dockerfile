FROM eclipse-temurin:17
WORKDIR /usr/src/java-app
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/src/java-app/app.jar"]
