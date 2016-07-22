package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ExpenseDetails;
import com.callippus.water.erp.repository.ExpenseDetailsRepository;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the ExpenseDetailsResource REST controller.
 *
 * @see ExpenseDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ExpenseDetailsResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_EXPENSE_NO = "AAAAA";
    private static final String UPDATED_EXPENSE_NO = "BBBBB";

    private static final BigDecimal DEFAULT_EXPENSE_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXPENSE_AMT = new BigDecimal(2);

    private static final ZonedDateTime DEFAULT_EXPENSE_DT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_EXPENSE_DT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_EXPENSE_DT_STR = dateTimeFormatter.format(DEFAULT_EXPENSE_DT);
    private static final String DEFAULT_INSTR_NO = "AAAAA";
    private static final String UPDATED_INSTR_NO = "BBBBB";

    private static final LocalDate DEFAULT_INSTR_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INSTR_DT = LocalDate.now(ZoneId.systemDefault());

    @Inject
    private ExpenseDetailsRepository expenseDetailsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restExpenseDetailsMockMvc;

    private ExpenseDetails expenseDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ExpenseDetailsResource expenseDetailsResource = new ExpenseDetailsResource();
        ReflectionTestUtils.setField(expenseDetailsResource, "expenseDetailsRepository", expenseDetailsRepository);
        this.restExpenseDetailsMockMvc = MockMvcBuilders.standaloneSetup(expenseDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        expenseDetails = new ExpenseDetails();
        expenseDetails.setExpenseNo(DEFAULT_EXPENSE_NO);
        expenseDetails.setExpenseAmt(DEFAULT_EXPENSE_AMT);
        expenseDetails.setExpenseDt(DEFAULT_EXPENSE_DT);
        expenseDetails.setInstrNo(DEFAULT_INSTR_NO);
        expenseDetails.setInstrDt(DEFAULT_INSTR_DT);
    }

    @Test
    @Transactional
    public void createExpenseDetails() throws Exception {
        int databaseSizeBeforeCreate = expenseDetailsRepository.findAll().size();

        // Create the ExpenseDetails

        restExpenseDetailsMockMvc.perform(post("/api/expenseDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(expenseDetails)))
                .andExpect(status().isCreated());

        // Validate the ExpenseDetails in the database
        List<ExpenseDetails> expenseDetailss = expenseDetailsRepository.findAll();
        assertThat(expenseDetailss).hasSize(databaseSizeBeforeCreate + 1);
        ExpenseDetails testExpenseDetails = expenseDetailss.get(expenseDetailss.size() - 1);
        assertThat(testExpenseDetails.getExpenseNo()).isEqualTo(DEFAULT_EXPENSE_NO);
        assertThat(testExpenseDetails.getExpenseAmt()).isEqualTo(DEFAULT_EXPENSE_AMT);
        assertThat(testExpenseDetails.getExpenseDt()).isEqualTo(DEFAULT_EXPENSE_DT);
        assertThat(testExpenseDetails.getInstrNo()).isEqualTo(DEFAULT_INSTR_NO);
        assertThat(testExpenseDetails.getInstrDt()).isEqualTo(DEFAULT_INSTR_DT);
    }

    @Test
    @Transactional
    public void getAllExpenseDetailss() throws Exception {
        // Initialize the database
        expenseDetailsRepository.saveAndFlush(expenseDetails);

        // Get all the expenseDetailss
        restExpenseDetailsMockMvc.perform(get("/api/expenseDetailss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(expenseDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].expenseNo").value(hasItem(DEFAULT_EXPENSE_NO.toString())))
                .andExpect(jsonPath("$.[*].expenseAmt").value(hasItem(DEFAULT_EXPENSE_AMT.intValue())))
                .andExpect(jsonPath("$.[*].expenseDt").value(hasItem(DEFAULT_EXPENSE_DT_STR)))
                .andExpect(jsonPath("$.[*].instrNo").value(hasItem(DEFAULT_INSTR_NO.toString())))
                .andExpect(jsonPath("$.[*].instrDt").value(hasItem(DEFAULT_INSTR_DT.toString())));
    }

    @Test
    @Transactional
    public void getExpenseDetails() throws Exception {
        // Initialize the database
        expenseDetailsRepository.saveAndFlush(expenseDetails);

        // Get the expenseDetails
        restExpenseDetailsMockMvc.perform(get("/api/expenseDetailss/{id}", expenseDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(expenseDetails.getId().intValue()))
            .andExpect(jsonPath("$.expenseNo").value(DEFAULT_EXPENSE_NO.toString()))
            .andExpect(jsonPath("$.expenseAmt").value(DEFAULT_EXPENSE_AMT.intValue()))
            .andExpect(jsonPath("$.expenseDt").value(DEFAULT_EXPENSE_DT_STR))
            .andExpect(jsonPath("$.instrNo").value(DEFAULT_INSTR_NO.toString()))
            .andExpect(jsonPath("$.instrDt").value(DEFAULT_INSTR_DT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingExpenseDetails() throws Exception {
        // Get the expenseDetails
        restExpenseDetailsMockMvc.perform(get("/api/expenseDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExpenseDetails() throws Exception {
        // Initialize the database
        expenseDetailsRepository.saveAndFlush(expenseDetails);

		int databaseSizeBeforeUpdate = expenseDetailsRepository.findAll().size();

        // Update the expenseDetails
        expenseDetails.setExpenseNo(UPDATED_EXPENSE_NO);
        expenseDetails.setExpenseAmt(UPDATED_EXPENSE_AMT);
        expenseDetails.setExpenseDt(UPDATED_EXPENSE_DT);
        expenseDetails.setInstrNo(UPDATED_INSTR_NO);
        expenseDetails.setInstrDt(UPDATED_INSTR_DT);

        restExpenseDetailsMockMvc.perform(put("/api/expenseDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(expenseDetails)))
                .andExpect(status().isOk());

        // Validate the ExpenseDetails in the database
        List<ExpenseDetails> expenseDetailss = expenseDetailsRepository.findAll();
        assertThat(expenseDetailss).hasSize(databaseSizeBeforeUpdate);
        ExpenseDetails testExpenseDetails = expenseDetailss.get(expenseDetailss.size() - 1);
        assertThat(testExpenseDetails.getExpenseNo()).isEqualTo(UPDATED_EXPENSE_NO);
        assertThat(testExpenseDetails.getExpenseAmt()).isEqualTo(UPDATED_EXPENSE_AMT);
        assertThat(testExpenseDetails.getExpenseDt()).isEqualTo(UPDATED_EXPENSE_DT);
        assertThat(testExpenseDetails.getInstrNo()).isEqualTo(UPDATED_INSTR_NO);
        assertThat(testExpenseDetails.getInstrDt()).isEqualTo(UPDATED_INSTR_DT);
    }

    @Test
    @Transactional
    public void deleteExpenseDetails() throws Exception {
        // Initialize the database
        expenseDetailsRepository.saveAndFlush(expenseDetails);

		int databaseSizeBeforeDelete = expenseDetailsRepository.findAll().size();

        // Get the expenseDetails
        restExpenseDetailsMockMvc.perform(delete("/api/expenseDetailss/{id}", expenseDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ExpenseDetails> expenseDetailss = expenseDetailsRepository.findAll();
        assertThat(expenseDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}
