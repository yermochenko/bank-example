package exception;

public class LogicException extends Exception {
	public LogicException() {}

	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogicException(String message) {
		super(message);
	}

	public LogicException(Throwable cause) {
		super(cause);
	}
}
