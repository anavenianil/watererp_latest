
package com.callippus.water.erp.mappings;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.CustDetails;


@Mapper
public interface CustDetailsMapper {
	CustDetailsMapper INSTANCE = Mappers.getMapper( CustDetailsMapper.class );
    

	@Mappings({
		@Mapping(target = "id", constant = "0"),
		@Mapping(target = "divCode", source = "street"),
		@Mapping(target = "secCode", source = "blockNo"),
		@Mapping(target = "secName", source= "bStreet"),
		@Mapping(target = "connDate", source= "connectionDate"),
		@Mapping(target = "consName", source= "firstName"),
		@Mapping(target = "houseNo", source= "plotNo"),
		@Mapping(target = "address", source= "ward"),
		@Mapping(target = "city", source= "dma"),
		@Mapping(target = "pinCode", source= "bPlotNo")
		
	})
    CustDetails appTxnToCustDetails(ApplicationTxn applicationTxn);
    
    
}
