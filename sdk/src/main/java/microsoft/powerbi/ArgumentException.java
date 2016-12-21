package microsoft.powerbi;

public class ArgumentException extends RuntimeException {

	public ArgumentException(String message) {
		super(message);
	}

	public ArgumentException(String message, Object value) {
		super(message);
	}
}
