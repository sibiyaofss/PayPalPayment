package com.paypal.dal.dao;

/**
* Repository Interface
*
* @author  Antony Sibiya J
* @version 1.0
*/
import java.util.List;

import com.paypal.dal.dto.TransactionVO;
import com.paypal.exception.PaymentInterfaceException;

public interface PaymentDao {
	/**
	 * Method to get the transaction information from database
	 */
	List<TransactionVO> getAllTransactionFromDB() throws PaymentInterfaceException;

}
