package com.paypal.exception;
/**
* Custom exception class to hold interface related exceptions in the application
*
* @author  Antony Sibiya J
* @version 1.0
*/
public class PaymentInterfaceException extends Exception {
	private String message;
	private String errorDesc;
	private String errorStackTrace;
	private static final long serialVersionUID = 1L;
	private Exception exception;
	
	public PaymentInterfaceException() {
		super();
	}
	
	public PaymentInterfaceException(Exception ex) {
		super(ex);
		this.exception = ex;
		this.errorDesc = ex.getMessage();
		this.errorStackTrace = ex.toString();
	}
	
	public PaymentInterfaceException(String message) {
		super();
		this.message=message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getErrorStackTrace() {
		return errorStackTrace;
	}

	public void setErrorStackTrace(String errorStackTrace) {
		this.errorStackTrace = errorStackTrace;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	
}
