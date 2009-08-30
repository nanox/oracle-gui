/**
 * 
 */
package com.gs.oracle;

/**
 * @author Green Moon
 *
 */
public class ApplicationException extends Exception {

	private String code, message;
	
	public ApplicationException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public ApplicationException() {
		this.code = "UNKNOWN";
	}

	public ApplicationException(String string) {
		super(string);
		message = string; 
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	
}
