package test.parser.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractRecord implements Record {

	@JsonProperty("filename")
	private String   fileName;
	@JsonProperty("result")
	private String     status;
	@JsonProperty("line")
	private int    lineNumber;
	String           currency;
	String            comment;

	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
