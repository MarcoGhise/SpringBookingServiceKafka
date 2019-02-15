FROM openjdk:8-jdk-alpine 
VOLUME /tmp
COPY target/Booking-service-pdf-1.0.0.jar pdf-service.jar
RUN mkdir /boardingcard
ENV JAVA_OPTS=""
ENTRYPOINT exec java -jar /pdf-service.jar --debug
EXPOSE 9080