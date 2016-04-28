package com.callippus.water.erp.service;

import com.callippus.water.erp.common.CPSUtils;
import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.domain.BillRunDetails;
import com.callippus.water.erp.domain.BillRunMaster;
import com.callippus.water.erp.domain.ConfigurationDetails;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.OnlinePaymentOrder;
import com.callippus.water.erp.domain.OnlinePaymentResponse;
import com.callippus.water.erp.mappings.BillMapper;
import com.callippus.water.erp.repository.BillDetailsRepository;
import com.callippus.water.erp.repository.BillFullDetailsRepository;
import com.callippus.water.erp.repository.BillRunDetailsRepository;
import com.callippus.water.erp.repository.BillRunMasterRepository;
import com.callippus.water.erp.repository.ConfigurationDetailsRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.OnlinePaymentOrderRepository;
import com.callippus.water.erp.repository.TariffMasterCustomRepository;
import com.ning.http.client.providers.netty.util.HttpUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import net.sf.saxon.functions.ParseXml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.*;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class OnlinePaymentService {

	private final Logger log = LoggerFactory
			.getLogger(OnlinePaymentService.class);

	@Inject
	private ConfigurationDetailsRepository configurationDetailsRepository;

	@Inject
	private OnlinePaymentOrderRepository onlinePaymentOrderRepository;

	enum Status {
		SUCCESS, FAILURE
	};

	public static void main(String[] args)
	{
		String xml = "<OrderRequest> <Currency>TSh</Currency> <MerchantKey>5b56ca5b-a882-4224-b3e7-b558e93e6cb0</MerchantKey> <MerchantCode>Test001</MerchantCode> <MerchantName>testmerchant</MerchantName> <ServiceCode>TESTS001</ServiceCode> <PayBy>TIGOPESADIR</PayBy> <Amount>1</Amount> <UserDefinedField>abcd</UserDefinedField> <Parameters> <Parameter name=\"Email\">test@gmail.com</Parameter> <Parameter name=\"Phone\">1234567895</Parameter> </Parameters> </OrderRequest> ";

		String responseXml = "<?xml version='1.0' encoding='UTF8'?><UnifiedPayment><ResponseCode>100</ResponseCode><RedirectUrl>http://IP:PORT/maxcompp/directp</RedirectUrl></UnifiedPayment>";
		parseXML(responseXml);
	}
	
	public String processOrder(OnlinePaymentOrder onlinePaymentOrder) {
		OnlinePaymentOrder result = onlinePaymentOrderRepository
				.save(onlinePaymentOrder);

		ConfigurationDetails cd = configurationDetailsRepository
				.findOneByName("ONLINE_PAYMENT_SERVER_URL");

		String xml = buildXML(onlinePaymentOrder);

		try {
			String response = postXML(xml, new URL(cd.getValue()));
		} catch (Exception e) {

		}

		return "";
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

	public static String parseXML(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Load the input XML document, parse it and return an instance of
			// the
			// Document class.
			Document document = builder.parse(new InputSource(new StringReader(xml)));

			OnlinePaymentResponse response = new OnlinePaymentResponse();
			NodeList nodeList = document.getElementsByTagName("ResponseCode");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;

					// Get the value of the ID attribute.
					String ID = node.getAttributes().getNamedItem("ResponseCode")
							.getNodeValue();

					// Get the value of all sub-elements.
					String firstname = elem.getElementsByTagName("RedirectUrl")
							.item(0).getChildNodes().item(0).getNodeValue();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			return "";
		}
	}

}
