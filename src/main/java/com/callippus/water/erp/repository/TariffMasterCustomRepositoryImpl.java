package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.TariffMaster;

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
		System.out.println("\n\n\n\n\n\n");
		String sql = "select * from (select * from tariff_master where unix_timestamp(valid_to) >= ?) a where unix_timestamp(valid_from) <= ? order by unix_timestamp(valid_from)";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(sql, new Object[]{validFrom.toEpochSecond(),validTo.toEpochSecond()});

		List<TariffMaster> items = new ArrayList<TariffMaster>();

		for (Map row : rows) {
			TariffMaster tariffMaster = new TariffMaster();
			tariffMaster.setId((Long) (row.get("id")));
			tariffMaster.setTariffCategoryMaster(tcmr.findOne((Long)row.get("tariff_category_master_id")));
			tariffMaster.setTariffName((String) row.get("tariffName"));
			
			Timestamp ts = (Timestamp)row.get("valid_from");
			ZonedDateTime valid_from = ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.of("UTC"));					
			tariffMaster.setValidFrom(valid_from);
			
			Timestamp ts2 = (Timestamp)row.get("valid_to");
			ZonedDateTime valid_to = ZonedDateTime.ofInstant(ts2.toInstant(), ZoneId.of("UTC"));			
			tariffMaster.setValidTo(valid_to);
			
			tariffMaster.setActive((String) row.get("active"));
			items.add(tariffMaster);
		}

		return items;
	}
}
