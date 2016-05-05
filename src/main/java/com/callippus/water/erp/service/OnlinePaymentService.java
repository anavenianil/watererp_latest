package com.callippus.water.erp.service;

import com.callippus.water.erp.common.CPSUtils;
import com.callippus.water.erp.domain.ConfigurationDetails;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.MerchantMaster;
import com.callippus.water.erp.domain.OnlinePaymentCallback;
import com.callippus.water.erp.domain.OnlinePaymentOrder;
import com.callippus.water.erp.domain.OnlinePaymentResponse;
import com.callippus.water.erp.domain.PGResponse;
import com.callippus.water.erp.domain.UnifiedPayment;
import com.callippus.water.erp.repository.ConfigurationDetailsRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.MerchantMasterRepository;
import com.callippus.water.erp.repository.OnlinePaymentCallbackRepository;
import com.callippus.water.erp.repository.OnlinePaymentOrderRepository;
import com.callippus.water.erp.repository.OnlinePaymentResponseRepository;
import com.callippus.water.erp.service.util.URLUtil;
import com.callippus.water.erp.service.util.XMLUtil;
import com.callippus.water.erp.web.rest.util.HeaderUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class OnlinePaymentService {

	private static final Logger log = LoggerFactory
			.getLogger(OnlinePaymentService.class);

	@Inject
	private CustDetailsRepository custDetailsRepository;

	@Inject
	private ConfigurationDetailsRepository configurationDetailsRepository;

	@Inject
	private OnlinePaymentOrderRepository onlinePaymentOrderRepository;

	@Inject
	private OnlinePaymentResponseRepository onlinePaymentResponseRepository;

	@Inject
	private OnlinePaymentCallbackRepository onlinePaymentCallbackRepository;

	@Inject
	private MerchantMasterRepository merchantMasterRepository;

	public static void main(String[] args) throws Exception {
		String xml = "<OrderRequest> <Currency>TSh</Currency> <MerchantKey>5b56ca5b-a882-4224-b3e7-b558e93e6cb0</MerchantKey> <MerchantCode>Test001</MerchantCode> <MerchantName>testmerchant</MerchantName> <ServiceCode>TESTS001</ServiceCode> <PayBy>TIGOPESADIR</PayBy> <Amount>1</Amount> <UserDefinedField>abcd</UserDefinedField> <Parameters> <Parameter name=\"Email\">test@gmail.com</Parameter> <Parameter name=\"Phone\">1234567895</Parameter> </Parameters> </OrderRequest> ";

		String responseXml = "<?xml version='1.0' encoding='UTF8'?><UnifiedPayment><ResponseCode>100</ResponseCode><RedirectUrl>http://IP:PORT/maxcompp/directp aymentreceipt.xhtml?txnref=6125783711&amp;name=VCN Test&amp;paymentmode=TESTMOD</RedirectUrl></UnifiedPayment>";
						
		UnifiedPayment unifiedPaymentResponse = parseUnifiedPaymentResponse(responseXml);
		log.debug("This is the unifiedPaymentResponse:"
				+ unifiedPaymentResponse);

		String merchantResponseXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> <OrderResponse>    <Currency>TSh</Currency>    <MerchantCode>Test001</MerchantCode>    <MerchantRefNumber>6418940697</MerchantRefNumber>    <PaymentMode>TIGOPESADIR</PaymentMode>    <ServiceCode>TESTS001</ServiceCode>    <Message>PAID</Message>    <ResponseCode>100</ResponseCode>    <TotalAmountPaid>1099.0</TotalAmountPaid>    <ValidationNumber>7523158367</ValidationNumber>    <UserDefinedFields>        <invoice>            <UserDefinedField>12</UserDefinedField>        </invoice>    </UserDefinedFields></OrderResponse>";
		
		boolean isValid = XMLUtil.validateXMLSchema("/schema/UnifiedResponse.xsd", merchantResponseXML);
				
		PGResponse pgResponse = parsePGResponse(merchantResponseXML);

		log.debug("This is the PGResponse:" + pgResponse);
	}

	@Transactional(rollbackFor = Exception.class)
	public String processPGResponse(String pgResponseXML) throws Exception {

		boolean isValid = XMLUtil.validateXMLSchema("/schema/UnifiedResponse.xsd", pgResponseXML);

		if(!isValid)
			throw new Exception("Invalid response from Payment Gateway.");
		
		PGResponse pgResponse = parsePGResponse(pgResponseXML);

		if (pgResponse == null)
			throw new Exception("Unable to parse response");

		OnlinePaymentCallback opc = new OnlinePaymentCallback();

		opc.setCurrency(pgResponse.getCurrency());
		opc.setPaymentMode(pgResponse.getPaymentMode());
		opc.setResponseCode(pgResponse.getResponseCode());
		opc.setServiceCode(pgResponse.getServiceCode());
		opc.setTotalAmountPaid(pgResponse.getTotalAmountPaid());
		opc.setUserDefinedField(pgResponse.getUserDefinedFields().getInvoice().getUserDefinedField());
		opc.setMerchantTxnRef(pgResponse.getMerchantRefNumber());

		MerchantMaster mm = merchantMasterRepository
				.getByMerchantCode(pgResponse.getMerchantCode());

		if (mm == null)
			throw new Exception("Invalid Merchant Code");

		opc.setMerchantMaster(mm);

		OnlinePaymentResponse opr = onlinePaymentResponseRepository
				.findByMerchantTxnRef(pgResponse.getMerchantRefNumber());

		if (opr == null)
			throw new Exception("Invalid Merchant Ref Number");

		opc.setOnlinePaymentOrder(opr.getOnlinePaymentOrder());

		opc = onlinePaymentCallbackRepository.save(opc);

		log.debug("This is the opc after save:" + opc);

		return "Successfully saved with id:" + opc.getId().toString();
	}

	@Transactional(rollbackFor = Exception.class)
	public OnlinePaymentOrder processOrder(OnlinePaymentOrder onlinePaymentOrder)
			throws Exception {

		CustDetails cust = custDetailsRepository.findByCan(onlinePaymentOrder
				.getUserDefinedField());

		if (cust == null)
			throw new Exception("Invalid CAN. Customer does not exist.");

		ConfigurationDetails cd = configurationDetailsRepository
				.findOneByName("ONLINE_PAYMENT_SERVICE_CODE");

		onlinePaymentOrder.setServiceCode(cd.getValue());
		onlinePaymentOrder.setOrderTime(ZonedDateTime.now());

		cd = configurationDetailsRepository
				.findOneByName("ONLINE_PAYMENT_MERCHANT_CODE");
		MerchantMaster mm = merchantMasterRepository.getByMerchantCode(cd
				.getValue());

		if (mm == null)
			throw new Exception("Invalid Merchant Code");

		onlinePaymentOrder.setMerchantMaster(mm);

		OnlinePaymentOrder result = onlinePaymentOrderRepository
				.save(onlinePaymentOrder);

		cd = configurationDetailsRepository
				.findOneByName("ONLINE_PAYMENT_SERVER_URL");

		String xml = buildXML(onlinePaymentOrder);
		OnlinePaymentResponse onlinePaymentResponse = null;
		try {
			String response = URLUtil.postXML(xml, new URL(cd.getValue()));

			UnifiedPayment unifiedPaymentResponse = parseUnifiedPaymentResponse(response
					.replace("&", "&amp;"));

			if (unifiedPaymentResponse == null) {
				throw new Exception(
						"Error from Unified Payment server: Unable to parse response");
			}

			if (unifiedPaymentResponse.getResponseCode().equals("100")) {
				onlinePaymentResponse = new OnlinePaymentResponse();
				onlinePaymentResponse.setRedirectUrl(unifiedPaymentResponse
						.getRedirectUrl());
				
				Map<String, List<String>> params = URLUtil.splitQuery(new URL(unifiedPaymentResponse
						.getRedirectUrl()));
				
				List<String> txnRef = params.get("txnref");
				
				if(txnRef.size() != 1)
					throw new Exception("Error from Unified Payment server: Missing Merchant Txn Ref");
				
				onlinePaymentResponse.setMerchantTxnRef(txnRef.get(0));				
				onlinePaymentResponse.setResponseCode(unifiedPaymentResponse
						.getResponseCode());
				onlinePaymentResponse.setResponseTime(ZonedDateTime.now());
				onlinePaymentResponse.setOnlinePaymentOrder(result);

				onlinePaymentResponseRepository.save(onlinePaymentResponse);
			} else
				throw new Exception("Error from Unified Payment server:"
						+ unifiedPaymentResponse.getResponseCode());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public String buildXML(OnlinePaymentOrder onlinePaymentOrder) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<OrderRequest>"
				+ "   <Currency>"
				+ onlinePaymentOrder.getMerchantMaster().getCurrency()
				+ "</Currency>"
				+ "   <MerchantKey>"
				+ onlinePaymentOrder.getMerchantMaster().getMerchantKey()
				+ "</MerchantKey>"
				+ "   <MerchantCode>"
				+ onlinePaymentOrder.getMerchantMaster().getMerchantCode()
				+ "</MerchantCode>"
				+ "   <MerchantName>"
				+ onlinePaymentOrder.getMerchantMaster().getMerchantName()
				+ "</MerchantName>"
				+ "   <ServiceCode>"
				+ onlinePaymentOrder.getServiceCode()
				+ "</ServiceCode>"
				+ "   <PayBy>"
				+ onlinePaymentOrder.getPayBy()
				+ "</PayBy>"
				+ "   <Amount>"
				+ onlinePaymentOrder.getAmount()
				+ "</Amount>"
				+ "   <UserDefinedField>"
				+ onlinePaymentOrder.getId()
				+ "</UserDefinedField>"
				+ "   <Parameters>"
				+ "      <Parameter name=\"Email\">"
				+ onlinePaymentOrder.getEmail()
				+ "</Parameter>"
				+ "      <Parameter name=\"Phone\">"
				+ onlinePaymentOrder.getPhone()
				+ "</Parameter>"
				+ "   </Parameters>" + "</OrderRequest>";
	}



	public static UnifiedPayment parseUnifiedPaymentResponse(String xml) {
		UnifiedPayment unifiedPaymentResponse = null;
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(UnifiedPayment.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			unmarshaller
					.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			StringReader reader = new StringReader(xml);
			unifiedPaymentResponse = (UnifiedPayment) unmarshaller
					.unmarshal(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return unifiedPaymentResponse;

	}

	public static PGResponse parsePGResponse(String xml) {
		PGResponse pgResponse = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PGResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			unmarshaller
					.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			StringReader reader = new StringReader(xml);
			pgResponse = (PGResponse) unmarshaller.unmarshal(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pgResponse;

	}

}
