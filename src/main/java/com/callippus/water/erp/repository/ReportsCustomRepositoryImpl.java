package com.callippus.water.erp.repository;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ReportsCustomRepositoryImpl  implements
ReportsCustomRepository {
	private final Logger log = LoggerFactory
			.getLogger(ReportsCustomRepositoryImpl.class);

	@Inject
	EntityManager entityManager;

	@Override
	public JasperPrint generateReport(String reportPath, Map<String,Object> params) throws JRException {
		InputStream jasperStream = this.getClass().getResourceAsStream(reportPath);
		
		Session session = entityManager.unwrap(Session.class);
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();
		
		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				params, conn);
		
		return jasperPrint;		
	}
	
}
