package com.callippus.water.erp.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafResource {
	
    private final Logger log = LoggerFactory.getLogger(ThymeleafResource.class);
    	
    @RequestMapping(value ="/{pageName:[A-z]*}Main")
    String requisitionMain(@PathVariable("pageName") String pageName, HttpRequest request) {
    	log.debug("This is the page:" + pageName);
        return pageName;
    }	    
}
