package com.paypal.dal.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public class TransactionVO  implements Serializable{
	private static final long serialVersionUID = 1L;
	private BigDecimal tnxAmount = null;
	private String beneficiaryAccount = null;
	private String beneficiaryName = null;
	private String beneficiaryId;
	private String currency = null;
	private String id = null;
	private String paymentMethod = null;
	private LocalDateTime transactionDateTime = null;
	private String invoiceNumber;
	private String paymentMode;
	private String applicantId;
	private String applicantName;
	private String applicantAccount;
	private Address applicantAddress;
	private TRANSACTION_TYPE transaction_TYPE;
	public BigDecimal getTnxAmount() {
		return tnxAmount;
	}
	public void setTnxAmount(BigDecimal tnxAmount) {
		this.tnxAmount = tnxAmount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public LocalDateTime getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(LocalDateTime transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public Address getApplicantAddress() {
		return applicantAddress;
	}
	public void setApplicantAddress(Address applicantAddress) {
		this.applicantAddress = applicantAddress;
	}
	public String getApplicantAccount() {
		return applicantAccount;
	}
	public void setApplicantAccount(String applicantAccount) {
		this.applicantAccount = applicantAccount;
	}
	public TRANSACTION_TYPE getTransaction_TYPE() {
		return transaction_TYPE;
	}
	public void setTransaction_TYPE(TRANSACTION_TYPE transaction_TYPE) {
		this.transaction_TYPE = transaction_TYPE;
	}
	public String getBeneficiaryAccount() {
		return beneficiaryAccount;
	}
	public void setBeneficiaryAccount(String beneficiaryAccount) {
		this.beneficiaryAccount = beneficiaryAccount;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	
	
	
	

}
