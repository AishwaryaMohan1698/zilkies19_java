package io.ztech.expensesapp.exceptions;

public class LoginFailedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginFailedException(String s) {
		super(s);
	}
}
