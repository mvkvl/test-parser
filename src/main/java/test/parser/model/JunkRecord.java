package test.parser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * == Junk == record - the record which can handle parsing errors
 * 
 * (added 2 types of records - Normal and Junk to support correct output 
 *  in final result; for example, when 'id', or 'amount' from input data 
 *  can't be parsed (if it's not a number), we still want to output source 
 *  data, so we use Junk record with 'String' as data type for 'id' and 'amount';
 *  from other side, when we output Normal record, we need 'id' and 'amount' to be 
 *  numbers in output, so they are declared as 'int' and 'float' in Normal record;
 *  
 *  sure we could stick to only one class, if we won't use Jackson and automatic 
 *  conversion to/from JSON)
 * 
 * @author kami
 *
 */
@JsonPropertyOrder({ "id", "amount", "currency", "comment", "filename", "line", "result" })
public class JunkRecord extends AbstractRecord {

	@JsonProperty("id")
	String  orderId;
	@JsonProperty("amount")
	String   amount;
	
	public JunkRecord() {}
	
	@Override
	public String toJSON() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}

	@Override
	public Record fromString(String record) {
		String[] parts = record.split(",", 4);  // maximum split into 4 parts (as comment can contain comma) 
		if (parts.length > 0)
			this.orderId  = parts[0]; 
		if (parts.length > 1)
			this.amount   = parts[1];
		if (parts.length > 2)
			this.currency = parts[2];
		if (parts.length > 3)
			this.comment  = parts[3];
		return this;
	}
	
}
