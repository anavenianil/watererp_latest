package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.BillOfMaterial;
import com.callippus.water.erp.repository.BillOfMaterialRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the BillOfMaterialResource REST controller.
 *
 * @see BillOfMaterialResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BillOfMaterialResourceIntTest {


    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;
    private static final String DEFAULT_BANK_NAME = "AAAAA";
    private static final String UPDATED_BANK_NAME = "BBBBB";
    private static final String DEFAULT_BRANCH_NAME = "AAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBB";

    private static final LocalDate DEFAULT_CHECK_OR_DD_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHECK_OR_DD_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_CHECK_OR_DD_NO = "AAAAA";
    private static final String UPDATED_CHECK_OR_DD_NO = "BBBBB";

    private static final LocalDate DEFAULT_BILL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BILL_DATE = LocalDate.now(ZoneId.systemDefault());

    @Inject
    private BillOfMaterialRepository billOfMaterialRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBillOfMaterialMockMvc;

    private BillOfMaterial billOfMaterial;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BillOfMaterialResource billOfMaterialResource = new BillOfMaterialResource();
        ReflectionTestUtils.setField(billOfMaterialResource, "billOfMaterialRepository", billOfMaterialRepository);
        this.restBillOfMaterialMockMvc = MockMvcBuilders.standaloneSetup(billOfMaterialResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        billOfMaterial = new BillOfMaterial();
        billOfMaterial.setAmount(DEFAULT_AMOUNT);
        billOfMaterial.setBankName(DEFAULT_BANK_NAME);
        billOfMaterial.setBranchName(DEFAULT_BRANCH_NAME);
        billOfMaterial.setCheckOrDdDate(DEFAULT_CHECK_OR_DD_DATE);
        billOfMaterial.setCheckOrDdNo(DEFAULT_CHECK_OR_DD_NO);
        billOfMaterial.setBillDate(DEFAULT_BILL_DATE);
    }

    @Test
    @Transactional
    public void createBillOfMaterial() throws Exception {
        int databaseSizeBeforeCreate = billOfMaterialRepository.findAll().size();

        // Create the BillOfMaterial

        restBillOfMaterialMockMvc.perform(post("/api/billOfMaterials")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billOfMaterial)))
                .andExpect(status().isCreated());

        // Validate the BillOfMaterial in the database
        List<BillOfMaterial> billOfMaterials = billOfMaterialRepository.findAll();
        assertThat(billOfMaterials).hasSize(databaseSizeBeforeCreate + 1);
        BillOfMaterial testBillOfMaterial = billOfMaterials.get(billOfMaterials.size() - 1);
        assertThat(testBillOfMaterial.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testBillOfMaterial.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testBillOfMaterial.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testBillOfMaterial.getCheckOrDdDate()).isEqualTo(DEFAULT_CHECK_OR_DD_DATE);
        assertThat(testBillOfMaterial.getCheckOrDdNo()).isEqualTo(DEFAULT_CHECK_OR_DD_NO);
        assertThat(testBillOfMaterial.getBillDate()).isEqualTo(DEFAULT_BILL_DATE);
    }

    @Test
    @Transactional
    public void getAllBillOfMaterials() throws Exception {
        // Initialize the database
        billOfMaterialRepository.saveAndFlush(billOfMaterial);

        // Get all the billOfMaterials
        restBillOfMaterialMockMvc.perform(get("/api/billOfMaterials?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(billOfMaterial.getId().intValue())))
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME.toString())))
                .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME.toString())))
                .andExpect(jsonPath("$.[*].checkOrDdDate").value(hasItem(DEFAULT_CHECK_OR_DD_DATE.toString())))
                .andExpect(jsonPath("$.[*].checkOrDdNo").value(hasItem(DEFAULT_CHECK_OR_DD_NO.toString())))
                .andExpect(jsonPath("$.[*].billDate").value(hasItem(DEFAULT_BILL_DATE.toString())));
    }

    @Test
    @Transactional
    public void getBillOfMaterial() throws Exception {
        // Initialize the database
        billOfMaterialRepository.saveAndFlush(billOfMaterial);

        // Get the billOfMaterial
        restBillOfMaterialMockMvc.perform(get("/api/billOfMaterials/{id}", billOfMaterial.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(billOfMaterial.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.bankName").value(DEFAULT_BANK_NAME.toString()))
            .andExpect(jsonPath("$.branchName").value(DEFAULT_BRANCH_NAME.toString()))
            .andExpect(jsonPath("$.checkOrDdDate").value(DEFAULT_CHECK_OR_DD_DATE.toString()))
            .andExpect(jsonPath("$.checkOrDdNo").value(DEFAULT_CHECK_OR_DD_NO.toString()))
            .andExpect(jsonPath("$.billDate").value(DEFAULT_BILL_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBillOfMaterial() throws Exception {
        // Get the billOfMaterial
        restBillOfMaterialMockMvc.perform(get("/api/billOfMaterials/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillOfMaterial() throws Exception {
        // Initialize the database
        billOfMaterialRepository.saveAndFlush(billOfMaterial);

		int databaseSizeBeforeUpdate = billOfMaterialRepository.findAll().size();

        // Update the billOfMaterial
        billOfMaterial.setAmount(UPDATED_AMOUNT);
        billOfMaterial.setBankName(UPDATED_BANK_NAME);
        billOfMaterial.setBranchName(UPDATED_BRANCH_NAME);
        billOfMaterial.setCheckOrDdDate(UPDATED_CHECK_OR_DD_DATE);
        billOfMaterial.setCheckOrDdNo(UPDATED_CHECK_OR_DD_NO);
        billOfMaterial.setBillDate(UPDATED_BILL_DATE);

        restBillOfMaterialMockMvc.perform(put("/api/billOfMaterials")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billOfMaterial)))
                .andExpect(status().isOk());

        // Validate the BillOfMaterial in the database
        List<BillOfMaterial> billOfMaterials = billOfMaterialRepository.findAll();
        assertThat(billOfMaterials).hasSize(databaseSizeBeforeUpdate);
        BillOfMaterial testBillOfMaterial = billOfMaterials.get(billOfMaterials.size() - 1);
        assertThat(testBillOfMaterial.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testBillOfMaterial.getBankName()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testBillOfMaterial.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testBillOfMaterial.getCheckOrDdDate()).isEqualTo(UPDATED_CHECK_OR_DD_DATE);
        assertThat(testBillOfMaterial.getCheckOrDdNo()).isEqualTo(UPDATED_CHECK_OR_DD_NO);
        assertThat(testBillOfMaterial.getBillDate()).isEqualTo(UPDATED_BILL_DATE);
    }

    @Test
    @Transactional
    public void deleteBillOfMaterial() throws Exception {
        // Initialize the database
        billOfMaterialRepository.saveAndFlush(billOfMaterial);

		int databaseSizeBeforeDelete = billOfMaterialRepository.findAll().size();

        // Get the billOfMaterial
        restBillOfMaterialMockMvc.perform(delete("/api/billOfMaterials/{id}", billOfMaterial.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<BillOfMaterial> billOfMaterials = billOfMaterialRepository.findAll();
        assertThat(billOfMaterials).hasSize(databaseSizeBeforeDelete - 1);
    }
}
