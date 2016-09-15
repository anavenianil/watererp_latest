package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.CustDetails;

public interface CustDetailsCustomRepository extends
JpaRepository<CustDetails, Long> {
	   
    public List<String> searchCAN(@Param("searchTerm") String searchTerm);
    public List<String> searchCANDetails(@Param("searchTerms") String searchTerms);
    
	public int loadTestData(String filePath) throws Exception;
	
}
