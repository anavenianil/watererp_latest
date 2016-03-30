package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.TariffMaster;
import com.callippus.water.erp.service.BillingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Spring Data JPA repository for the TariffMaster entity.
 */
public class TariffMasterCustomRepositoryImpl extends
SimpleJpaRepository<TariffMaster, Long> implements
TariffMasterCustomRepository {
	private final Logger log = LoggerFactory.getLogger(TariffMasterCustomRepositoryImpl.class);
	
	@Inject
	TariffCategoryMasterRepository tcmr;
	
	@PersistenceContext
	EntityManager entityManager;

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	// There are two constructors to choose from, either can be used.
	@Inject
	public TariffMasterCustomRepositoryImpl(EntityManager entityManager) {
		super(TariffMaster.class, entityManager);
	}

	public TariffMasterCustomRepositoryImpl(
			Class<TariffMaster> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);

		// This is the recommended method for accessing inherited class
		// dependencies. Menu_item
		this.entityManager = entityManager;
	}

	
	public List<TariffMaster> findTariffs(ZonedDateTime validFrom, ZonedDateTime validTo){
		String sql = "select * from (select * from tariff_master where unix_timestamp(valid_to) >= ?) a where unix_timestamp(valid_from) <= ? order by unix_timestamp(valid_from)";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(sql, new Object[]{validFrom.toEpochSecond(),validTo.toEpochSecond()});

		List<TariffMaster> items = new ArrayList<TariffMaster>();
		
		int i=0;
		
		for (Map row : rows) {
			TariffMaster tariffMaster = new TariffMaster();
			tariffMaster.setId((Long) (row.get("id")));
			tariffMaster.setTariffCategoryMaster(tcmr.findOne((Long)row.get("tariff_category_master_id")));
			tariffMaster.setTariffName((String) row.get("tariff_name"));
			
			if(i != 0){
				Timestamp ts = (Timestamp)row.get("valid_from");
				ZonedDateTime valid_from = ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.of("UTC"));
				tariffMaster.setValidFrom(valid_from);
			}
			else
				tariffMaster.setValidFrom(validFrom);
								
			if(i < rows.size()-1){
				Timestamp ts2 = (Timestamp)row.get("valid_to");
				ZonedDateTime valid_to = ZonedDateTime.ofInstant(ts2.toInstant(), ZoneId.of("UTC"));			
				tariffMaster.setValidTo(valid_to);
			}
			else
				tariffMaster.setValidTo(validTo);
			
			tariffMaster.setActive((String) row.get("active"));
			items.add(tariffMaster);
			i++;
		}
		log.debug("Tariffs for the period:" + items.toString());
		return items;
	}
}
