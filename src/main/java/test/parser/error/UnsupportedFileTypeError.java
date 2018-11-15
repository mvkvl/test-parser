package test.parser.error;

public class UnsupportedFileTypeError extends RuntimeException {

	private static final long serialVersionUID = 2034799080374966348L;

	public UnsupportedFileTypeError(String msg) {
		super(msg);
	}
	public UnsupportedFileTypeError() {
		super();
	}
}
