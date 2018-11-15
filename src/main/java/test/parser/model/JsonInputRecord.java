package test.parser.model;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import test.parser.error.InputRecordError;

/**
 *  This class is used during reading of JSON file
 *  (needed to map JSON string to Java Object and 
 *   convert it to string, which is used further 
 *   for Normal (or Junk) Record construction)
 * 
 */
public class JsonInputRecord {
	
	@JsonProperty("orderId")  String  orderId;
	@JsonProperty("amount")   String   amount;
	@JsonProperty("currency") String currency;
	@JsonProperty("comment")  String  comment;

	public JsonInputRecord fromJSON(String jsonStr) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonStr, JsonInputRecord.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new InputRecordError();
		} catch (IOException e) {
			e.printStackTrace();
			throw new InputRecordError();
		}
	}
	
	public String toString() {
		return this.orderId + "," + this.amount + "," + this.currency + "," + this.comment;
	}
}
