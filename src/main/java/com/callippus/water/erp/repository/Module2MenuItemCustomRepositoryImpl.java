package com.callippus.water.erp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.callippus.water.erp.domain.Module2MenuItem;
import com.callippus.water.erp.domain.ModuleMenuDTO;

//import java.sql.PreparedStatement;

public class Module2MenuItemCustomRepositoryImpl extends
		SimpleJpaRepository<Module2MenuItem, Long> implements
		Module2MenuItemCustomRepository {

	@Inject
	Module2MenuItemRepository repository;

	@PersistenceContext
	EntityManager entityManager;

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	// There are two constructors to choose from, either can be used.
	@Inject
	public Module2MenuItemCustomRepositoryImpl(EntityManager entityManager) {
		super(Module2MenuItem.class, entityManager);
	}

	public Module2MenuItemCustomRepositoryImpl(
			Class<Module2MenuItem> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);

		// This is the recommended method for accessing inherited class
		// dependencies. Menu_item
		this.entityManager = entityManager;
	}


	@SuppressWarnings("unchecked")
	public List<ModuleMenuDTO> findAllByLoginUsingMapping(String user) {
		System.out.println("\n\n\n\n\n\n");
		String sql = "SELECT m.id, mo.id   module_id,me.id   menu_item_id,mo.name module_name,mo.server_url,me.name menu_name,me.path "
				+ " FROM   module2_menu_item m,module mo,menu_item me,menu_item2_url n,url2_role r,jhi_user_authority a,jhi_user u "
				+ " WHERE  m.module_id = mo.id AND m.menu_item_id = me.id AND m.menu_item_id = n.menu_item_id AND n.url_id = r.url_id "
				+ " AND r.authority_name = a.authority_name AND u.login = '"
				+ user
				+ "' AND u.id = a.user_id "
				+ " ORDER  BY mo.priority,m.priority";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(sql);

		List<ModuleMenuDTO> items = new ArrayList<ModuleMenuDTO>();

		for (Map row : rows) {
			ModuleMenuDTO moduleMenuDTO = new ModuleMenuDTO();
			moduleMenuDTO.setId((Long) (row.get("id")));
			moduleMenuDTO.setModule_id((Long) row.get("module_id"));
			moduleMenuDTO.setServer_url((String)row.get("server_url"));
			moduleMenuDTO.setMenu_item_id((Long) row.get("menu_item_id"));
			moduleMenuDTO.setModule_name((String) row.get("module_name"));
			moduleMenuDTO.setMenu_name((String) row.get("menu_name"));
			moduleMenuDTO.setPath((String) row.get("path"));
			items.add(moduleMenuDTO);
		}

		return items;
	}

}
