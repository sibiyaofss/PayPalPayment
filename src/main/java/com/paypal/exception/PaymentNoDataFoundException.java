package com.paypal.exception;
/**
* Custom exception class to hold data related exceptions in the application
*
* @author  Antony Sibiya J
* @version 1.0
*/
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class PaymentNoDataFoundException extends PaymentInterfaceException {
	private static final long serialVersionUID = 8284403263570905358L;
	

	private String message="No Data Found";
	private String errorDesc;
	private String errorStackTrace;
	private Exception exception;
	
	public PaymentNoDataFoundException() {
		super();
	}
	
	public PaymentNoDataFoundException(Exception ex) {
		super(ex);
		this.exception = ex;
		this.errorDesc = ex.getMessage();
		this.errorStackTrace = ex.toString();
	}
	
	public PaymentNoDataFoundException(String message) {
		super();
		this.message=message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	@Override
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String getErrorDesc() {
		return errorDesc;
	}
	@Override
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	@Override
	public String getErrorStackTrace() {
		return errorStackTrace;
	}
	@Override
	public void setErrorStackTrace(String errorStackTrace) {
		this.errorStackTrace = errorStackTrace;
	}
	@Override
	public Exception getException() {
		return exception;
	}
	@Override
	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	


}
