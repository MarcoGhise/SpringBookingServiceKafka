package it.blog.kafka.booking.pdf;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfController {

	private static final Log logger = LogFactory.getLog(PdfController.class);
	
	@Autowired
	PdfGenaratorUtil pdfGenaratorUtil;
	
	@RequestMapping(method = RequestMethod.POST, path="/generate")
    public ResponseEntity<String> generateBoardingCard(@RequestBody Booking booking) throws Exception{
    	
		logger.info("Boarding Card:" + booking.getCode());
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("name", booking.getName());
		data.put("surname", booking.getSurname());
		data.put("code", booking.getCode());
		data.put("flightNumber", booking.getFlightNumber());
		data.put("seat", booking.getSeat());
		pdfGenaratorUtil.createPdf("boardingcard", data);
		
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
