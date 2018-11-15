package test.parser.service;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
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
    	}
    	
    }

}
