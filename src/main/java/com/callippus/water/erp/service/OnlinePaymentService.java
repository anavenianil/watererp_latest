package com.callippus.water.erp.service;

import com.callippus.water.erp.common.CPSUtils;
import com.callippus.water.erp.domain.ConfigurationDetails;
import com.callippus.water.erp.domain.OnlinePaymentCallback;
import com.callippus.water.erp.domain.OnlinePaymentOrder;
import com.callippus.water.erp.domain.OnlinePaymentResponse;
import com.callippus.water.erp.domain.PGResponse;
import com.callippus.water.erp.domain.UnifiedPayment;
import com.callippus.water.erp.repository.ConfigurationDetailsRepository;
import com.callippus.water.erp.repository.MerchantMasterRepository;
import com.callippus.water.erp.repository.OnlinePaymentCallbackRepository;
import com.callippus.water.erp.repository.OnlinePaymentOrderRepository;
import com.callippus.water.erp.repository.OnlinePaymentResponseRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class OnlinePaymentService {

	private static final Logger log = LoggerFactory
			.getLogger(OnlinePaymentService.class);

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


	public static void main(String[] args) {
		String xml = "<OrderRequest> <Currency>TSh</Currency> <MerchantKey>5b56ca5b-a882-4224-b3e7-b558e93e6cb0</MerchantKey> <MerchantCode>Test001</MerchantCode> <MerchantName>testmerchant</MerchantName> <ServiceCode>TESTS001</ServiceCode> <PayBy>TIGOPESADIR</PayBy> <Amount>1</Amount> <UserDefinedField>abcd</UserDefinedField> <Parameters> <Parameter name=\"Email\">test@gmail.com</Parameter> <Parameter name=\"Phone\">1234567895</Parameter> </Parameters> </OrderRequest> ";

		String responseXml = "<?xml version='1.0' encoding='UTF8'?><UnifiedPayment><ResponseCode>100</ResponseCode><RedirectUrl>http://IP:PORT/maxcompp/directp aymentreceipt.xhtml?txnref=6125783711&amp;name=VCN Test&amp;paymentmode=TESTMOD</RedirectUrl></UnifiedPayment>";
		UnifiedPayment unifiedPaymentResponse = parseUnifiedPaymentResponse(responseXml);
		log.debug("This is the unifiedPaymentResponse:" + unifiedPaymentResponse);
		
		
		String merchantResponseXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> <OrderResponse>     <Currency>TSh</Currency>     <MerchantCode>Test001</MerchantCode>     <MerchantRefNumber>9312171800</MerchantRefNumber>     <PaymentMode>PAYMENTMODE</PaymentMode>     <ServiceCode>test-service</ServiceCode>     <Message>SUCCESS</Message>     <ResponseCode>200</ResponseCode>     <TotalAmountPaid>280.0</TotalAmountPaid> <UserDefinedField>123</UserDefinedField> </OrderResponse>";
		PGResponse pgResponse = parsePGResponse(merchantResponseXML);
		

		log.debug("This is the PGResponse:" + pgResponse);
	}

	public String processPGResponse(String pgResponseXML) {
		
		PGResponse pgResponse = parsePGResponse(pgResponseXML);
		
		OnlinePaymentCallback opc = new OnlinePaymentCallback();
		
		opc.setCurrency(pgResponse.getCurrency());
		opc.setPaymentMode(pgResponse.getPaymentMode());
		opc.setResponseCode(pgResponse.getResponseCode());
		opc.setServiceCode(pgResponse.getServiceCode());
		opc.setTotalAmountPaid(pgResponse.getTotalAmountPaid());
		opc.setUserDefinedField(pgResponse.getUserDefinedField());
		opc.setMerchantMaster(merchantMasterRepository.getByMerchantCode(pgResponse.getMerchantCode()) );
		opc.setOnlinePaymentOrder(onlinePaymentOrderRepository.findOne(pgResponse.getMerchantRefNumber()));
		
		opc = onlinePaymentCallbackRepository.save(opc);
		
		log.debug("This is the opc after save:" + opc);
		
		return "Successfully saved with id:" + opc.getId().toString();
	}	
	
	public String processOrder(OnlinePaymentOrder onlinePaymentOrder) {
		OnlinePaymentOrder result = onlinePaymentOrderRepository
				.save(onlinePaymentOrder);

		ConfigurationDetails cd = configurationDetailsRepository
				.findOneByName("ONLINE_PAYMENT_SERVER_URL");

		String xml = buildXML(onlinePaymentOrder);
		OnlinePaymentResponse onlinePaymentResponse = null;
		try {
			String response = postXML(xml, new URL(cd.getValue()));
			
			UnifiedPayment unifiedPaymentResponse = parseUnifiedPaymentResponse(response);
			
			onlinePaymentResponse = new OnlinePaymentResponse();
			onlinePaymentResponse.setRedirectUrl(unifiedPaymentResponse.getRedirectUrl());
			onlinePaymentResponse.setResponseCode(unifiedPaymentResponse.getResponseCode());
			onlinePaymentResponse.setResponseTime(ZonedDateTime.now());
			onlinePaymentResponse.setOnlinePaymentOrder(result);
			
			onlinePaymentResponseRepository.save(onlinePaymentResponse);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		return onlinePaymentResponse.getRedirectUrl();
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
				+ onlinePaymentOrder.getUserDefinedField()
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

	public String postXML(String xmlString, URL url) {

		HttpURLConnection connection = null;
		try {
			// Create connection
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/xml");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(xmlString.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(xmlString);
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {
			log.debug("Exception>>>>>>>>>>>>>>>>>>>"
					+ CPSUtils.stackTraceToString(e));

			e.printStackTrace();
			return e.getMessage();

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static UnifiedPayment parseUnifiedPaymentResponse(String xml) {
		UnifiedPayment unifiedPaymentResponse = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(UnifiedPayment.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			StringReader reader = new StringReader(xml);
			unifiedPaymentResponse = (UnifiedPayment) unmarshaller.unmarshal(reader);
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

			unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			StringReader reader = new StringReader(xml);
			pgResponse = (PGResponse) unmarshaller.unmarshal(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pgResponse;
		
	}

	
}
