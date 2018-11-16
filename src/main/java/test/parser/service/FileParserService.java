package test.parser.service;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import test.parser.parser.ParserFabric;

/**
 * Spring Boot File Parser Service
 * 
 * @author kami
 *
 */
@Service
public class FileParserService {

	Logger logger = LoggerFactory.getLogger(FileParserService.class);
	
	/**
	 * parse given file with needed parser
	 * (needed parser is detected by file extension)
	 * 
	 * @param file
	 */
    public void parse(String fileName) {
    	// if file exists, parse it
    	File file = new File(fileName);
    	if(file.exists() && !file.isDirectory()) { 
    		new ParserFabric().getParser(FilenameUtils.getExtension(fileName)).parse(fileName);
    		logger.info("File " + fileName + " parsed successfully");
    	} else {
    		logger.error("File " + fileName + " not found");
    	}
    }
    
}
