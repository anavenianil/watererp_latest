package com.callippus.water.erp.domain;

import java.util.Date;

public class DebitDTO {

	private Long id;
	private Date billDate;
	private Float netPayableAmount;
	private Double balance;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public Float getNetPayableAmount() {
		return netPayableAmount;
	}
	public void setNetPayableAmount(Float netPayableAmount) {
		this.netPayableAmount = netPayableAmount;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
