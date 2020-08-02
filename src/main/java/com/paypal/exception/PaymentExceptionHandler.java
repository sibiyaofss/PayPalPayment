package com.paypal.exception;
/**
* Exception handler class to propagate all exceptions caught in the application
*
* @author  Antony Sibiya J
* @version 1.0
*/

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice(annotations = RestController.class)
public class PaymentExceptionHandler extends ResponseEntityExceptionHandler {

	static String notFound = "NOT FOUND";
	static String intSerErr = "INTERNAL SERVER ERROR";
	static String otherExc = "OTHER_EXCEPTION";
	static String failed = "Failed";
	@ExceptionHandler(PaymentNoDataFoundException.class)
	public ResponseEntity<PaymentError> notFoundException(final PaymentNoDataFoundException e, WebRequest request) {
		PaymentError apiError = new PaymentError(notFound);
		apiError.setErrMsg(notFound);
		apiError.setDebugMessage(e.getMessage());
		apiError.setHttpStatus(HttpStatus.FORBIDDEN);
		apiError.setStatus(failed);
		apiError.setMessage("Transaction Details not available for the requested info");
		return new ResponseEntity<>(apiError,  HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(PaymentInterfaceException.class)
	public ResponseEntity<PaymentError> commonException(final PaymentInterfaceException e, WebRequest request) {
		PaymentError apiError = new PaymentError(intSerErr);
		apiError.setErrMsg(e.getMessage());
		apiError.setDebugMessage(e.getMessage());
		apiError.setHttpStatus(HttpStatus.FORBIDDEN);
		apiError.setStatus(failed);
		return new ResponseEntity<>(apiError,HttpStatus.FORBIDDEN);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<PaymentError> allException(final Exception e, WebRequest request) {
		PaymentError apiError = new PaymentError(otherExc);
		apiError.setErrMsg(e.getMessage());
		apiError.setDebugMessage(e.getMessage());
		apiError.setHttpStatus(HttpStatus.FORBIDDEN);
		apiError.setStatus(failed);
		return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
	}

}
