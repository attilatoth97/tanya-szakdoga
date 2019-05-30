package hu.szakdolgozat.tanya.exception;

public class TanyaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TanyaException() {
		super();
	}

	public TanyaException(String message) {
		super(message);
	}

}
