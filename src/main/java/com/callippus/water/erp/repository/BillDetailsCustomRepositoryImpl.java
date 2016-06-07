package com.callippus.water.erp.repository;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.callippus.water.erp.domain.BillAndCollectionDTO;
import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.service.BillingService;
import com.callippus.water.erp.web.rest.dto.RequestCountDTO;

public class BillDetailsCustomRepositoryImpl extends
SimpleJpaRepository<BillDetails, Long>
implements BillDetailsCustomRepository{

	private final Logger log = LoggerFactory.getLogger(BillDetailsCustomRepositoryImpl.class);

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

	@Override
	public List<BillAndCollectionDTO> getBillCollection(String can){

		String sql ="select * from "+
		"( "+
		"select bill_date  txn_date, 'Bill', net_payable_amount debit, 0.0 credit, 0.0 balance  from bill_run_details brd, bill_full_details bfd "+
		"where brd.can=bfd.can and brd.status=4 and brd.can=? "+
		"union "+
		"select receipt_dt txn_date, 'Payment', 0.0 debit, receipt_amt credit, 0.0 balance from coll_details where can=? "+
		") a "+
		"order by txn_date ";
		
		
		List<java.util.Map<String, Object>> rows = jdbcTemplate.queryForList(sql,
				new Object[] {  can ,  can });
		
		List<BillAndCollectionDTO> items = new ArrayList<BillAndCollectionDTO>();
		for (Map row : rows) {
			BillAndCollectionDTO dr = new BillAndCollectionDTO();
			
			Instant instant = ((Timestamp) row.get("txn_date")).toInstant();
			dr.setTxn_date(ZonedDateTime.ofInstant(instant, ZoneId.systemDefault()));
			dr.setTxn_type((String) row.get("txn_type"));
			dr.setDebit((Double) row.get("debit"));
			dr.setCredit((Double) row.get("credit"));
			dr.setBalance((Double) row.get("balance"));
			
			log.debug("This is the dr:" + dr.toString());
			
			items.add(dr);
		}
		
		
		return items;
		
	}
	
	
}