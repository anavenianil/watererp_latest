
package com.callippus.water.erp.mappings;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.CustMeterMapping;


@Mapper
public interface CustDetailsMapper {
	CustDetailsMapper INSTANCE = Mappers.getMapper( CustDetailsMapper.class );

	@Mappings({
		@Mapping(target = "id", constant = "0"),
		@Mapping(target = "divCode", source = "street"),
		@Mapping(target = "secCode", source = "blockNo"),
		@Mapping(target = "secName", source= "bStreet"),
		@Mapping(target = "connDate", source= "connectionDate"),
		@Mapping(target = "meterFixDate", source= "connectionDate"),
		@Mapping(target = "metReadingDt", source= "connectionDate"),
		@Mapping(target = "houseNo", source= "plotNo"),
		@Mapping(target = "address", source= "ward"),
		@Mapping(target = "prevReading", source= "meterReading"),
		@Mapping(target = "longi", constant= "0"),
		@Mapping(target = "lat", constant= "0"),
		@Mapping(target = "drFlag", constant= "0"),
		@Mapping(target = "noticeFlag", constant= "0"),
		@Mapping(target = "cpFlag", constant= "0"),
		@Mapping(target = "ccFlag", constant= "0"),
		@Mapping(target = "resUnits", constant= "1"),
		@Mapping(target = "hrsSurcharge", constant= "0.0"),
		@Mapping(target = "surcharge", constant= "0.0"),
		@Mapping(target = "otherCharges", constant= "0.0"),
		@Mapping(target = "prevBillType", constant= "M")
	})
    CustDetails appTxnToCustDetails(ApplicationTxn applicationTxn);
	
	
	/*@Mappings({
		@Mapping(target = "id", constant = "0"),
		@Mapping(target = "fromDate", source = "connectionDate")
		//@Mapping(target = "toDate", source = ""),
		//@Mapping(target = "custDetails", source= ""),
		//@Mapping(target = "meterDetails", source= "")
	})
    CustMeterMapping issueMeterToCustMeterMapp(ApplicationTxn applicationTxn);*/
	
    
    
}
