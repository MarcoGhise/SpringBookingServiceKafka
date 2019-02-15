package it.blog.kafka.booking.pdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableBinding(Sink.class)
public class BookingServicePdfApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingServicePdfApplication.class, args);
	}

}

