package com.paypal.dal.dao;

import java.io.FileWriter;
import java.io.IOException;
/**
* Repository class to get the data
*
* @author  Antony Sibiya J
* @version 1.0
*/
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.dal.dto.TransactionVO;
import com.paypal.dal.mapper.TransactionMapper;
import com.paypal.exception.PaymentInterfaceException;
import com.paypal.resourceLoader.PaymentResLoader;

@Repository
public class PaymentDaoImpl implements PaymentDao {
	@Autowired
	public JdbcTemplate jdbcTemplateObject;

	@Autowired
	ObjectMapper customJson;
	static final Logger log = LogManager.getLogger(PaymentDaoImpl.class);

	@Autowired
	public PaymentResLoader paymentResLoader;

	/**
	 * Method to get the transaction information from database
	 */
	@Override
	public List<TransactionVO> getAllTransactionFromDB() throws PaymentInterfaceException {
		List<TransactionVO> transactionVOs = jdbcTemplateObject.query(paymentResLoader.lookUpSQL("getAllTransactions"),
				new TransactionMapper(customJson));

		try (FileWriter file = new FileWriter("transactions.json")) {

			file.write(customJson.writeValueAsString(transactionVOs));
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return transactionVOs;
	}

}
