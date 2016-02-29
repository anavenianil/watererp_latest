package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ManageCashPoint;
import com.callippus.water.erp.repository.ManageCashPointRepository;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the ManageCashPointResource REST controller.
 *
 * @see ManageCashPointResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ManageCashPointResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final ZonedDateTime DEFAULT_TODAY_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_TODAY_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_TODAY_DATE_STR = dateTimeFormatter.format(DEFAULT_TODAY_DATE);
    private static final String DEFAULT_PAYEE_NAME = "AAAAA";
    private static final String UPDATED_PAYEE_NAME = "BBBBB";

    private static final Float DEFAULT_TXN_AMOUNT = 1F;
    private static final Float UPDATED_TXN_AMOUNT = 2F;

    private static final Float DEFAULT_OPEN_BAL = 1F;
    private static final Float UPDATED_OPEN_BAL = 2F;

    private static final Float DEFAULT_AVAIL_BAL = 1F;
    private static final Float UPDATED_AVAIL_BAL = 2F;

    private static final Integer DEFAULT_TOTAL_RECEIPTS = 1;
    private static final Integer UPDATED_TOTAL_RECEIPTS = 2;
    private static final String DEFAULT_LOCATION_CODE = "AAAAA";
    private static final String UPDATED_LOCATION_CODE = "BBBBB";

    @Inject
    private ManageCashPointRepository manageCashPointRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restManageCashPointMockMvc;

    private ManageCashPoint manageCashPoint;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ManageCashPointResource manageCashPointResource = new ManageCashPointResource();
        ReflectionTestUtils.setField(manageCashPointResource, "manageCashPointRepository", manageCashPointRepository);
        this.restManageCashPointMockMvc = MockMvcBuilders.standaloneSetup(manageCashPointResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        manageCashPoint = new ManageCashPoint();
        manageCashPoint.setTodayDate(DEFAULT_TODAY_DATE);
        manageCashPoint.setPayeeName(DEFAULT_PAYEE_NAME);
        manageCashPoint.setTxnAmount(DEFAULT_TXN_AMOUNT);
        manageCashPoint.setOpenBal(DEFAULT_OPEN_BAL);
        manageCashPoint.setAvailBal(DEFAULT_AVAIL_BAL);
        manageCashPoint.setTotalReceipts(DEFAULT_TOTAL_RECEIPTS);
        manageCashPoint.setLocationCode(DEFAULT_LOCATION_CODE);
    }

    @Test
    @Transactional
    public void createManageCashPoint() throws Exception {
        int databaseSizeBeforeCreate = manageCashPointRepository.findAll().size();

        // Create the ManageCashPoint

        restManageCashPointMockMvc.perform(post("/api/manageCashPoints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(manageCashPoint)))
                .andExpect(status().isCreated());

        // Validate the ManageCashPoint in the database
        List<ManageCashPoint> manageCashPoints = manageCashPointRepository.findAll();
        assertThat(manageCashPoints).hasSize(databaseSizeBeforeCreate + 1);
        ManageCashPoint testManageCashPoint = manageCashPoints.get(manageCashPoints.size() - 1);
        assertThat(testManageCashPoint.getTodayDate()).isEqualTo(DEFAULT_TODAY_DATE);
        assertThat(testManageCashPoint.getPayeeName()).isEqualTo(DEFAULT_PAYEE_NAME);
        assertThat(testManageCashPoint.getTxnAmount()).isEqualTo(DEFAULT_TXN_AMOUNT);
        assertThat(testManageCashPoint.getOpenBal()).isEqualTo(DEFAULT_OPEN_BAL);
        assertThat(testManageCashPoint.getAvailBal()).isEqualTo(DEFAULT_AVAIL_BAL);
        assertThat(testManageCashPoint.getTotalReceipts()).isEqualTo(DEFAULT_TOTAL_RECEIPTS);
        assertThat(testManageCashPoint.getLocationCode()).isEqualTo(DEFAULT_LOCATION_CODE);
    }

    @Test
    @Transactional
    public void getAllManageCashPoints() throws Exception {
        // Initialize the database
        manageCashPointRepository.saveAndFlush(manageCashPoint);

        // Get all the manageCashPoints
        restManageCashPointMockMvc.perform(get("/api/manageCashPoints?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(manageCashPoint.getId().intValue())))
                .andExpect(jsonPath("$.[*].todayDate").value(hasItem(DEFAULT_TODAY_DATE_STR)))
                .andExpect(jsonPath("$.[*].payeeName").value(hasItem(DEFAULT_PAYEE_NAME.toString())))
                .andExpect(jsonPath("$.[*].txnAmount").value(hasItem(DEFAULT_TXN_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].openBal").value(hasItem(DEFAULT_OPEN_BAL.doubleValue())))
                .andExpect(jsonPath("$.[*].availBal").value(hasItem(DEFAULT_AVAIL_BAL.doubleValue())))
                .andExpect(jsonPath("$.[*].totalReceipts").value(hasItem(DEFAULT_TOTAL_RECEIPTS)))
                .andExpect(jsonPath("$.[*].locationCode").value(hasItem(DEFAULT_LOCATION_CODE.toString())));
    }

    @Test
    @Transactional
    public void getManageCashPoint() throws Exception {
        // Initialize the database
        manageCashPointRepository.saveAndFlush(manageCashPoint);

        // Get the manageCashPoint
        restManageCashPointMockMvc.perform(get("/api/manageCashPoints/{id}", manageCashPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(manageCashPoint.getId().intValue()))
            .andExpect(jsonPath("$.todayDate").value(DEFAULT_TODAY_DATE_STR))
            .andExpect(jsonPath("$.payeeName").value(DEFAULT_PAYEE_NAME.toString()))
            .andExpect(jsonPath("$.txnAmount").value(DEFAULT_TXN_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.openBal").value(DEFAULT_OPEN_BAL.doubleValue()))
            .andExpect(jsonPath("$.availBal").value(DEFAULT_AVAIL_BAL.doubleValue()))
            .andExpect(jsonPath("$.totalReceipts").value(DEFAULT_TOTAL_RECEIPTS))
            .andExpect(jsonPath("$.locationCode").value(DEFAULT_LOCATION_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingManageCashPoint() throws Exception {
        // Get the manageCashPoint
        restManageCashPointMockMvc.perform(get("/api/manageCashPoints/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManageCashPoint() throws Exception {
        // Initialize the database
        manageCashPointRepository.saveAndFlush(manageCashPoint);

		int databaseSizeBeforeUpdate = manageCashPointRepository.findAll().size();

        // Update the manageCashPoint
        manageCashPoint.setTodayDate(UPDATED_TODAY_DATE);
        manageCashPoint.setPayeeName(UPDATED_PAYEE_NAME);
        manageCashPoint.setTxnAmount(UPDATED_TXN_AMOUNT);
        manageCashPoint.setOpenBal(UPDATED_OPEN_BAL);
        manageCashPoint.setAvailBal(UPDATED_AVAIL_BAL);
        manageCashPoint.setTotalReceipts(UPDATED_TOTAL_RECEIPTS);
        manageCashPoint.setLocationCode(UPDATED_LOCATION_CODE);

        restManageCashPointMockMvc.perform(put("/api/manageCashPoints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(manageCashPoint)))
                .andExpect(status().isOk());

        // Validate the ManageCashPoint in the database
        List<ManageCashPoint> manageCashPoints = manageCashPointRepository.findAll();
        assertThat(manageCashPoints).hasSize(databaseSizeBeforeUpdate);
        ManageCashPoint testManageCashPoint = manageCashPoints.get(manageCashPoints.size() - 1);
        assertThat(testManageCashPoint.getTodayDate()).isEqualTo(UPDATED_TODAY_DATE);
        assertThat(testManageCashPoint.getPayeeName()).isEqualTo(UPDATED_PAYEE_NAME);
        assertThat(testManageCashPoint.getTxnAmount()).isEqualTo(UPDATED_TXN_AMOUNT);
        assertThat(testManageCashPoint.getOpenBal()).isEqualTo(UPDATED_OPEN_BAL);
        assertThat(testManageCashPoint.getAvailBal()).isEqualTo(UPDATED_AVAIL_BAL);
        assertThat(testManageCashPoint.getTotalReceipts()).isEqualTo(UPDATED_TOTAL_RECEIPTS);
        assertThat(testManageCashPoint.getLocationCode()).isEqualTo(UPDATED_LOCATION_CODE);
    }

    @Test
    @Transactional
    public void deleteManageCashPoint() throws Exception {
        // Initialize the database
        manageCashPointRepository.saveAndFlush(manageCashPoint);

		int databaseSizeBeforeDelete = manageCashPointRepository.findAll().size();

        // Get the manageCashPoint
        restManageCashPointMockMvc.perform(delete("/api/manageCashPoints/{id}", manageCashPoint.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ManageCashPoint> manageCashPoints = manageCashPointRepository.findAll();
        assertThat(manageCashPoints).hasSize(databaseSizeBeforeDelete - 1);
    }
}
