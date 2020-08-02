package com.paypal.service;

/**
* Service to handle business logic
*
* @author  Antony Sibiya J
* @version 1.0
*/
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.dal.dao.PaymentDao;
import com.paypal.dal.dto.TransactionVO;
import com.paypal.dal.utils.TransactionPredicate;
import com.paypal.exception.PaymentInterfaceException;
import com.paypal.exception.PaymentNoDataFoundException;

@Service
public class PaymentService {

	@Autowired
	PaymentDao paymentDao;

	/**
	 * Transactions performed by all users on a specific day
	 * 
	 * @param inpuDate
	 * @return
	 * @throws PaymentInterfaceException
	 */
	public List<TransactionVO> getAllPaymentTnxsDayBasis(LocalDate inpuDate) throws PaymentInterfaceException {
		try {
			List<TransactionVO> transactionVOs = paymentDao.getAllTransactionFromDB();
			transactionVOs = transactionVOs.stream().filter(TransactionPredicate.onSpecificDate(inpuDate))
					.collect(Collectors.toList());
			if (transactionVOs.isEmpty()) {
				throw new PaymentNoDataFoundException();
			}
			return transactionVOs;
		} catch (PaymentInterfaceException e) {
			throw e;
		}
	}

	/**
	 * Transactions information specifically to a particular Applicant Id on the
	 * basis of year, month, day, hours.
	 * 
	 * @param userId
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @return
	 * @throws PaymentInterfaceException
	 */
	public List<TransactionVO> getPaymentTnxsForUser(String userId, String year, Optional<String> month,
			Optional<String> day, Optional<String> hour) throws PaymentInterfaceException {
		try {
			List<TransactionVO> transactionVOs = paymentDao.getAllTransactionFromDB();
			List<Predicate<TransactionVO>> allPredicates = new ArrayList<Predicate<TransactionVO>>();
			allPredicates.add(TransactionPredicate.onSpecificUserId(userId));
			allPredicates.add(TransactionPredicate.onYearBasis(Integer.parseInt(year)));
			if (month.isPresent())
				allPredicates.add(TransactionPredicate.onMonthBasis(month.get()));
			if (day.isPresent())
				allPredicates.add(TransactionPredicate.onDaysBasis(day.get()));
			if (hour.isPresent())
				allPredicates.add(TransactionPredicate.onHourBasis(Integer.parseInt(hour.get())));
			transactionVOs = transactionVOs.stream().filter(allPredicates.stream().reduce(x -> true, Predicate::and))
					.collect(Collectors.toList());
			if (transactionVOs.isEmpty()) {
				throw new PaymentNoDataFoundException();
			}
			return transactionVOs;
		} catch (NumberFormatException num) {
			throw new PaymentInterfaceException(num.getMessage());
		} catch (PaymentInterfaceException e) {
			throw e;
		}
	}

	/**
	 * Transactions information specifically to a particular Applicant on particular
	 * transaction type.
	 * 
	 * @param userId
	 * @param tnxType
	 * @return
	 * @throws PaymentInterfaceException
	 */
	public List<TransactionVO> getPaymentTnxsForTnxType(String userId, String tnxType)
			throws PaymentInterfaceException {
		try {
			List<TransactionVO> transactionVOs = paymentDao.getAllTransactionFromDB();
			List<Predicate<TransactionVO>> allPredicates = new ArrayList<Predicate<TransactionVO>>();
			allPredicates.add(TransactionPredicate.onSpecificUserId(userId));
			allPredicates.add(TransactionPredicate.onSpecificTnxType(tnxType));
			transactionVOs = transactionVOs.stream().filter(allPredicates.stream().reduce(x -> true, Predicate::and))
					.collect(Collectors.toList());
			if (transactionVOs.isEmpty()) {
				throw new PaymentNoDataFoundException();
			}
			return transactionVOs;
		} catch (PaymentInterfaceException e) {
			throw e;
		}
	}

	/**
	 * Transactions information specifically to a particular Applicant who should be
	 * the logged in user
	 * 
	 * @param userId
	 * @param loggedInUser
	 * @return
	 * @throws PaymentInterfaceException
	 */
	public List<TransactionVO> getPaymentTnxsForLoggedInUser(String userId, String loggedInUser)
			throws PaymentInterfaceException {
		try {
			List<TransactionVO> transactionVOs = paymentDao.getAllTransactionFromDB();
			List<Predicate<TransactionVO>> allPredicates = new ArrayList<Predicate<TransactionVO>>();
			allPredicates.add(TransactionPredicate.onSpecificUserId(userId));
			allPredicates.add(TransactionPredicate.onSpecificUserId(loggedInUser));
			transactionVOs = transactionVOs.stream().filter(allPredicates.stream().reduce(x -> true, Predicate::and))
					.collect(Collectors.toList());
			if (transactionVOs.isEmpty()) {
				throw new PaymentNoDataFoundException();
			}
			return transactionVOs;
		} catch (PaymentInterfaceException e) {
			throw e;
		}
	}

}
