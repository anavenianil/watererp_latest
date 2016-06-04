package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.domain.BillRunDetails;
import com.callippus.water.erp.domain.BillRunMaster;
import com.callippus.water.erp.domain.CollDetails;
import com.callippus.water.erp.repository.BillRunDetailsRepository;
import com.callippus.water.erp.repository.BillRunMasterRepository;
import com.callippus.water.erp.repository.CollDetailsRepository;
import com.callippus.water.erp.repository.CollectionTypeMasterRepository;
import com.callippus.water.erp.repository.CustDetailsCustomRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.PaymentTypesRepository;
import com.callippus.water.erp.service.BillingService;

import org.jfree.util.Log;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BillRunMasterResource REST controller.
 *
 * @see BillRunMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BillRunMasterResourceIntTest {

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
			.withZone(ZoneId.of("Z"));

	private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.now();
	private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now();
	private static final String DEFAULT_DATE_STR = dateTimeFormatter.format(DEFAULT_DATE);
	private static final String DEFAULT_AREA = "AAAAA";
	private static final String UPDATED_AREA = "BBBBB";

	private static final Integer DEFAULT_SUCCESS = 1;
	private static final Integer UPDATED_SUCCESS = 2;

	private static final Integer DEFAULT_FAILED = 1;
	private static final Integer UPDATED_FAILED = 2;
	private static final String DEFAULT_STATUS = "AAAAA";
	private static final String UPDATED_STATUS = "BBBBB";

	// Expected Units, Water Cess, Rent, Lock charges after Run 1
	static final Map<String, Float[]> expectedCharges = Arrays
			.stream(new Object[][] { { "02020005", new Float[] { 3.0f, 2460.0f, 0.0f, 0.0f } },
					{ "08090001", new Float[] { 3.0f, 2460.0f, 2330.0f, 0.0f } },
					{ "04060001", new Float[] { 4.0f, 3202.76f, 4630.0f, 0.0f } },
					{ "05050001", new Float[] { 4.0f, 3246.32f, 2330.0f, 0.0f } },
					{ "04060002", new Float[] { 19.0f, 15213.1f, 4630.0f, 0.0f } },
					{ "04060003", new Float[] { 17.0f, 13796.8f, 2330.0f, 0.0f } },
					{ "04060004", new Float[] { 20.0f, 15880.5f, 6930.0f, 0.0f } },
					{ "05050002", new Float[] { 30.0f, 23946.3f, 4630.0f, 0.0f } } })
			.collect(Collectors.toMap(kv -> (String) kv[0], kv -> (Float[]) kv[1]));

	// Manual Payments
	static final Map<String, Float[]> manual_payments = Arrays
			.stream(new Object[][] { { "02020005", new Float[] { 5794.6f } }, { "08090001", new Float[] { 4800f } },
					{ "04060001", new Float[] { 4000f } }, { "05050001", new Float[] { 3000f, 1000f } },
					{ "04060002", new Float[] { 0.0f } }, { "04060003", new Float[] { 0.0f } },
					{ "04060004", new Float[] { 0.0f } }, { "05050002", new Float[] { 0.0f } } })
			.collect(Collectors.toMap(kv -> (String) kv[0], kv -> (Float[]) kv[1]));

	// Online Payments
	static final Map<String, Float[]> online_payments = Arrays
			.stream(new Object[][] { { "02020005", new Float[] { 0.0f } }, { "08090001", new Float[] { 0.0f } },
					{ "04060001", new Float[] { 0.0f } }, { "05050001", new Float[] { 1610.0f } },
					{ "04060002", new Float[] { 0.0f } }, { "04060003", new Float[] { 16200.0f } },
					{ "04060004", new Float[] { 22970.0f } }, { "05050002", new Float[] { 28820.0f } } })
			.collect(Collectors.toMap(kv -> (String) kv[0], kv -> (Float[]) kv[1]));

	// Expected Units, Water Cess, Rent, Lock charges after Run 2
	static final Map<String, Float[]> expectedCharges2 = Arrays
			.stream(new Object[][] { { "02020005", new Float[] { 3.0f, 2460.0f, 0.0f, 0.0f } },
					{ "08090001", new Float[] { 3.0f, 2460.0f, 2330.0f, 0.0f } },
					{ "04060001", new Float[] { 4.0f, 3202.76f, 4630.0f, 0.0f } },
					{ "05050001", new Float[] { 4.0f, 3246.32f, 2330.0f, 0.0f } },
					{ "04060002", new Float[] { 19.0f, 15213.1f, 4630.0f, 0.0f } },
					{ "04060003", new Float[] { 17.0f, 13796.8f, 2330.0f, 0.0f } },
					{ "04060004", new Float[] { 20.0f, 15880.5f, 6930.0f, 0.0f } },
					{ "05050002", new Float[] { 30.0f, 23946.3f, 4630.0f, 0.0f } } })
			.collect(Collectors.toMap(kv -> (String) kv[0], kv -> (Float[]) kv[1]));
	@Inject
	private BillRunMasterRepository billRunMasterRepository;

	@Inject
	private BillRunDetailsRepository billRunDetailsRepository;

	@Inject
	private BillingService billingService;

	@Inject
	private MappingJackson2HttpMessageConverter jacksonMessageConverter;

	@Inject
	private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

	private MockMvc restBillRunMasterMockMvc;

	private BillRunMaster billRunMaster;

	@Inject
	private CollDetailsRepository collDetailsRepository;

	@Inject
	private CollectionTypeMasterRepository collectionTypeMasterRepository;

	@Inject
	private CustDetailsRepository custDetailsRepository;

	@Inject
	private CustDetailsCustomRepository custDetailsCustomRepository;

	@Inject
	private PaymentTypesRepository paymentTypesRepository;

	private MockMvc restCollDetailsMockMvc;

	private CollDetails collDetails;

	@PostConstruct
	public void setup() {
		MockitoAnnotations.initMocks(this);
		BillRunMasterResource billRunMasterResource = new BillRunMasterResource();
		ReflectionTestUtils.setField(billRunMasterResource, "billRunMasterRepository", billRunMasterRepository);
		ReflectionTestUtils.setField(billRunMasterResource, "billingService", billingService);
		this.restBillRunMasterMockMvc = MockMvcBuilders.standaloneSetup(billRunMasterResource)
				.setCustomArgumentResolvers(pageableArgumentResolver).setMessageConverters(jacksonMessageConverter)
				.build();
	}

	@Before
	public void initTest() {
		billRunMaster = new BillRunMaster();
		billRunMaster.setDate(DEFAULT_DATE);
		billRunMaster.setArea(DEFAULT_AREA);
		billRunMaster.setSuccess(DEFAULT_SUCCESS);
		billRunMaster.setFailed(DEFAULT_FAILED);
		billRunMaster.setStatus(DEFAULT_STATUS);
	}

	@Test
	@Transactional
	public void createBillRunMaster() throws Exception {

		// Create the BillRunMaster

		billRunMaster.setArea(null);

		try {
			custDetailsCustomRepository.loadTestData("/scripts/initData.sql");
			custDetailsCustomRepository.loadTestData("/scripts/run1.sql");

			MvcResult result = restBillRunMasterMockMvc
					.perform(post("/api/billRunMasters").contentType(TestUtil.APPLICATION_JSON_UTF8)
							.content(TestUtil.convertObjectToJsonBytes(billRunMaster)))
					.andExpect(status().isOk()).andReturn();

			JSONObject content = new JSONObject(result.getResponse().getContentAsString());

			Long id = content.getLong("id");

			List<BillRunDetails> brdList = billRunDetailsRepository.findByBillRunId(id);

			for (BillRunDetails brd : brdList) {
				BillFullDetails bfd = brd.getBillFullDetails();
				Assert.assertEquals(expectedCharges.get(bfd.getCan())[0].floatValue(), bfd.getUnits().floatValue(),
						0.1f);
				Assert.assertEquals(expectedCharges.get(bfd.getCan())[1].floatValue(), bfd.getWaterCess().floatValue(),
						1.0f);
				Assert.assertEquals(expectedCharges.get(bfd.getCan())[2].floatValue(),
						bfd.getServiceCharge().floatValue() + bfd.getMeterServiceCharge().floatValue(), 1.0f);

				CollDetailsResourceIntTest ct = new CollDetailsResourceIntTest();
				CollDetailsResource collDetailsResource = new CollDetailsResource();
				ReflectionTestUtils.setField(collDetailsResource, "collDetailsRepository", collDetailsRepository);
				ReflectionTestUtils.setField(collDetailsResource, "custDetailsRepository", custDetailsRepository);
				ReflectionTestUtils.setField(ct, "collectionTypeMasterRepository", collectionTypeMasterRepository);
				ReflectionTestUtils.setField(ct, "paymentTypesRepository", paymentTypesRepository);
				restCollDetailsMockMvc = MockMvcBuilders.standaloneSetup(collDetailsResource)
						.setCustomArgumentResolvers(pageableArgumentResolver)
						.setMessageConverters(jacksonMessageConverter).build();

				for (int i = 0; i < manual_payments.get(bfd.getCan()).length
						&& manual_payments.get(bfd.getCan())[i] > 0.0f; i++) {
					ct.createPayment(restCollDetailsMockMvc, bfd.getCan(), manual_payments.get(bfd.getCan())[i],
							ZonedDateTime.now());
				}

			}
/*
			custDetailsCustomRepository.loadTestData("/run2.sql");

			result = restBillRunMasterMockMvc
					.perform(post("/api/billRunMasters").contentType(TestUtil.APPLICATION_JSON_UTF8)
							.content(TestUtil.convertObjectToJsonBytes(billRunMaster)))
					.andExpect(status().isOk()).andReturn();

			content = new JSONObject(result.getResponse().getContentAsString());

			id = content.getLong("id");

			brdList = billRunDetailsRepository.findByBillRunId(232L);

			for (BillRunDetails brd : brdList) {
				BillFullDetails bfd = brd.getBillFullDetails();
				Assert.assertEquals(expectedCharges2.get(bfd.getCan())[0].floatValue(), bfd.getUnits().floatValue(),
						0.1f);
				Assert.assertEquals(expectedCharges2.get(bfd.getCan())[1].floatValue(), bfd.getWaterCess().floatValue(),
						1.0f);
				Assert.assertEquals(expectedCharges2.get(bfd.getCan())[2].floatValue(),
						bfd.getServiceCharge().floatValue() + bfd.getMeterServiceCharge().floatValue(), 1.0f);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
