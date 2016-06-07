package com.callippus.water.erp.domain;

import java.util.List;

//import java.sql.Date;
//import java.sql.Timestamp;
//import java.time.LocalDate;

//import com.sun.jmx.snmp.Timestamp;
public class BillAndCollectionDTO {


	private  List<CreditDTO> creditDTO;
	private  List<DebitDTO> debitDTO;
	
	public List<CreditDTO> getCreditDTO() {
		return creditDTO;
	}
	public void setCreditDTO(List<CreditDTO> creditDTO) {
		this.creditDTO = creditDTO;
	}
	public List<DebitDTO> getDebitDTO() {
		return debitDTO;
	}
	public void setDebitDTO(List<DebitDTO> debitDTO) {
		this.debitDTO = debitDTO;
	}

	
	
}