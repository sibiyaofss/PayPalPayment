package com.paypal.controller;

/**
* Controller to handle as requied APIS
*
* @author  Antony Sibiya J
* @version 1.0
*/
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.dal.dto.TransactionVO;
import com.paypal.exception.PaymentInterfaceException;
import com.paypal.exception.PaymentNoDataFoundException;
import com.paypal.service.PaymentService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
public class PaymentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	HttpSession httpSession;

	@Autowired
	PaymentService paymentService;

	@ApiOperation(value = "API to get transactions performed by all users on a specific day", 
			notes = "Date should be inputted", response = String.class, responseContainer = "List")
	@GetMapping(value = "/getAllPaymentTnxsDayBasis")
	public ResponseEntity<List<TransactionVO>> getAllPaymentTnxsDayBasis(
			@RequestParam("inputDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inpuDate)
			throws PaymentInterfaceException {
		try {
			return new ResponseEntity<>(paymentService.getAllPaymentTnxsDayBasis(inpuDate), HttpStatus.OK);
		} catch (PaymentNoDataFoundException e) {
			LOGGER.error(e.getMessage());
			throw new PaymentNoDataFoundException(e.getMessage());
		} catch (PaymentInterfaceException e) {
			LOGGER.error(e.getMessage());
			throw new PaymentInterfaceException(e.getMessage());
		}
	}

	@ApiOperation(value = "API to get transactions information specifically to a particular Applicant Id on the basis of year, month, day, hours. ", 
			notes = "Applicant Id and Year.month,day and hour should be specified", response = String.class, responseContainer = "List")
	@GetMapping(value = { "/getPaymentTnxsForUser/{userId}/{year}", "/getPaymentTnxsForUser/{userId}/{year}/{month}",
			"/getPaymentTnxsForUser/{userId}/{year}/{month}/{day}",
			"/getPaymentTnxsForUser/{userId}/{year}/{month}/{day}/{hour}" })
	public ResponseEntity<List<TransactionVO>> getPaymentTnxsForUser(@PathVariable String userId,
			@PathVariable String year, @PathVariable(required = false) Optional<String> month,
			@PathVariable(required = false) Optional<String> day, @PathVariable(required = false) Optional<String> hour)
			throws PaymentInterfaceException {
		try {
			return new ResponseEntity<>(paymentService.getPaymentTnxsForUser(userId, year, month, day, hour),
					HttpStatus.OK);
		} catch (PaymentNoDataFoundException e) {
			LOGGER.error(e.getMessage());
			throw new PaymentNoDataFoundException(e.getMessage());
		} catch (PaymentInterfaceException e) {
			LOGGER.error(e.getMessage());
			throw new PaymentInterfaceException(e.getMessage());
		}
	}

	@ApiOperation(value = "API to get transactions information specifically to a particular Applicant  on particular transaction type. ", 
			notes = "Applicant Id and transaction type should be specified", response = String.class, responseContainer = "List")
	@GetMapping(value = "/getPaymentTnxsForTnxType/{userId}/{tnxType}")
	public ResponseEntity<List<TransactionVO>> getPaymentTnxsForTnxType(@PathVariable String userId,
			@PathVariable String tnxType) throws PaymentInterfaceException {
		try {
			return new ResponseEntity<>(paymentService.getPaymentTnxsForTnxType(userId, tnxType), HttpStatus.OK);
		} catch (PaymentNoDataFoundException e) {
			LOGGER.error(e.getMessage());
			throw new PaymentNoDataFoundException(e.getMessage());
		} catch (PaymentInterfaceException e) {
			LOGGER.error(e.getMessage());
			throw new PaymentInterfaceException(e.getMessage());
		}
	}

	@ApiOperation(value = "API to get transactions information specifically to a particular Applicant  who should be the logged in user. ", 
			notes = "Applicant Id should be specified", response = String.class, responseContainer = "List")
	@GetMapping(value = "/getPaymentTnxsForLoggedInUser/{userId}")
	public ResponseEntity<List<TransactionVO>> getPaymentTnxsForLoggedInUser(@PathVariable String userId)
			throws PaymentInterfaceException {
		try {
			return new ResponseEntity<>(paymentService.getPaymentTnxsForLoggedInUser(userId,
					(String) httpSession.getAttribute("loggedInUser")), HttpStatus.OK);
		} catch (PaymentNoDataFoundException e) {
			LOGGER.error(e.getMessage());
			throw new PaymentNoDataFoundException(e.getMessage());
		} catch (PaymentInterfaceException e) {
			LOGGER.error(e.getMessage());
			throw new PaymentInterfaceException(e.getMessage());
		}
	}

}
