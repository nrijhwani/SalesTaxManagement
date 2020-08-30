package org.sales.tax.constants;

/**
 * ErrorMessage.java - a Java ENUM designed for storing information about
 * error messages.
 * 
 * @author - Neel Rijhwani | rijhwani.neel@gmail.com
 * @version 1.0
 */

public enum ErrorMessage {

	APPLICATION_ERROR("Some application error occured, please check logs for more details."),
	INVALID_INPUT("Provided input is invalid, please provide valid input."),
	QUANTITY_INVALID("Quantity is invalid input for ");

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	ErrorMessage(String message) {
		this.message = message;
	}

}
