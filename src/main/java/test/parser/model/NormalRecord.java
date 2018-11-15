package test.parser.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import test.parser.error.InputRecordError;

/**
 * 
 * == Normal == record - the record which can be parsed without errors
 * 
 * (added 2 types of records - Normal and Junk to support correct output 
 *  in final result; for example, when 'id', or 'amount' from input data 
 *  can't be parsed (if it's not a number), we still want to output source 
 *  data, so we use Junk record with 'String' as data type for 'id' and 'amount';
 *  from other side, when we output Normal record, we need 'id' and 'amount' to be 
 *  numbers in output, so they are declared as 'int' and 'float' in Normal record;
 *  
 *  sure we could stick to only one class, if we won't use Jackson and automatic 
 *  conversion to JSON)
 * 
 * @author kami
 *
 */
@JsonPropertyOrder({ "id", "amount", "currency", "comment", "filename", "line", "result" })
public class NormalRecord extends AbstractRecord {

	@JsonProperty("id")
	int     orderId;
	
	@JsonProperty("amount")
	float    amount;

	private Set<String> supportedCurrencies = new HashSet<String>(Arrays.asList("RUB", "USD", "EUR", "JPY", "CHF"));

	public NormalRecord() {}

	public String toJSON() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}

	public Record fromString(String record) {
		String[] parts = record.split(",");
		try {
			this.orderId = Integer.parseInt(parts[0]);} 
		catch (NumberFormatException ex) {
			throw new InputRecordError("orderId is not a number");}
		try {
			this.amount = Float.parseFloat(parts[1]);}
		catch (NumberFormatException ex) {
			throw new InputRecordError("amount is not a number");}
		currency = parts[2];
		if (currency == null || currency.isEmpty())
			throw new InputRecordError("currency not set");
		if (!supportedCurrencies.contains(currency.toUpperCase()))
			throw new InputRecordError("currency not supported");
		comment = parts[3];
		if (comment == null || comment.isEmpty())
			throw new InputRecordError("comment not set");

		return this;
	}

}
