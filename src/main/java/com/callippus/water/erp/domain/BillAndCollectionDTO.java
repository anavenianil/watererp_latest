package com.callippus.water.erp.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class BillAndCollectionDTO {
	ZonedDateTime txn_date;
	String txn_type;
	BigDecimal debit;
	BigDecimal credit;
	BigDecimal balance;
	
	
	public ZonedDateTime getTxn_date() {
		return txn_date;
	}


	public void setTxn_date(ZonedDateTime txn_date) {
		this.txn_date = txn_date;
	}


	public String getTxn_type() {
		return txn_type;
	}


	public void setTxn_type(String txn_type) {
		this.txn_type = txn_type;
	}


	public BigDecimal getDebit() {
		return debit;
	}


	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}


	public BigDecimal getCredit() {
		return credit;
	}


	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}


	@Override
	public String toString() {
		return "BillAndCollectionDTO [txn_date=" + txn_date + ", txn_type=" + txn_type + ", debit=" + debit
				+ ", credit=" + credit + ", balance=" + balance + "]";
	}

	
}