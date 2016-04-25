package com.callippus.water.erp.security;

import java.util.ArrayList;
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
    
	public Map<String, List<String>> getPermissions() {
		
		List<Url2Role> list = url2RoleRepository.findAll();
				
		Map<String, List<String>> hm = new HashMap<String, List<String>>();
		for (Url2Role url2Role : list) {
			List<String> valueList = null;
			if(hm.get(url2Role.getUrl().getUrlPattern()) == null){
				valueList = new ArrayList<String>();
			}
			else
				valueList = hm.get(url2Role.getUrl().getUrlPattern());
			
			valueList.add(url2Role.getAuthority().getName());
			
			hm.put(url2Role.getUrl().getUrlPattern(),valueList);
		}
		log.debug("Created HashMap of URL Patterns and Roles:" + hm.toString());
		
		return hm;
	}	
}
