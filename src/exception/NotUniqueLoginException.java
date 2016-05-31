package exception;

public class NotUniqueLoginException extends LogicException {
	private String login;

	public NotUniqueLoginException(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}
}
