package com.callippus.water.erp.service.util;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class DateUtil {

	/*
	 * public ZonedDateTime getDate(){
	 * 
	 * return ZonedDateTime.now(); }
	 */

	@RequestMapping(value = "/zonedDateTime", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Date getZonedDateTime() {
		SimpleDateFormat df = new SimpleDateFormat();
		Date d1=new Date();
		return d1;
	}

}
