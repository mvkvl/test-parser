package test.parser.parser;

import test.parser.error.InputRecordError;
import test.parser.model.JsonInputRecord;
import test.parser.model.JunkRecord;
import test.parser.model.NormalRecord;
import test.parser.model.Record;

/**
 * JSON-file data processor
 * 
 * @author kami
 *
 */
public class JSONParser extends AbstractParser  {

	private int lineNum = 0;
	
	/**
	 * parses one string from JSON file
	 * 
	 */
	@Override
	protected void processLine(String file, String line) {
		// loads JSON into Java Object
		// can't load directly to JunkRecord due to Jackson 
		// fields mapps difference (as fields in input and 
		// output are different)
		String str = new JsonInputRecord().fromJSON(line).toString();
		Record record;
		try {
			// try to create NormalRecord (if everything is being parsed correctly) 
			record = new NormalRecord().fromString(str);
			record.setStatus("OK");
		} catch (InputRecordError ex) {
			// fall back to JunkRecord 
			record = new JunkRecord().fromString(str);
			record.setStatus(ex.getMessage());
		}
		// set additional fields
		record.setLineNumber(++lineNum);
		record.setFileName(file);
		// output
		System.out.println(record.toJSON());
	}

}
