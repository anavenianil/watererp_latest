package com.callippus.water.erp.repository;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.callippus.water.erp.domain.BillDetails;

public class BillDetailsCustomRepositoryImpl extends
SimpleJpaRepository<BillDetails, Long>
implements BillDetailsCustomRepository{
		
	@Inject
	BillDetailsRepository billDetailsRepository;
	
	@Inject
	EntityManager entityManager;
	
	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;
	
	@Inject
	public BillDetailsCustomRepositoryImpl(EntityManager entityManager) {
		super(BillDetails.class, entityManager);
	}

	public BillDetailsCustomRepositoryImpl(
			Class<BillDetails> domainClass,
			EntityManager em) {
		super(domainClass, em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JasperPrint billDetailReports(Long id, Long testBatchId) throws JRException {
		InputStream jasperStream = this.getClass().getResourceAsStream(
				"/reports/ConsumersWater.jasper");
		Map<String, Object> params = new HashMap<>();
		
		Session session = entityManager.unwrap(Session.class);
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();
		
		params.put("requisitionId", id.toString());
		params.put("testBatchId", testBatchId.toString());
		params.put("SubReportPath", "reports/");
		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				params, conn);
		
		return jasperPrint;
		
	}

}