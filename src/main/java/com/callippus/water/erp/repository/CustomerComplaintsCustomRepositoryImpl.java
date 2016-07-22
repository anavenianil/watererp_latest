package com.callippus.water.erp.repository;

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

import com.callippus.water.erp.domain.CustomerComplaints;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CustomerComplaintsCustomRepositoryImpl extends
		SimpleJpaRepository<CustomerComplaints, Long> implements
		CustomerComplaintsCustomRepository {
	private final Logger log = LoggerFactory
			.getLogger(CustomerComplaintsCustomRepositoryImpl.class);

	@Inject
	EntityManager entityManager;

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	@Inject
	public CustomerComplaintsCustomRepositoryImpl(EntityManager entityManager) {
		super(CustomerComplaints.class, entityManager);
	}

	public CustomerComplaintsCustomRepositoryImpl(
			Class<CustomerComplaints> domainClass, EntityManager em) {
		super(domainClass, em);
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}

	/**
	 * List customer complaints
	 */
	public List<String> searchCustomerComplaint(@Param("searchTerm") String searchTerm) {
		String sql = "SELECT concat(can ,' - ',complaint_by,' - ',id) complaint from customer_complaints where can like ? or complaint_by like ? or id like ? limit 10";
		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(sql, new Object[] {"%" + searchTerm + "%","%" + searchTerm + "%","%" + searchTerm + "%"});

		List<String> items = new ArrayList<String>();

		for (Map row : rows) {
			items.add((String)row.get("complaint"));
		}

		return items;
	}
}
