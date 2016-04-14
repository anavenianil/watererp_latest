package com.callippus.water.erp.repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;

import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.ModuleMenuDTO;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CustDetailsCustomRepositoryImpl extends
		SimpleJpaRepository<CustDetails, Long> implements
		CustDetailsCustomRepository {
	private final Logger log = LoggerFactory
			.getLogger(CustDetailsCustomRepositoryImpl.class);

	@Inject
	EntityManager entityManager;

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	@Inject
	public CustDetailsCustomRepositoryImpl(EntityManager entityManager) {
		super(CustDetails.class, entityManager);
	}

	public CustDetailsCustomRepositoryImpl(
			Class<CustDetails> domainClass, EntityManager em) {
		super(domainClass, em);
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}

	/**
	 * List count of pending request
	 */
	public List<String> searchCAN(@Param("searchTerm") String searchTerm){

		String sql = "SELECT can from cust_details where can like ? limit 10";
		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(sql, new Object[] {"%" + searchTerm + "%"});

		List<String> items = new ArrayList<String>();

		for (Map row : rows) {
			items.add((String)row.get("can"));
		}

		return items;
	}

}
