package com.callippus.water.erp.domain;

import java.sql.Timestamp;

public class CreditDTO {
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	private Float receiptAmount;
	private Timestamp receiptDate;
	private Double balance;
	private String collname;
	
	
	
	public String getCollname() {
		return collname;
	}
	public void setCollname(String collname) {
		this.collname = collname;
	}
	public Float getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(Float receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public Timestamp getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(Timestamp receiptDate) {
		this.receiptDate = receiptDate;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
