package org.sales.tax.customException;

public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = -8577096840634031972L;
	private String message;

	public InvalidDataException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
