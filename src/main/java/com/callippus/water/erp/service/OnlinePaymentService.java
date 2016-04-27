package com.callippus.water.erp.service;

import com.callippus.water.erp.common.CPSUtils;
import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.domain.BillRunDetails;
import com.callippus.water.erp.domain.BillRunMaster;
import com.callippus.water.erp.domain.ConfigurationDetails;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.OnlinePaymentOrder;
import com.callippus.water.erp.mappings.BillMapper;
import com.callippus.water.erp.repository.BillDetailsRepository;
import com.callippus.water.erp.repository.BillFullDetailsRepository;
import com.callippus.water.erp.repository.BillRunDetailsRepository;
import com.callippus.water.erp.repository.BillRunMasterRepository;
import com.callippus.water.erp.repository.ConfigurationDetailsRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.OnlinePaymentOrderRepository;
import com.callippus.water.erp.repository.TariffMasterCustomRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.*;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class OnlinePaymentService {

	private final Logger log = LoggerFactory.getLogger(OnlinePaymentService.class);

    @Inject
    private ConfigurationDetailsRepository configurationDetailsRepository;
    
    
@Inject
private OnlinePaymentOrderRepository onlinePaymentOrderRepository;


	enum Status {
		SUCCESS, FAILURE
	};

	public String processOrder(OnlinePaymentOrder onlinePaymentOrder)
	{

        OnlinePaymentOrder result = onlinePaymentOrderRepository.save(onlinePaymentOrder);
        
        ConfigurationDetails cd = configurationDetailsRepository.findOneByName("ONLINE_PAYMENT_SERVER_URL");
        		
		return "";
	}
}
