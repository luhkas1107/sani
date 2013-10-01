package br.com.sani.exception;

public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException() {
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable throwed) {
		super(throwed);
	}

	public DAOException(String message, Throwable throwed) {
		super(message, throwed);
	}

}