package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.Receipt;
import com.callippus.water.erp.repository.ReceiptRepository;

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
 * Test class for the ReceiptResource REST controller.
 *
 * @see ReceiptResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ReceiptResourceIntTest {


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
    private ReceiptRepository receiptRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restReceiptMockMvc;

    private Receipt receipt;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReceiptResource receiptResource = new ReceiptResource();
        ReflectionTestUtils.setField(receiptResource, "receiptRepository", receiptRepository);
        this.restReceiptMockMvc = MockMvcBuilders.standaloneSetup(receiptResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        receipt = new Receipt();
        receipt.setAmount(DEFAULT_AMOUNT);
        receipt.setBankName(DEFAULT_BANK_NAME);
        receipt.setBranchName(DEFAULT_BRANCH_NAME);
        receipt.setCheckOrDdDate(DEFAULT_CHECK_OR_DD_DATE);
        receipt.setCheckOrDdNo(DEFAULT_CHECK_OR_DD_NO);
        receipt.setBillDate(DEFAULT_BILL_DATE);
    }

    @Test
    @Transactional
    public void createReceipt() throws Exception {
        int databaseSizeBeforeCreate = receiptRepository.findAll().size();

        // Create the Receipt

        restReceiptMockMvc.perform(post("/api/receipts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(receipt)))
                .andExpect(status().isCreated());

        // Validate the Receipt in the database
        List<Receipt> receipts = receiptRepository.findAll();
        assertThat(receipts).hasSize(databaseSizeBeforeCreate + 1);
        Receipt testReceipt = receipts.get(receipts.size() - 1);
        assertThat(testReceipt.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testReceipt.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testReceipt.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testReceipt.getCheckOrDdDate()).isEqualTo(DEFAULT_CHECK_OR_DD_DATE);
        assertThat(testReceipt.getCheckOrDdNo()).isEqualTo(DEFAULT_CHECK_OR_DD_NO);
        assertThat(testReceipt.getBillDate()).isEqualTo(DEFAULT_BILL_DATE);
    }

    @Test
    @Transactional
    public void getAllReceipts() throws Exception {
        // Initialize the database
        receiptRepository.saveAndFlush(receipt);

        // Get all the receipts
        restReceiptMockMvc.perform(get("/api/receipts?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(receipt.getId().intValue())))
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME.toString())))
                .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME.toString())))
                .andExpect(jsonPath("$.[*].checkOrDdDate").value(hasItem(DEFAULT_CHECK_OR_DD_DATE.toString())))
                .andExpect(jsonPath("$.[*].checkOrDdNo").value(hasItem(DEFAULT_CHECK_OR_DD_NO.toString())))
                .andExpect(jsonPath("$.[*].billDate").value(hasItem(DEFAULT_BILL_DATE.toString())));
    }

    @Test
    @Transactional
    public void getReceipt() throws Exception {
        // Initialize the database
        receiptRepository.saveAndFlush(receipt);

        // Get the receipt
        restReceiptMockMvc.perform(get("/api/receipts/{id}", receipt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(receipt.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.bankName").value(DEFAULT_BANK_NAME.toString()))
            .andExpect(jsonPath("$.branchName").value(DEFAULT_BRANCH_NAME.toString()))
            .andExpect(jsonPath("$.checkOrDdDate").value(DEFAULT_CHECK_OR_DD_DATE.toString()))
            .andExpect(jsonPath("$.checkOrDdNo").value(DEFAULT_CHECK_OR_DD_NO.toString()))
            .andExpect(jsonPath("$.billDate").value(DEFAULT_BILL_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReceipt() throws Exception {
        // Get the receipt
        restReceiptMockMvc.perform(get("/api/receipts/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReceipt() throws Exception {
        // Initialize the database
        receiptRepository.saveAndFlush(receipt);

		int databaseSizeBeforeUpdate = receiptRepository.findAll().size();

        // Update the receipt
        receipt.setAmount(UPDATED_AMOUNT);
        receipt.setBankName(UPDATED_BANK_NAME);
        receipt.setBranchName(UPDATED_BRANCH_NAME);
        receipt.setCheckOrDdDate(UPDATED_CHECK_OR_DD_DATE);
        receipt.setCheckOrDdNo(UPDATED_CHECK_OR_DD_NO);
        receipt.setBillDate(UPDATED_BILL_DATE);

        restReceiptMockMvc.perform(put("/api/receipts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(receipt)))
                .andExpect(status().isOk());

        // Validate the Receipt in the database
        List<Receipt> receipts = receiptRepository.findAll();
        assertThat(receipts).hasSize(databaseSizeBeforeUpdate);
        Receipt testReceipt = receipts.get(receipts.size() - 1);
        assertThat(testReceipt.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testReceipt.getBankName()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testReceipt.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testReceipt.getCheckOrDdDate()).isEqualTo(UPDATED_CHECK_OR_DD_DATE);
        assertThat(testReceipt.getCheckOrDdNo()).isEqualTo(UPDATED_CHECK_OR_DD_NO);
        assertThat(testReceipt.getBillDate()).isEqualTo(UPDATED_BILL_DATE);
    }

    @Test
    @Transactional
    public void deleteReceipt() throws Exception {
        // Initialize the database
        receiptRepository.saveAndFlush(receipt);

		int databaseSizeBeforeDelete = receiptRepository.findAll().size();

        // Get the receipt
        restReceiptMockMvc.perform(delete("/api/receipts/{id}", receipt.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Receipt> receipts = receiptRepository.findAll();
        assertThat(receipts).hasSize(databaseSizeBeforeDelete - 1);
    }
}
