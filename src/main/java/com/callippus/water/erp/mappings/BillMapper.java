package com.callippus.water.erp.mappings;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.domain.CustDetails;


@Mapper
public interface BillMapper {
	BillMapper INSTANCE = Mappers.getMapper( BillMapper.class );
    
    @Mappings({
        @Mapping(target = "id", constant = "0"),
        @Mapping(source = "cust_details.can", target = "can"),
        @Mapping(source = "cust_details.metReaderCode", target = "metReaderCode"),
        @Mapping(source = "cust_details.mobileNo", target = "mobileNo"),
        @Mapping(source = "bill_details.lat", target = "lat"),
        @Mapping(source = "bill_details.longi", target = "longi")
    })
    BillFullDetails bdToBfd(BillDetails bill_details, CustDetails cust_details);
    
    
}
