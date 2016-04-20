package com.callippus.water.erp.repository;

import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface ReportsCustomRepository  {
	
	public JasperPrint generateReport(String reportPath, Map<String, Object> params) throws JRException;

}
