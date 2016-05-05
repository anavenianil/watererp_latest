package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.TariffMaster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Spring Data JPA repository for the TariffMaster entity.
 */
public class TariffMasterCustomRepositoryImpl extends
		SimpleJpaRepository<TariffMaster, Long> implements
		TariffMasterCustomRepository {
	private final Logger log = LoggerFactory
			.getLogger(TariffMasterCustomRepositoryImpl.class);

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

	public TariffMasterCustomRepositoryImpl(Class<TariffMaster> domainClass,
			EntityManager entityManager) {
		super(domainClass, entityManager);

		// This is the recommended method for accessing inherited class
		// dependencies. Menu_item
		this.entityManager = entityManager;
	}

	public List<java.util.Map<String, Object>> findTariffs(String can,
			LocalDate validFrom, LocalDate validTo, float avgKL,
			int unMeteredFlag, int newMeterFlag, int newMeterNoSvcFlag) {
		String sql = "SELECT tariff_type_master_id, avg(rate) rate,"+
				"       CASE "+
				"           WHEN tariff_type_master_id=1 THEN CASE "+
				"                                                 WHEN 1=? THEN sum(rate * months * min_unmetered_kl) "+
				"                                                 ELSE sum(rate * months * avg_kl) "+
				"                                             END "+
				"           ELSE CASE "+
				"                    WHEN 1=? THEN SUM(0) "+
				"                    ELSE sum(rate * months) "+
				"                END "+
				"       END amount "+
				"FROM "+
				"  (SELECT a.id tariff_master_id, "+
				"          tariff_name, "+
				"          valid_from, "+
				"          valid_to, "+
				"          c.tariff_category_master_id, "+
				"          case when Timestampdiff(month, valid_from, valid_to + INTERVAL 1 day) = 0 then 1 else Timestampdiff(month, valid_from, valid_to + INTERVAL 1 day) end months, "+
				"          t.id tariff_charges_id, "+
				"          t.tariff_desc, "+
				"          t.slab_min, "+
				"          t.slab_max, "+
				"          t.rate, "+
				"          t.min_unmetered_kl, "+
				"          CASE "+
				"              WHEN t.min_kl > ? and 0 = ? THEN t.min_kl "+
				"              ELSE ? "+
				"          END avg_kl, "+
				"              t.tariff_type_master_id "+
				"   FROM  "+
				"     (SELECT id, "+
				"               tariff_name, "+
				"               (CASE WHEN (valid_from) < ? THEN STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s') ELSE valid_from END) valid_from, "+
				"               (CASE WHEN (valid_to) > ? THEN STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s') ELSE valid_to END) valid_to, "+
				"               active, "+
				"               a.tariff_category_master_id "+
				"   FROM "+
				"     (SELECT * "+
				"      FROM tariff_master "+
				"      WHERE (valid_from) >=? "+
				"        AND (valid_to) <= ? "+
				"        AND ACTIVE = 1 "+
				"      UNION SELECT * "+
				"      FROM tariff_master "+
				"      WHERE ? BETWEEN valid_from AND valid_to "+
				"        AND ACTIVE = 1 "+
				"      UNION SELECT * "+
				"      FROM tariff_master "+
				"      WHERE ? BETWEEN valid_from AND valid_to "+
				"        AND ACTIVE = 1)a) a, "+
				"       tariff_charges t, "+
				"       cust_details c "+
				"   WHERE c.can = ? "+
				"     AND ? BETWEEN SLAB_MIN + 0.00001 AND SLAB_MAX "+
				"     AND c.tariff_category_master_id+0=a.tariff_category_master_id+0 "+
				"     AND a.id=t.tariff_master_id) a "+
				"GROUP BY tariff_type_master_id "+
				"ORDER BY (valid_from) ";

		Timestamp from = Timestamp.valueOf(validFrom.atStartOfDay());
		Timestamp to = Timestamp.valueOf(validTo.atStartOfDay());

		List<java.util.Map<String, Object>> rows = jdbcTemplate.queryForList(
				sql, new Object[] { unMeteredFlag, newMeterNoSvcFlag, avgKL, newMeterFlag, avgKL,
						from, from, to, to, from, to, from, to, can, avgKL });	
		
		log.debug("Output from billing query:" + rows);
		return rows;
	}
}
