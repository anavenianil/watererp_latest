package com.callippus.water.erp.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.callippus.water.erp.domain.Url2Role;
import com.callippus.water.erp.repository.Url2RoleRepository;

@Component
public class PermissionMap {

private final Logger log = LoggerFactory.getLogger(PermissionMap.class);
	
    @Inject
    private Url2RoleRepository url2RoleRepository;
    
	public Map<String, String> getPermissions() {
		
		List<Url2Role> list = url2RoleRepository.findAll();
				
		Map<String, String> hm = new HashMap<String, String>();
		for (Url2Role url2Role : list) {			
			hm.put(url2Role.getUrl().getUrlPattern(),url2Role.getAuthority().getName());
		}
		log.debug("Created HashMap of URL Patterns and Roles:" + hm.toString());
		
		return hm;

	}
	
}
