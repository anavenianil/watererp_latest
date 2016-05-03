package com.callippus.water.erp.service.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.callippus.water.erp.common.CPSUtils;
import com.callippus.water.erp.service.OnlinePaymentService;

public class XMLUtil {

	private static final Logger log = LoggerFactory
			.getLogger(XMLUtil.class);
	
	public static boolean validateXMLSchema(String xsdPath, String xml) throws IOException, SAXException{
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new StreamSource(OnlinePaymentService.class.getClass().getResourceAsStream(xsdPath)));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new StringReader(xml)));
		} catch (IOException e) {
			System.out.println("Exception: " + e.getMessage());
			throw e;
		} catch (SAXException e1) {
			System.out.println("SAX Exception: " + e1.getMessage());
			throw e1;
		}
		return true;
	}
	
}
