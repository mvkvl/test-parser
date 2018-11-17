package test.parser.error;

public class InputRecordError extends RuntimeException {

	private static final long serialVersionUID = -4056906638065417181L;
	
	public InputRecordError(String msg) {
		super(msg);
	}
	public InputRecordError() {
		super();
	}
}
