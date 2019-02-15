package it.blog.kafka.booking.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

@Component
public class PdfGenaratorUtil {

	@Autowired
	private TemplateEngine templateEngine;
	
	@Value("${output.directory}")
	String outputDirectory;
	
	private static final Log logger = LogFactory.getLog(PdfGenaratorUtil.class);
	
	private String getFilename(String code, String name, String surname)
	{
		String filename = code + "_" + name.toLowerCase() + "_" + surname.toLowerCase();
		filename = filename.replaceAll("[^a-zA-Z0-9.-]", "_");
		
		return filename;
		
	}

	public void createPdf(String templateName, Map<String, String> map) throws Exception {
		Assert.notNull(templateName, "The templateName can not be null");
		Context ctx = new Context();
		if (map != null) 
			map.entrySet().stream().forEach(e -> ctx.setVariable(e.getKey(), e.getValue()));
		
		String processedHtml = templateEngine.process(templateName, ctx);
		  FileOutputStream os = null;
		  
		  String fileName = getFilename(map.get("code"), map.get("name"), map.get("surname"));
		  
	        try {
	            final File outputFile = new File(outputDirectory + "/" + fileName + ".pdf");
	            boolean isExists = outputFile.exists();
	            
	            if (isExists)
	            	outputFile.delete();	
	            
	            outputFile.createNewFile();
	            
	            os = new FileOutputStream(outputFile);

	            ITextRenderer renderer = new ITextRenderer();
	            renderer.getFontResolver().addFont("verdana.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	            renderer.setDocumentFromString(processedHtml);
	            renderer.layout();
	            renderer.createPDF(os, false);
	            renderer.finishPDF();
	            logger.info("PDF created successfully");
	        }
	        catch(Exception e)
	        {
	        	System.out.println(e.getMessage());
	        }
	        finally {
	            if (os != null) {
	                try {
	                    os.close();
	                } catch (IOException e) { }
	            }
	        }
	}
}