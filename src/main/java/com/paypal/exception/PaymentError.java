package com.paypal.exception;
/**
* Java domain object for an error in the application
*
* @author  Antony Sibiya J
* @version 1.0
*/
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentError {

	private String errMsg;
	private String debugMessage;
	private String status;
	private HttpStatus httpStatus;
	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private PaymentError() {
		timestamp = LocalDateTime.now();
	}

	public PaymentError(String status) {
		this();
		this.status = status;
	}

	public PaymentError(HttpStatus httpStatus, Throwable ex) {
		this();
		this.httpStatus = httpStatus;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	public PaymentError(HttpStatus httpStatus, String message, Throwable ex) {
		this();
		this.httpStatus = httpStatus;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
