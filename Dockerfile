#./mvnw package && java -jar target/application-0.0.1-SNAPSHOT.jar

FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8082
ARG JAR_FILE=target/Application-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
CMD ["flask", "run", "--host", "0.0.0.0"]


#docker build -t application:0.0.1 .