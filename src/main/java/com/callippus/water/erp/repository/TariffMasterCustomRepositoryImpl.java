package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.TariffMaster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.LocalDate;
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

	
	public List<java.util.Map<String, Object>> findTariffs(LocalDate validFrom, LocalDate validTo, float avgKL, int unMeteredFlag, int newMeterFlag){
		String sql = "SELECT tariff_type_master_id,"
				+ "case when tariff_type_master_id=1 then "
				+ "case when 1=? then sum(rate * months * min_unmetered_kl) else sum(rate * months * avg_kl)  end " //Unmetered Flag
				+ "else CASE WHEN 1=? THEN SUM(0) ELSE sum(rate * months) END end " //New Meter Flag
				+ "amount FROM (SELECT a.id tariff_master_id, "
				+ "tariff_name, "
				+ "valid_from, "
				+ "valid_to, "
				+ "tariff_category_master_id, "
				+ "TIMESTAMPDIFF(MONTH,valid_from,valid_to + interval 1 day) months, "
				+ "t.id tariff_charges_id, "
				+ "t.tariff_desc, "
				+ "t.slab_min, "
				+ "t.slab_max, "
				+ "t.rate , "
				+ "t.min_unmetered_kl , "
				+ "case when t.min_kl > ? then t.min_kl else ? end avg_kl,"
				+ "t.tariff_type_master_id from "
				+ "(SELECT id,tariff_name,(CASE WHEN (valid_from) < ? THEN STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s') ELSE valid_from END) valid_from,(CASE WHEN (valid_to) > ? "
				+ "THEN STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s') ELSE valid_to END) valid_to, active,tariff_category_master_id "
				+ "FROM "
				+ "(SELECT * "
				+ "FROM tariff_master "
				+ "WHERE (valid_to) >=?) a "
				+ "WHERE active=1 "
				+ "AND (valid_from) <= ?) a, "
				+ "tariff_charges t  WHERE a.id=t.tariff_master_id ) a "
				+ "group by tariff_type_master_id  "
				+ "ORDER BY (valid_from)";
/****	
==================================================================================		
MySQL Sample Query
==================================================================================		

		SELECT tariff_type_master_id, case when tariff_type_master_id=1 then sum(rate * months * avg_kl) else
       sum(rate * months) end amount
			FROM
			  (SELECT a.id,
			          tariff_name,
			          valid_from,
			          valid_to,
			          tariff_category_master_id,
			          t.rate,
			          case when t.min_kl > 10 then t.min_kl else 10 end avg_kl,
			          TIMESTAMPDIFF(MONTH,valid_from,valid_to) months,
			          t.tariff_type_master_id
			   FROM
			     (SELECT id,tariff_name,(CASE WHEN valid_from < date('2013-10-01') THEN str_to_date('2013-10-01 00:00:00', '%Y-%m-%d %H:%i:%s') ELSE valid_from END) valid_from,
			      FROM
			        (SELECT *
			         FROM tariff_master
			         WHERE unix_timestamp(valid_to) >= unix_timestamp(date('2013-10-01'))) a
			      WHERE active=1
			        AND unix_timestamp(valid_from) <= unix_timestamp(date('2016-03-01') )) a,
			       tariff_charges t
			   WHERE a.id=t.tariff_master_id) a
			group by tariff_type_master_id
			ORDER BY unix_timestamp(valid_from)
		
==================================================================================		
		<html>
		<head>
		<title>Query select a.id, tariff_name,valid_from,valid_to,tariff_category_master_id,TIMESTAMPDIFF(MONTH,valid_from,valid_to) months,t.id,t.tariff_desc,t.slab_min,t.slab_max,t.rate from( select id,tariff_name,(case when valid_from < date('2013-10-01') then str_to_date('2013-10-01 00:00:00', '%Y-%m-%d %H:%i:%s') else valid_from end) 		 valid_from,(case when valid_to > date('2016-03-01') then str_to_date('2016-03-01 00:00:00', '%Y-%m-%d %H:%i:%s') else valid_to end) valid_to,      active,tariff_category_master_id 		 from (select * from tariff_master where unix_timestamp(valid_to) >= unix_timestamp(date('2013-10-01')))       a 		 where active=1 and unix_timestamp(valid_from) <= unix_timestamp(date('2016-03-01')      )) a, tariff_charges t      where a.id=t.tariff_master_id      order by unix_timestamp(valid_from), Thu Mar 31 18:38:22 2016
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		</head>
		<body><h1>Query select a.id, tariff_name,valid_from,valid_to,tariff_category_master_id,TIMESTAMPDIFF(MONTH,valid_from,valid_to) months,t.id,t.tariff_desc,t.slab_min,t.slab_max,t.rate from( select id,tariff_name,(case when valid_from < date('2013-10-01') then str_to_date('2013-10-01 00:00:00', '%Y-%m-%d %H:%i:%s') else valid_from end) 		 valid_from,(case when valid_to > date('2016-03-01') then str_to_date('2016-03-01 00:00:00', '%Y-%m-%d %H:%i:%s') else valid_to end) valid_to,      active,tariff_category_master_id 		 from (select * from tariff_master where unix_timestamp(valid_to) >= unix_timestamp(date('2013-10-01')))       a 		 where active=1 and unix_timestamp(valid_from) <= unix_timestamp(date('2016-03-01')      )) a, tariff_charges t      where a.id=t.tariff_master_id      order by unix_timestamp(valid_from), Thu Mar 31 18:38:22 2016
		</h1>
		<table border=1 cellspacing=1 cellpadding=0><tr>
		<th>id</th><th>tariff_name</th><th>valid_from</th><th>valid_to</th><th>tariff_category_master_id</th><th>months</th><th>id</th><th>tariff_desc</th><th>slab_min</th><th>slab_max</th><th>rate</th></tr>
		<tr>
		<td>1</td><td>Domestic - 2010 to 2015</td><td>2013-10-01 00:00:00</td><td>2015-12-31 00:00:00</td><td>1</td><td>26</td><td>1</td><td>Domestic - Usage</td><td>0</td><td>999999</td><td>780</td></tr>
		<tr>
		<td>1</td><td>Domestic - 2010 to 2015</td><td>2013-10-01 00:00:00</td><td>2015-12-31 00:00:00</td><td>1</td><td>26</td><td>2</td><td>Domestic - Fixed</td><td>0</td><td>999999</td><td>18000</td></tr>
		<tr>
		<td>1</td><td>Domestic - 2010 to 2015</td><td>2013-10-01 00:00:00</td><td>2015-12-31 00:00:00</td><td>1</td><td>26</td><td>3</td><td>Domestic - Service</td><td>0</td><td>999999</td><td>1800</td></tr>
		<tr>
		<td>2</td><td>Domestic - 2016 Jan to Mar</td><td>2016-01-01 00:00:00</td><td>2016-03-01 00:00:00</td><td>1</td><td>2</td><td>4</td><td>Domestic - Usage</td><td>0</td><td>999999</td><td>780</td></tr>
		<tr>
		<td>2</td><td>Domestic - 2016 Jan to Mar</td><td>2016-01-01 00:00:00</td><td>2016-03-01 00:00:00</td><td>1</td><td>2</td><td>5</td><td>Domestic - Fixed</td><td>0</td><td>999999</td><td>19000</td></tr>
		<tr>
		<td>2</td><td>Domestic - 2016 Jan to Mar</td><td>2016-01-01 00:00:00</td><td>2016-03-01 00:00:00</td><td>1</td><td>2</td><td>6</td><td>Domestic - Service</td><td>0</td><td>999999</td><td>1800</td></tr>
		</table>
		</body></html>
****/		
		
		Timestamp from = Timestamp.valueOf(validFrom.atStartOfDay());
		Timestamp to = Timestamp.valueOf(validTo.atStartOfDay());
		
		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList( sql, new Object[]{unMeteredFlag, newMeterFlag, avgKL,avgKL, from,from,
						to,to,from,to});

		log.debug("Output from billing query:" + rows);
		return rows;
	}
}
