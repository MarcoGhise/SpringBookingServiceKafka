package it.blog.kafka.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

@Component
public class KafkaSender {

	private Source source;

    private static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    public KafkaSender(Source source){
        this.source = source;
    }

    public void publishBoardingCard(Booking booking){
       logger.debug("Sending Kafka message {}", booking);

       source.output().send(MessageBuilder.withPayload(booking).setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
    }
}
