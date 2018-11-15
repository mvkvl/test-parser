package test.parser.model;

/**
 * common Record interface
 * 
 * @author kami
 *
 */
public interface Record {
	public Record fromString(String recordStr);
	public String toJSON();
	public void setStatus(String status);
	public void setFileName(String fileName);
	public void setLineNumber(int line);
}
