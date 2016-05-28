package com.callippus.water.erp.repository;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.callippus.water.erp.domain.BillAndCollectionDTO;
import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.domain.CreditDTO;
import com.callippus.water.erp.domain.DebitDTO;
import com.callippus.water.erp.web.rest.dto.RequestCountDTO;

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

	@Override
	public List<BillAndCollectionDTO> getBillCollection(String can){

		/*String sql = "SELECT bill_date,net_payable_amount,receipt_amt,receipt_dt,(b.net_payable_amount-c.receipt_amt)balance FROM bill_full_details b "
				+ " left join coll_details c on  b.can=c.can where b.can='"+can+"'";
		*/
		List<BillAndCollectionDTO> items= new ArrayList<BillAndCollectionDTO>();
		String sql = "select bill_date,coll_name,net_payable_amount,receipt_amt,receipt_dt, (case when b.arrears=0 then (b.net_payable_amount)+(b.arrears) else  (b.net_payable_amount+b.net_payable_amount-c.receipt_amt)end)Drbalance,((case when b.arrears=0  then (b.net_payable_amount)+(b.arrears) else  (b.net_payable_amount+b.net_payable_amount-c.receipt_amt)end)-receipt_amt)Crbalance FROM bill_full_details b join coll_details c  on  b.can=c.can join collection_type_master cm on cm.id=c.collection_type_master_id  where b.can='"+can+"'";
				
		
		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(sql);
		List<DebitDTO> ddto = new ArrayList<DebitDTO>();
		for (Map row : rows) {
			DebitDTO dr = new DebitDTO();
			BillAndCollectionDTO billAndCollectionDTO = new BillAndCollectionDTO();
			//rc.setId((Long) row.get("b.id"));
			dr.setBillDate((Date) row.get("bill_date"));
			dr.setNetPayableAmount((Float) row.get("net_payable_amount"));
			dr.setBalance((Double) row.get("Drbalance"));
			System.out.println("The value of billfulldetails is:"+dr );
			ddto.add(dr);
			billAndCollectionDTO.setDebitDTO(ddto);
			System.out.println(billAndCollectionDTO);
			items.add(billAndCollectionDTO);
			//return bac;
		}	
		
		String sql1 = "select bill_date,coll_name,net_payable_amount,receipt_amt,receipt_dt, (case when b.arrears=0 then (b.net_payable_amount)+(b.arrears) else  (b.net_payable_amount+b.net_payable_amount-c.receipt_amt)end)Drbalance,((case when b.arrears=0  then (b.net_payable_amount)+(b.arrears) else  (b.net_payable_amount+b.net_payable_amount-c.receipt_amt)end)-receipt_amt)Crbalance FROM bill_full_details b join coll_details c  on  b.can=c.can join collection_type_master cm on cm.id=c.collection_type_master_id  where b.can='"+can+"'";
			
		
		
		
		List<java.util.Map<String, Object>> rows1 = jdbcTemplate
				.queryForList(sql1);
		//List<BillAndCollectionDTO> bac = new ArrayList<BillAndCollectionDTO>();
		List<CreditDTO> dto = new ArrayList<CreditDTO>();
		for (Map row : rows) {
			CreditDTO cr = new CreditDTO();
			BillAndCollectionDTO billAndCollectionDTO = new BillAndCollectionDTO();
			//rc.setId((Long) row.get("b.id"));
			cr.setReceiptDate((Timestamp) row.get("receipt_dt"));
			cr.setReceiptAmount((Float) row.get("receipt_amt"));
			cr.setBalance((Double) row.get("Crbalance"));
			cr.setCollname((String) row.get("coll_name"));
			System.out.println("The value of billfulldetails is:"+cr );
			dto.add(cr);
			billAndCollectionDTO.setCreditDTO(dto);
			System.out.println(billAndCollectionDTO);
			items.add(billAndCollectionDTO);
			//return bac;		
		}
		
		
		return items;
		
	}
	
	
}