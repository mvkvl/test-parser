package test.parser.parser;

import test.parser.error.InputRecordError;
import test.parser.model.JunkRecord;
import test.parser.model.NormalRecord;
import test.parser.model.Record;

/**
 * CSV-file data processor
 * 
 * @author kami
 *
 */
public class CSVParser extends AbstractParser  {

	private int lineNum = 0;
	
	/**
	 * parses one string from CSV file
	 * 
	 */
	@Override
	protected void processLine(String file, String line) {
		Record record;
		try {
			// try to create NormalRecord (if everything is being parsed correctly) 
			record = new NormalRecord().fromString(line);
			record.setStatus("OK");
		} catch (InputRecordError ex) {
			// fall back to JunkRecord 
			record = new JunkRecord().fromString(line);
			record.setStatus(ex.getMessage());
		}
		// set additional fields
		record.setLineNumber(++lineNum);
		record.setFileName(file);
		// output
		System.out.println(record.toJSON());
	}
	
}
