package com.callippus.water.erp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.callippus.water.erp.domain.CollectionTypeMaster;

public interface CollectionTypeMasterCustomRepository  extends
JpaRepository<CollectionTypeMaster, Long> {	
	public List<CollectionTypeMaster> searchEXP(@Param("searchTerm") String searchTerm);
}
