FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/Booking-service-mvc-1.0.jar mvc-service.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java -jar /mvc-service.jar --debug
EXPOSE 3333