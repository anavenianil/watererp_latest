package com.callippus.water.erp.domain;

import javax.persistence.Entity;

public class ModuleMenuDTO {

	Long id;
	Long module_id;
	Long menu_item_id;
	String module_name;
	String menu_name;
	String path;
	String server_url;
	
	
	public String getServer_url() {
		return server_url;
	}
	public void setServer_url(String server_url) {
		this.server_url = server_url;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getModule_id() {
		return module_id;
	}
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}
	public Long getMenu_item_id() {
		return menu_item_id;
	}
	public void setMenu_item_id(Long menu_item_id) {
		this.menu_item_id = menu_item_id;
	}
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}	
}