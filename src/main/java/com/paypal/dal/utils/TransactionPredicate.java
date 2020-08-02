package com.paypal.dal.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.function.Predicate;

import com.paypal.dal.dto.TRANSACTION_TYPE;
import com.paypal.dal.dto.TransactionVO;

public class TransactionPredicate {

	/**
	 * Filter based on date
	 * 
	 * @param inpuDate
	 * @return
	 */
	public static Predicate<TransactionVO> onSpecificDate(LocalDate inpuDate) {
		return p -> p.getTransactionDateTime().toLocalDate().compareTo(inpuDate) == 0;
	}

	/**
	 * Filter based on Applicant id
	 * 
	 * @param userId
	 * @return
	 */
	public static Predicate<TransactionVO> onSpecificUserId(String userId) {
		return p -> p.getApplicantId().equals(userId);
	}

	/**
	 * Filter based on transaction type
	 * 
	 * @param tnxType
	 * @return
	 */
	public static Predicate<TransactionVO> onSpecificTnxType(String tnxType) {
		return p -> p.getTransaction_TYPE() == (TRANSACTION_TYPE.valueOf(tnxType));
	}

	/**
	 * Year basis
	 * 
	 * @param inpuDate
	 * @return
	 */
	public static Predicate<TransactionVO> onYearBasis(int inpuDate) {
		return p -> p.getTransactionDateTime().getYear() == inpuDate;
	}

	/**
	 * Month Basis
	 * 
	 * @param month
	 * @return
	 */
	public static Predicate<TransactionVO> onMonthBasis(String month) {
		return p -> p.getTransactionDateTime().getMonth() == Month.valueOf(month.toUpperCase());
	}

	/**
	 * Day Basis
	 * 
	 * @param day
	 * @return
	 */
	public static Predicate<TransactionVO> onDaysBasis(String day) {
		return p -> p.getTransactionDateTime().getDayOfWeek() == DayOfWeek.valueOf(day.toUpperCase());
	}

	/**
	 * Hour Basis
	 * 
	 * @param hours
	 * @return
	 */
	public static Predicate<TransactionVO> onHourBasis(int hours) {
		return p -> p.getTransactionDateTime().getHour() == hours;
	}

}
