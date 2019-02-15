package it.blog.kafka.booking.pdf;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;


@Component
public class KafkaConsumer {

	private static final Log logger = LogFactory.getLog(KafkaConsumer.class);
	
	@Autowired
	PdfGenaratorUtil pdfGenaratorUtil;

	@StreamListener(Sink.INPUT)
    public void loggerSink(Booking booking) throws Exception {
        logger.info("Received a message: " + booking.toString());
        
    	Map<String, String> data = new HashMap<String, String>();
		data.put("name", booking.getName());
		data.put("surname", booking.getSurname());
		data.put("code", booking.getCode());
		data.put("flightNumber", booking.getFlightNumber());
		data.put("seat", booking.getSeat());
		pdfGenaratorUtil.createPdf("boardingcard", data);
    }
}
