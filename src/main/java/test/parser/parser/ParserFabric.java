package test.parser.parser;

import test.parser.error.UnsupportedFileTypeError;

/**
 * ParserFabric to get Parser of needed type;
 * here we can add support for other input file types
 * 
 */
public class ParserFabric {

	public Parser getParser(String type) {
		if (type.equalsIgnoreCase("csv"))
			return new CSVParser();
		else if (type.equalsIgnoreCase("json"))
			return new JSONParser();
		// here we can extend supported file types
		// else if (type.equalsIgnoreCase("xlsx"))
		//     return new XLSXParser();
		
		// unsupported file type passed - throw an error 
		throw new UnsupportedFileTypeError("file type 'type'");
	}

}
