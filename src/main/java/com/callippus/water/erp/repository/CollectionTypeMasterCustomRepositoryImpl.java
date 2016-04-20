package com.callippus.water.erp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.callippus.water.erp.domain.CollectionTypeMaster;

public class CollectionTypeMasterCustomRepositoryImpl  extends
SimpleJpaRepository<CollectionTypeMaster, Long> implements
CollectionTypeMasterCustomRepository {
	private final Logger log = LoggerFactory
			.getLogger(CollectionTypeMasterCustomRepositoryImpl.class);
	@Inject
	EntityManager entityManager;

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;
	
	@Inject
	public CollectionTypeMasterCustomRepositoryImpl(EntityManager entityManager) {
		super(CollectionTypeMaster.class, entityManager);
	}
	
	public CollectionTypeMasterCustomRepositoryImpl(
			Class<CollectionTypeMaster> domainClass, EntityManager em) {
		super(domainClass, em);
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}

	@Override
	public List<CollectionTypeMaster> searchEXP(String searchTerm) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT id,coll_name exp_type from collection_type_master where coll_name like ? and txn_type = ? limit 10";
		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(sql, new Object[] {"%" + searchTerm + "%","E"});
		CollectionTypeMaster collectionTypeMaster;
		List<CollectionTypeMaster> items = new ArrayList<CollectionTypeMaster>();

		for (Map row : rows) {
			collectionTypeMaster = new CollectionTypeMaster();
			collectionTypeMaster.setId((Long)row.get("id"));			
			collectionTypeMaster.setCollName((String)row.get("exp_type"));
			items.add(collectionTypeMaster);
		}
		return items;
	}
}
