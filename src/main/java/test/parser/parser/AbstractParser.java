package test.parser.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 
 * 
 * 
 * @author kami
 *
 */
public abstract class AbstractParser implements Parser {

	/**
	 * Common parse method for all parsers
	 * - read a file line by line and process each of them 
	 * 
	 */
	@Override
	public void parse(String fileName) {
		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(line -> processLine(fileName, line));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * abstract class should be redefined in each exact parser
	 * 
	 * @param fileName
	 * @param line
	 */
	protected abstract void processLine(String fileName, String line);
	
}
