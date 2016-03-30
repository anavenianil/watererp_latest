package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.SibEntry;
import com.callippus.water.erp.repository.SibEntryRepository;

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
 * Test class for the SibEntryResource REST controller.
 *
 * @see SibEntryResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SibEntryResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final Long DEFAULT_SIB_ID = 1L;
    private static final Long UPDATED_SIB_ID = 2L;
    private static final String DEFAULT_SO_NO = "AAAAA";
    private static final String UPDATED_SO_NO = "BBBBB";

    private static final ZonedDateTime DEFAULT_SO_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_SO_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_SO_DATE_STR = dateTimeFormatter.format(DEFAULT_SO_DATE);

    private static final ZonedDateTime DEFAULT_DEMAND_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_DEMAND_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_DEMAND_DATE_STR = dateTimeFormatter.format(DEFAULT_DEMAND_DATE);
    private static final String DEFAULT_DIR = "AAAAA";
    private static final String UPDATED_DIR = "BBBBB";
    private static final String DEFAULT_DIV_NAME = "AAAAA";
    private static final String UPDATED_DIV_NAME = "BBBBB";

    private static final Long DEFAULT_INV_NO = 1L;
    private static final Long UPDATED_INV_NO = 2L;

    private static final ZonedDateTime DEFAULT_SIB_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_SIB_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_SIB_DATE_STR = dateTimeFormatter.format(DEFAULT_SIB_DATE);
    private static final String DEFAULT_SIB_NO = "AAAAA";
    private static final String UPDATED_SIB_NO = "BBBBB";

    private static final ZonedDateTime DEFAULT_IR_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_IR_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_IR_DATE_STR = dateTimeFormatter.format(DEFAULT_IR_DATE);
    private static final String DEFAULT_IR_NO = "AAAAA";
    private static final String UPDATED_IR_NO = "BBBBB";
    private static final String DEFAULT_VENDOR_CODE = "AAAAA";
    private static final String UPDATED_VENDOR_CODE = "BBBBB";
    private static final String DEFAULT_REMARKS = "AAAAA";
    private static final String UPDATED_REMARKS = "BBBBB";

    private static final ZonedDateTime DEFAULT_TO_USER = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_TO_USER = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_TO_USER_STR = dateTimeFormatter.format(DEFAULT_TO_USER);

    private static final ZonedDateTime DEFAULT_FROM_USER = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_FROM_USER = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_FROM_USER_STR = dateTimeFormatter.format(DEFAULT_FROM_USER);

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);
    private static final String DEFAULT_DC_NO = "AAAAA";
    private static final String UPDATED_DC_NO = "BBBBB";

    private static final ZonedDateTime DEFAULT_DC_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_DC_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_DC_DATE_STR = dateTimeFormatter.format(DEFAULT_DC_DATE);

    @Inject
    private SibEntryRepository sibEntryRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restSibEntryMockMvc;

    private SibEntry sibEntry;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SibEntryResource sibEntryResource = new SibEntryResource();
        ReflectionTestUtils.setField(sibEntryResource, "sibEntryRepository", sibEntryRepository);
        this.restSibEntryMockMvc = MockMvcBuilders.standaloneSetup(sibEntryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        sibEntry = new SibEntry();
        sibEntry.setSibId(DEFAULT_SIB_ID);
        sibEntry.setSoNo(DEFAULT_SO_NO);
        sibEntry.setSoDate(DEFAULT_SO_DATE);
        sibEntry.setDemandDate(DEFAULT_DEMAND_DATE);
        sibEntry.setDir(DEFAULT_DIR);
        sibEntry.setDivName(DEFAULT_DIV_NAME);
        sibEntry.setInvNo(DEFAULT_INV_NO);
        sibEntry.setSibDate(DEFAULT_SIB_DATE);
        sibEntry.setSibNo(DEFAULT_SIB_NO);
        sibEntry.setIrDate(DEFAULT_IR_DATE);
        sibEntry.setIrNo(DEFAULT_IR_NO);
        sibEntry.setVendorCode(DEFAULT_VENDOR_CODE);
        sibEntry.setRemarks(DEFAULT_REMARKS);
        sibEntry.setToUser(DEFAULT_TO_USER);
        sibEntry.setFromUser(DEFAULT_FROM_USER);
        sibEntry.setStatus(DEFAULT_STATUS);
        sibEntry.setCreationDate(DEFAULT_CREATION_DATE);
        sibEntry.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        sibEntry.setDcNo(DEFAULT_DC_NO);
        sibEntry.setDcDate(DEFAULT_DC_DATE);
    }

    @Test
    @Transactional
    public void createSibEntry() throws Exception {
        int databaseSizeBeforeCreate = sibEntryRepository.findAll().size();

        // Create the SibEntry

        restSibEntryMockMvc.perform(post("/api/sibEntrys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sibEntry)))
                .andExpect(status().isCreated());

        // Validate the SibEntry in the database
        List<SibEntry> sibEntrys = sibEntryRepository.findAll();
        assertThat(sibEntrys).hasSize(databaseSizeBeforeCreate + 1);
        SibEntry testSibEntry = sibEntrys.get(sibEntrys.size() - 1);
        assertThat(testSibEntry.getSibId()).isEqualTo(DEFAULT_SIB_ID);
        assertThat(testSibEntry.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testSibEntry.getSoDate()).isEqualTo(DEFAULT_SO_DATE);
        assertThat(testSibEntry.getDemandDate()).isEqualTo(DEFAULT_DEMAND_DATE);
        assertThat(testSibEntry.getDir()).isEqualTo(DEFAULT_DIR);
        assertThat(testSibEntry.getDivName()).isEqualTo(DEFAULT_DIV_NAME);
        assertThat(testSibEntry.getInvNo()).isEqualTo(DEFAULT_INV_NO);
        assertThat(testSibEntry.getSibDate()).isEqualTo(DEFAULT_SIB_DATE);
        assertThat(testSibEntry.getSibNo()).isEqualTo(DEFAULT_SIB_NO);
        assertThat(testSibEntry.getIrDate()).isEqualTo(DEFAULT_IR_DATE);
        assertThat(testSibEntry.getIrNo()).isEqualTo(DEFAULT_IR_NO);
        assertThat(testSibEntry.getVendorCode()).isEqualTo(DEFAULT_VENDOR_CODE);
        assertThat(testSibEntry.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testSibEntry.getToUser()).isEqualTo(DEFAULT_TO_USER);
        assertThat(testSibEntry.getFromUser()).isEqualTo(DEFAULT_FROM_USER);
        assertThat(testSibEntry.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSibEntry.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testSibEntry.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testSibEntry.getDcNo()).isEqualTo(DEFAULT_DC_NO);
        assertThat(testSibEntry.getDcDate()).isEqualTo(DEFAULT_DC_DATE);
    }

    @Test
    @Transactional
    public void getAllSibEntrys() throws Exception {
        // Initialize the database
        sibEntryRepository.saveAndFlush(sibEntry);

        // Get all the sibEntrys
        restSibEntryMockMvc.perform(get("/api/sibEntrys?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(sibEntry.getId().intValue())))
                .andExpect(jsonPath("$.[*].sibId").value(hasItem(DEFAULT_SIB_ID.intValue())))
                .andExpect(jsonPath("$.[*].soNo").value(hasItem(DEFAULT_SO_NO.toString())))
                .andExpect(jsonPath("$.[*].soDate").value(hasItem(DEFAULT_SO_DATE_STR)))
                .andExpect(jsonPath("$.[*].demandDate").value(hasItem(DEFAULT_DEMAND_DATE_STR)))
                .andExpect(jsonPath("$.[*].dir").value(hasItem(DEFAULT_DIR.toString())))
                .andExpect(jsonPath("$.[*].divName").value(hasItem(DEFAULT_DIV_NAME.toString())))
                .andExpect(jsonPath("$.[*].invNo").value(hasItem(DEFAULT_INV_NO.intValue())))
                .andExpect(jsonPath("$.[*].sibDate").value(hasItem(DEFAULT_SIB_DATE_STR)))
                .andExpect(jsonPath("$.[*].sibNo").value(hasItem(DEFAULT_SIB_NO.toString())))
                .andExpect(jsonPath("$.[*].irDate").value(hasItem(DEFAULT_IR_DATE_STR)))
                .andExpect(jsonPath("$.[*].irNo").value(hasItem(DEFAULT_IR_NO.toString())))
                .andExpect(jsonPath("$.[*].vendorCode").value(hasItem(DEFAULT_VENDOR_CODE.toString())))
                .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
                .andExpect(jsonPath("$.[*].toUser").value(hasItem(DEFAULT_TO_USER_STR)))
                .andExpect(jsonPath("$.[*].fromUser").value(hasItem(DEFAULT_FROM_USER_STR)))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].dcNo").value(hasItem(DEFAULT_DC_NO.toString())))
                .andExpect(jsonPath("$.[*].dcDate").value(hasItem(DEFAULT_DC_DATE_STR)));
    }

    @Test
    @Transactional
    public void getSibEntry() throws Exception {
        // Initialize the database
        sibEntryRepository.saveAndFlush(sibEntry);

        // Get the sibEntry
        restSibEntryMockMvc.perform(get("/api/sibEntrys/{id}", sibEntry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(sibEntry.getId().intValue()))
            .andExpect(jsonPath("$.sibId").value(DEFAULT_SIB_ID.intValue()))
            .andExpect(jsonPath("$.soNo").value(DEFAULT_SO_NO.toString()))
            .andExpect(jsonPath("$.soDate").value(DEFAULT_SO_DATE_STR))
            .andExpect(jsonPath("$.demandDate").value(DEFAULT_DEMAND_DATE_STR))
            .andExpect(jsonPath("$.dir").value(DEFAULT_DIR.toString()))
            .andExpect(jsonPath("$.divName").value(DEFAULT_DIV_NAME.toString()))
            .andExpect(jsonPath("$.invNo").value(DEFAULT_INV_NO.intValue()))
            .andExpect(jsonPath("$.sibDate").value(DEFAULT_SIB_DATE_STR))
            .andExpect(jsonPath("$.sibNo").value(DEFAULT_SIB_NO.toString()))
            .andExpect(jsonPath("$.irDate").value(DEFAULT_IR_DATE_STR))
            .andExpect(jsonPath("$.irNo").value(DEFAULT_IR_NO.toString()))
            .andExpect(jsonPath("$.vendorCode").value(DEFAULT_VENDOR_CODE.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.toUser").value(DEFAULT_TO_USER_STR))
            .andExpect(jsonPath("$.fromUser").value(DEFAULT_FROM_USER_STR))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.dcNo").value(DEFAULT_DC_NO.toString()))
            .andExpect(jsonPath("$.dcDate").value(DEFAULT_DC_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingSibEntry() throws Exception {
        // Get the sibEntry
        restSibEntryMockMvc.perform(get("/api/sibEntrys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSibEntry() throws Exception {
        // Initialize the database
        sibEntryRepository.saveAndFlush(sibEntry);

		int databaseSizeBeforeUpdate = sibEntryRepository.findAll().size();

        // Update the sibEntry
        sibEntry.setSibId(UPDATED_SIB_ID);
        sibEntry.setSoNo(UPDATED_SO_NO);
        sibEntry.setSoDate(UPDATED_SO_DATE);
        sibEntry.setDemandDate(UPDATED_DEMAND_DATE);
        sibEntry.setDir(UPDATED_DIR);
        sibEntry.setDivName(UPDATED_DIV_NAME);
        sibEntry.setInvNo(UPDATED_INV_NO);
        sibEntry.setSibDate(UPDATED_SIB_DATE);
        sibEntry.setSibNo(UPDATED_SIB_NO);
        sibEntry.setIrDate(UPDATED_IR_DATE);
        sibEntry.setIrNo(UPDATED_IR_NO);
        sibEntry.setVendorCode(UPDATED_VENDOR_CODE);
        sibEntry.setRemarks(UPDATED_REMARKS);
        sibEntry.setToUser(UPDATED_TO_USER);
        sibEntry.setFromUser(UPDATED_FROM_USER);
        sibEntry.setStatus(UPDATED_STATUS);
        sibEntry.setCreationDate(UPDATED_CREATION_DATE);
        sibEntry.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        sibEntry.setDcNo(UPDATED_DC_NO);
        sibEntry.setDcDate(UPDATED_DC_DATE);

        restSibEntryMockMvc.perform(put("/api/sibEntrys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sibEntry)))
                .andExpect(status().isOk());

        // Validate the SibEntry in the database
        List<SibEntry> sibEntrys = sibEntryRepository.findAll();
        assertThat(sibEntrys).hasSize(databaseSizeBeforeUpdate);
        SibEntry testSibEntry = sibEntrys.get(sibEntrys.size() - 1);
        assertThat(testSibEntry.getSibId()).isEqualTo(UPDATED_SIB_ID);
        assertThat(testSibEntry.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testSibEntry.getSoDate()).isEqualTo(UPDATED_SO_DATE);
        assertThat(testSibEntry.getDemandDate()).isEqualTo(UPDATED_DEMAND_DATE);
        assertThat(testSibEntry.getDir()).isEqualTo(UPDATED_DIR);
        assertThat(testSibEntry.getDivName()).isEqualTo(UPDATED_DIV_NAME);
        assertThat(testSibEntry.getInvNo()).isEqualTo(UPDATED_INV_NO);
        assertThat(testSibEntry.getSibDate()).isEqualTo(UPDATED_SIB_DATE);
        assertThat(testSibEntry.getSibNo()).isEqualTo(UPDATED_SIB_NO);
        assertThat(testSibEntry.getIrDate()).isEqualTo(UPDATED_IR_DATE);
        assertThat(testSibEntry.getIrNo()).isEqualTo(UPDATED_IR_NO);
        assertThat(testSibEntry.getVendorCode()).isEqualTo(UPDATED_VENDOR_CODE);
        assertThat(testSibEntry.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testSibEntry.getToUser()).isEqualTo(UPDATED_TO_USER);
        assertThat(testSibEntry.getFromUser()).isEqualTo(UPDATED_FROM_USER);
        assertThat(testSibEntry.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSibEntry.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testSibEntry.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testSibEntry.getDcNo()).isEqualTo(UPDATED_DC_NO);
        assertThat(testSibEntry.getDcDate()).isEqualTo(UPDATED_DC_DATE);
    }

    @Test
    @Transactional
    public void deleteSibEntry() throws Exception {
        // Initialize the database
        sibEntryRepository.saveAndFlush(sibEntry);

		int databaseSizeBeforeDelete = sibEntryRepository.findAll().size();

        // Get the sibEntry
        restSibEntryMockMvc.perform(delete("/api/sibEntrys/{id}", sibEntry.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<SibEntry> sibEntrys = sibEntryRepository.findAll();
        assertThat(sibEntrys).hasSize(databaseSizeBeforeDelete - 1);
    }
}
