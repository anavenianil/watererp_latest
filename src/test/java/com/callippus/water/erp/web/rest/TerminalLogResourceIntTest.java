package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.TerminalLog;
import com.callippus.water.erp.repository.TerminalLogRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the TerminalLogResource REST controller.
 *
 * @see TerminalLogResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TerminalLogResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final Float DEFAULT_AMOUNT = 1F;
    private static final Float UPDATED_AMOUNT = 2F;

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED);
    private static final String DEFAULT_MODIFIED_BY = "AAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBB";
    private static final String DEFAULT_USER_ID = "AAAAA";
    private static final String UPDATED_USER_ID = "BBBBB";

    private static final LocalDate DEFAULT_BANK_DEPOSIT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BANK_DEPOSIT_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_BEFORE_UPDATE = "AAAAA";
    private static final String UPDATED_BEFORE_UPDATE = "BBBBB";
    private static final String DEFAULT_AFTER_UPDATE = "AAAAA";
    private static final String UPDATED_AFTER_UPDATE = "BBBBB";
    private static final String DEFAULT_MR_CODE = "AAAAA";
    private static final String UPDATED_MR_CODE = "BBBBB";
    private static final String DEFAULT_REMARK = "AAAAA";
    private static final String UPDATED_REMARK = "BBBBB";
    private static final String DEFAULT_TXN_TYPE = "AAAAA";
    private static final String UPDATED_TXN_TYPE = "BBBBB";

    @Inject
    private TerminalLogRepository terminalLogRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTerminalLogMockMvc;

    private TerminalLog terminalLog;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TerminalLogResource terminalLogResource = new TerminalLogResource();
        ReflectionTestUtils.setField(terminalLogResource, "terminalLogRepository", terminalLogRepository);
        this.restTerminalLogMockMvc = MockMvcBuilders.standaloneSetup(terminalLogResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        terminalLog = new TerminalLog();
        terminalLog.setAmount(DEFAULT_AMOUNT);
        terminalLog.setLastModified(DEFAULT_LAST_MODIFIED);
        terminalLog.setModifiedBy(DEFAULT_MODIFIED_BY);
        terminalLog.setUserId(DEFAULT_USER_ID);
        terminalLog.setBankDepositDate(DEFAULT_BANK_DEPOSIT_DATE);
        terminalLog.setBeforeUpdate(DEFAULT_BEFORE_UPDATE);
        terminalLog.setAfterUpdate(DEFAULT_AFTER_UPDATE);
        terminalLog.setMrCode(DEFAULT_MR_CODE);
        terminalLog.setRemark(DEFAULT_REMARK);
        terminalLog.setTxnType(DEFAULT_TXN_TYPE);
    }

    @Test
    @Transactional
    public void createTerminalLog() throws Exception {
        int databaseSizeBeforeCreate = terminalLogRepository.findAll().size();

        // Create the TerminalLog

        restTerminalLogMockMvc.perform(post("/api/terminalLogs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(terminalLog)))
                .andExpect(status().isCreated());

        // Validate the TerminalLog in the database
        List<TerminalLog> terminalLogs = terminalLogRepository.findAll();
        assertThat(terminalLogs).hasSize(databaseSizeBeforeCreate + 1);
        TerminalLog testTerminalLog = terminalLogs.get(terminalLogs.size() - 1);
        assertThat(testTerminalLog.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testTerminalLog.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testTerminalLog.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testTerminalLog.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testTerminalLog.getBankDepositDate()).isEqualTo(DEFAULT_BANK_DEPOSIT_DATE);
        assertThat(testTerminalLog.getBeforeUpdate()).isEqualTo(DEFAULT_BEFORE_UPDATE);
        assertThat(testTerminalLog.getAfterUpdate()).isEqualTo(DEFAULT_AFTER_UPDATE);
        assertThat(testTerminalLog.getMrCode()).isEqualTo(DEFAULT_MR_CODE);
        assertThat(testTerminalLog.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testTerminalLog.getTxnType()).isEqualTo(DEFAULT_TXN_TYPE);
    }

    @Test
    @Transactional
    public void getAllTerminalLogs() throws Exception {
        // Initialize the database
        terminalLogRepository.saveAndFlush(terminalLog);

        // Get all the terminalLogs
        restTerminalLogMockMvc.perform(get("/api/terminalLogs?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(terminalLog.getId().intValue())))
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED_STR)))
                .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.toString())))
                .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
                .andExpect(jsonPath("$.[*].bankDepositDate").value(hasItem(DEFAULT_BANK_DEPOSIT_DATE.toString())))
                .andExpect(jsonPath("$.[*].beforeUpdate").value(hasItem(DEFAULT_BEFORE_UPDATE.toString())))
                .andExpect(jsonPath("$.[*].afterUpdate").value(hasItem(DEFAULT_AFTER_UPDATE.toString())))
                .andExpect(jsonPath("$.[*].mrCode").value(hasItem(DEFAULT_MR_CODE.toString())))
                .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
                .andExpect(jsonPath("$.[*].txnType").value(hasItem(DEFAULT_TXN_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getTerminalLog() throws Exception {
        // Initialize the database
        terminalLogRepository.saveAndFlush(terminalLog);

        // Get the terminalLog
        restTerminalLogMockMvc.perform(get("/api/terminalLogs/{id}", terminalLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(terminalLog.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED_STR))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.bankDepositDate").value(DEFAULT_BANK_DEPOSIT_DATE.toString()))
            .andExpect(jsonPath("$.beforeUpdate").value(DEFAULT_BEFORE_UPDATE.toString()))
            .andExpect(jsonPath("$.afterUpdate").value(DEFAULT_AFTER_UPDATE.toString()))
            .andExpect(jsonPath("$.mrCode").value(DEFAULT_MR_CODE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.txnType").value(DEFAULT_TXN_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTerminalLog() throws Exception {
        // Get the terminalLog
        restTerminalLogMockMvc.perform(get("/api/terminalLogs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTerminalLog() throws Exception {
        // Initialize the database
        terminalLogRepository.saveAndFlush(terminalLog);

		int databaseSizeBeforeUpdate = terminalLogRepository.findAll().size();

        // Update the terminalLog
        terminalLog.setAmount(UPDATED_AMOUNT);
        terminalLog.setLastModified(UPDATED_LAST_MODIFIED);
        terminalLog.setModifiedBy(UPDATED_MODIFIED_BY);
        terminalLog.setUserId(UPDATED_USER_ID);
        terminalLog.setBankDepositDate(UPDATED_BANK_DEPOSIT_DATE);
        terminalLog.setBeforeUpdate(UPDATED_BEFORE_UPDATE);
        terminalLog.setAfterUpdate(UPDATED_AFTER_UPDATE);
        terminalLog.setMrCode(UPDATED_MR_CODE);
        terminalLog.setRemark(UPDATED_REMARK);
        terminalLog.setTxnType(UPDATED_TXN_TYPE);

        restTerminalLogMockMvc.perform(put("/api/terminalLogs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(terminalLog)))
                .andExpect(status().isOk());

        // Validate the TerminalLog in the database
        List<TerminalLog> terminalLogs = terminalLogRepository.findAll();
        assertThat(terminalLogs).hasSize(databaseSizeBeforeUpdate);
        TerminalLog testTerminalLog = terminalLogs.get(terminalLogs.size() - 1);
        assertThat(testTerminalLog.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testTerminalLog.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testTerminalLog.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTerminalLog.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testTerminalLog.getBankDepositDate()).isEqualTo(UPDATED_BANK_DEPOSIT_DATE);
        assertThat(testTerminalLog.getBeforeUpdate()).isEqualTo(UPDATED_BEFORE_UPDATE);
        assertThat(testTerminalLog.getAfterUpdate()).isEqualTo(UPDATED_AFTER_UPDATE);
        assertThat(testTerminalLog.getMrCode()).isEqualTo(UPDATED_MR_CODE);
        assertThat(testTerminalLog.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testTerminalLog.getTxnType()).isEqualTo(UPDATED_TXN_TYPE);
    }

    @Test
    @Transactional
    public void deleteTerminalLog() throws Exception {
        // Initialize the database
        terminalLogRepository.saveAndFlush(terminalLog);

		int databaseSizeBeforeDelete = terminalLogRepository.findAll().size();

        // Get the terminalLog
        restTerminalLogMockMvc.perform(delete("/api/terminalLogs/{id}", terminalLog.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<TerminalLog> terminalLogs = terminalLogRepository.findAll();
        assertThat(terminalLogs).hasSize(databaseSizeBeforeDelete - 1);
    }
}
