package com.paypal.dal.mapper;
/**
* RowMapper to map the db value
*
* @author  Antony Sibiya J
* @version 1.0
*/
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.dal.dto.Address;
import com.paypal.dal.dto.TRANSACTION_TYPE;
import com.paypal.dal.dto.TransactionVO;
import com.paypal.dal.utils.PaymentConstants;
@Component
public class TransactionMapper implements RowMapper<TransactionVO> {

	ObjectMapper customJson;
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 

	public TransactionMapper(ObjectMapper customJson) {
		this.customJson=customJson;
	}

	@Override
	public TransactionVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TransactionVO transactionVO = new TransactionVO();

		if (hasColumn(rs, PaymentConstants.APPLICANTACCOUNT)) {
			transactionVO.setApplicantAccount(rs.getString(PaymentConstants.APPLICANTACCOUNT));
		}

		if (hasColumn(rs, PaymentConstants.APPLICANTADDRESS)) {
			try {
				transactionVO.setApplicantAddress(
						customJson.readValue(rs.getString(PaymentConstants.APPLICANTADDRESS), Address.class));
			} catch (JsonProcessingException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (hasColumn(rs, PaymentConstants.APPLICANTID)) {
			transactionVO.setApplicantId(rs.getString(PaymentConstants.APPLICANTID));
		}
		if (hasColumn(rs, PaymentConstants.APPLICANTNAME)) {
			transactionVO.setApplicantName(rs.getString(PaymentConstants.APPLICANTNAME));
		}
		if (hasColumn(rs, PaymentConstants.BENEFICIARYACCOUNT)) {
			transactionVO.setBeneficiaryAccount(rs.getString(PaymentConstants.BENEFICIARYACCOUNT));
		}
		if (hasColumn(rs, PaymentConstants.BENEFICIARYID)) {
			transactionVO.setBeneficiaryId(rs.getString(PaymentConstants.BENEFICIARYID));
		}
		if (hasColumn(rs, PaymentConstants.BENEFICIARYNAME)) {
			transactionVO.setBeneficiaryName(rs.getString(PaymentConstants.BENEFICIARYNAME));
		}
		if (hasColumn(rs, PaymentConstants.CURRENCY)) {
			transactionVO.setCurrency(rs.getString(PaymentConstants.CURRENCY));
		}
		if (hasColumn(rs, PaymentConstants.ID)) {
			transactionVO.setId(rs.getString(PaymentConstants.ID));
		}
		if (hasColumn(rs, PaymentConstants.INVOICENUMBER)) {
			transactionVO.setInvoiceNumber(rs.getString(PaymentConstants.INVOICENUMBER));
		}
		if (hasColumn(rs, PaymentConstants.PAYMENTMETHOD)) {
			transactionVO.setPaymentMethod(rs.getString(PaymentConstants.PAYMENTMETHOD));
		}
		if (hasColumn(rs, PaymentConstants.PAYMENTMODE)) {
			transactionVO.setPaymentMode(rs.getString(PaymentConstants.PAYMENTMODE));
		}
		if (hasColumn(rs, PaymentConstants.TNXAMOUNT)) {
			transactionVO.setTnxAmount(rs.getBigDecimal(PaymentConstants.TNXAMOUNT));
		}
		if (hasColumn(rs, PaymentConstants.TRANSACTION_TYPE)) {
			transactionVO.setTransaction_TYPE(TRANSACTION_TYPE.valueOf(rs.getString(PaymentConstants.TRANSACTION_TYPE)));
		}
		if (hasColumn(rs, PaymentConstants.TRANSACTIONDATETIME)) {
			transactionVO.setTransactionDateTime(LocalDateTime.parse(rs.getString(PaymentConstants.TRANSACTIONDATETIME), formatter));
		}
		return transactionVO;
	}

	public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		for (int x = 1; x <= columns; x++) {
			if (columnName.equals(rsmd.getColumnName(x))) {
				return true;
			}
		}
		return false;
	}

}
