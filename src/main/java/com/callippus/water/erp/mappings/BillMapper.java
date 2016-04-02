package com.callippus.water.erp.mappings;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.domain.BillFullDetails;

@Mapper
public interface BillMapper {
	BillMapper INSTANCE = Mappers.getMapper( BillMapper.class );
    
    BillFullDetails bdToBfd(BillDetails bill_details);
}
