
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
    
    /*@Mappings({
        @Mapping(target = "id", constant = "0"),
        @Mapping(source = "cust_details.can", target = "can"),
        @Mapping(source = "cust_details.metReaderCode", target = "metReaderCode"),
        @Mapping(source = "cust_details.tariffCategoryMaster.id", target = "category"),
        @Mapping(source = "cust_details.mobileNo", target = "mobileNo"),
        @Mapping(source = "bill_details.lat", target = "lat"),
        @Mapping(source = "bill_details.longi", target = "longi"),
        @Mapping(source = "cust_details.meterFixDate", target = "meterFixDate"),
        @Mapping(source = "bill_details.metReadingDt", target = "metReadingDt")
    })*/
    CustDetails appTxnToCustDetails(ApplicationTxn applicationTxn);
    
    
}
