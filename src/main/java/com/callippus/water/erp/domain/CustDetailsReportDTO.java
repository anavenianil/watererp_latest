package com.callippus.water.erp.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class CustDetailsReportDTO {
	
	private Long dmaId;
	private Long totalConnection;
	private Long totalInOneDma;
	private String dmaName;
	private List<CustDetails> custDetails;
	private Set<TariffCategoryMaster> tariffCategoryMasters;
	private Map<String,Integer> tariffsCounts;
	
	public Long getDmaId() {
		return dmaId;
	}

	public void setDmaId(Long dmaId) {
		this.dmaId = dmaId;
	}

	public Long getTotalConnection() {
		return totalConnection;
	}

	public void setTotalConnection(Long totalConnection) {
		this.totalConnection = totalConnection;
	}

	public Long getTotalInOneDma() {
		return totalInOneDma;
	}

	public void setTotalInOneDma(Long totalInOneDma) {
		this.totalInOneDma = totalInOneDma;
	}

	public String getDmaName() {
		return dmaName;
	}

	public void setDmaName(String dmaName) {
		this.dmaName = dmaName;
	}

	public List<CustDetails> getCustDetails() {
		return custDetails;
	}

	public void setCustDetails(List<CustDetails> custDetails) {
		this.custDetails = custDetails;
	}

	public Set<TariffCategoryMaster> getTariffCategoryMasters() {
		return tariffCategoryMasters;
	}

	public void setTariffCategoryMasters(
			Set<TariffCategoryMaster> tariffCategoryMasters) {
		this.tariffCategoryMasters = tariffCategoryMasters;
	}

	public Map<String, Integer> getTariffsCounts() {
		return tariffsCounts;
	}

	public void setTariffsCounts(Map<String, Integer> tariffsCounts) {
		this.tariffsCounts = tariffsCounts;
	}
	
	
}
