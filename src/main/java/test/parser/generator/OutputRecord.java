package test.parser.generator;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OutputRecord implements Serializable {

	private static final long serialVersionUID = -1743206692124699104L;
	
	String  orderId;
	String   amount;
	String currency;
	String  comment;
	
	public OutputRecord(int id, float amount, String currency, String comment) {
		this.orderId  = id+"";
		this.amount   = amount+"";
		this.currency = currency;
		this.comment  = comment;
	}
	public OutputRecord(String id, String amount, String currency, String comment) {
		this.orderId  = id;
		this.amount   = amount;
		this.currency = currency;
		this.comment  = comment;
	}

	public String getOrderId() {
		return orderId;
	}
	public String getAmount() {
		return amount;
	}
	public String getCurrency() {
		return currency;
	}
	public String getComment() {
		return comment;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String toString() {
		return this.orderId + ", " + this.amount + ", " + this.currency + ", " + this.comment;
	}
	public String toCSV() {
		return this.orderId + "," + this.amount + "," + this.currency + "," + this.comment;
	}
	public String toJSON() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}

}
