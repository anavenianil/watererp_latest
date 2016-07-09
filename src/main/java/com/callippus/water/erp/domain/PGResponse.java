package com.callippus.water.erp.domain;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OrderResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class PGResponse {
	@XmlElement(name = "MerchantCode")
	private String merchantCode;

	@XmlElement(name = "PaymentMode")
	private String paymentMode;

	@XmlElement(name = "MerchantRefNumber")
	private String merchantRefNumber;

	@XmlElement(name = "UserDefinedFields")
    private UserDefinedFields userDefinedFields;

	@XmlElement(name = "Message")
	private String message;

	@XmlElement(name = "ServiceCode")
	private String serviceCode;

	@XmlElement(name = "ResponseCode")
	private String responseCode;

	@XmlElement(name = "Currency")
	private String currency;

	@XmlElement(name = "TotalAmountPaid")
	private BigDecimal totalAmountPaid;
	
	@XmlElement(name = "ValidationNumber")
	private String validationNumber;	

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	public String getMerchantRefNumber() {
		return merchantRefNumber;
	}

	public void setMerchantRefNumber(String merchantRefNumber) {
		this.merchantRefNumber = merchantRefNumber;
	}

	public UserDefinedFields getUserDefinedFields() {
		return userDefinedFields;
	}

	public void setUserDefinedFields(UserDefinedFields userDefinedFields) {
		this.userDefinedFields = userDefinedFields;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getTotalAmountPaid() {
		return totalAmountPaid;
	}

	public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
		this.totalAmountPaid = totalAmountPaid;
	}

	
	public String getValidationNumber() {
		return validationNumber;
	}

	public void setValidationNumber(String validationNumber) {
		this.validationNumber = validationNumber;
	}

	@Override
	public String toString() {
		return "ClassPojo [MerchantCode = " + merchantCode + ", PaymentMode = "
				+ paymentMode + ", MerchantRefNumber = " + merchantRefNumber
				+ ", UserDefinedFields = " + userDefinedFields.toString() + ", Message = "
				+ message + ", ServiceCode = " + serviceCode
				+ ", ResponseCode = " + responseCode + ", Currency = "
				+ currency + ", TotalAmountPaid = " + totalAmountPaid + "]";
	}
}