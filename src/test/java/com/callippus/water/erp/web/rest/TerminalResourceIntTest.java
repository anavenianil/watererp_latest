package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.Terminal;
import com.callippus.water.erp.repository.TerminalRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the TerminalResource REST controller.
 *
 * @see TerminalResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TerminalResourceIntTest {


    private static final Float DEFAULT_AMOUNT = 1F;
    private static final Float UPDATED_AMOUNT = 2F;
    private static final String DEFAULT_STATUS = "AAAAA";
    private static final String UPDATED_STATUS = "BBBBB";
    private static final String DEFAULT_USER_ID = "AAAAA";
    private static final String UPDATED_USER_ID = "BBBBB";
    private static final String DEFAULT_MR_CODE = "AAAAA";
    private static final String UPDATED_MR_CODE = "BBBBB";
    private static final String DEFAULT_SEC_CODE = "AAAAA";
    private static final String UPDATED_SEC_CODE = "BBBBB";
    private static final String DEFAULT_DIV_CODE = "AAAAA";
    private static final String UPDATED_DIV_CODE = "BBBBB";
    private static final String DEFAULT_SEC_NAME = "AAAAA";
    private static final String UPDATED_SEC_NAME = "BBBBB";
    private static final String DEFAULT_USER_NAME = "AAAAA";
    private static final String UPDATED_USER_NAME = "BBBBB";
    private static final String DEFAULT_MOBILE_NO = "AAAAA";
    private static final String UPDATED_MOBILE_NO = "BBBBB";
    private static final String DEFAULT_VER = "AAAAA";
    private static final String UPDATED_VER = "BBBBB";

    @Inject
    private TerminalRepository terminalRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTerminalMockMvc;

    private Terminal terminal;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TerminalResource terminalResource = new TerminalResource();
        ReflectionTestUtils.setField(terminalResource, "terminalRepository", terminalRepository);
        this.restTerminalMockMvc = MockMvcBuilders.standaloneSetup(terminalResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        terminal = new Terminal();
        terminal.setAmount(DEFAULT_AMOUNT);
        terminal.setStatus(DEFAULT_STATUS);
        terminal.setUserId(DEFAULT_USER_ID);
        terminal.setMrCode(DEFAULT_MR_CODE);
        terminal.setSecCode(DEFAULT_SEC_CODE);
        terminal.setDivCode(DEFAULT_DIV_CODE);
        terminal.setSecName(DEFAULT_SEC_NAME);
        terminal.setUserName(DEFAULT_USER_NAME);
        terminal.setMobileNo(DEFAULT_MOBILE_NO);
        terminal.setVer(DEFAULT_VER);
    }

    @Test
    @Transactional
    public void createTerminal() throws Exception {
        int databaseSizeBeforeCreate = terminalRepository.findAll().size();

        // Create the Terminal

        restTerminalMockMvc.perform(post("/api/terminals")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(terminal)))
                .andExpect(status().isCreated());

        // Validate the Terminal in the database
        List<Terminal> terminals = terminalRepository.findAll();
        assertThat(terminals).hasSize(databaseSizeBeforeCreate + 1);
        Terminal testTerminal = terminals.get(terminals.size() - 1);
        assertThat(testTerminal.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testTerminal.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTerminal.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testTerminal.getMrCode()).isEqualTo(DEFAULT_MR_CODE);
        assertThat(testTerminal.getSecCode()).isEqualTo(DEFAULT_SEC_CODE);
        assertThat(testTerminal.getDivCode()).isEqualTo(DEFAULT_DIV_CODE);
        assertThat(testTerminal.getSecName()).isEqualTo(DEFAULT_SEC_NAME);
        assertThat(testTerminal.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testTerminal.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testTerminal.getVer()).isEqualTo(DEFAULT_VER);
    }

    @Test
    @Transactional
    public void getAllTerminals() throws Exception {
        // Initialize the database
        terminalRepository.saveAndFlush(terminal);

        // Get all the terminals
        restTerminalMockMvc.perform(get("/api/terminals?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(terminal.getId().intValue())))
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
                .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
                .andExpect(jsonPath("$.[*].mrCode").value(hasItem(DEFAULT_MR_CODE.toString())))
                .andExpect(jsonPath("$.[*].secCode").value(hasItem(DEFAULT_SEC_CODE.toString())))
                .andExpect(jsonPath("$.[*].divCode").value(hasItem(DEFAULT_DIV_CODE.toString())))
                .andExpect(jsonPath("$.[*].secName").value(hasItem(DEFAULT_SEC_NAME.toString())))
                .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
                .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO.toString())))
                .andExpect(jsonPath("$.[*].ver").value(hasItem(DEFAULT_VER.toString())));
    }

    @Test
    @Transactional
    public void getTerminal() throws Exception {
        // Initialize the database
        terminalRepository.saveAndFlush(terminal);

        // Get the terminal
        restTerminalMockMvc.perform(get("/api/terminals/{id}", terminal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(terminal.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.mrCode").value(DEFAULT_MR_CODE.toString()))
            .andExpect(jsonPath("$.secCode").value(DEFAULT_SEC_CODE.toString()))
            .andExpect(jsonPath("$.divCode").value(DEFAULT_DIV_CODE.toString()))
            .andExpect(jsonPath("$.secName").value(DEFAULT_SEC_NAME.toString()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO.toString()))
            .andExpect(jsonPath("$.ver").value(DEFAULT_VER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTerminal() throws Exception {
        // Get the terminal
        restTerminalMockMvc.perform(get("/api/terminals/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTerminal() throws Exception {
        // Initialize the database
        terminalRepository.saveAndFlush(terminal);

		int databaseSizeBeforeUpdate = terminalRepository.findAll().size();

        // Update the terminal
        terminal.setAmount(UPDATED_AMOUNT);
        terminal.setStatus(UPDATED_STATUS);
        terminal.setUserId(UPDATED_USER_ID);
        terminal.setMrCode(UPDATED_MR_CODE);
        terminal.setSecCode(UPDATED_SEC_CODE);
        terminal.setDivCode(UPDATED_DIV_CODE);
        terminal.setSecName(UPDATED_SEC_NAME);
        terminal.setUserName(UPDATED_USER_NAME);
        terminal.setMobileNo(UPDATED_MOBILE_NO);
        terminal.setVer(UPDATED_VER);

        restTerminalMockMvc.perform(put("/api/terminals")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(terminal)))
                .andExpect(status().isOk());

        // Validate the Terminal in the database
        List<Terminal> terminals = terminalRepository.findAll();
        assertThat(terminals).hasSize(databaseSizeBeforeUpdate);
        Terminal testTerminal = terminals.get(terminals.size() - 1);
        assertThat(testTerminal.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testTerminal.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTerminal.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testTerminal.getMrCode()).isEqualTo(UPDATED_MR_CODE);
        assertThat(testTerminal.getSecCode()).isEqualTo(UPDATED_SEC_CODE);
        assertThat(testTerminal.getDivCode()).isEqualTo(UPDATED_DIV_CODE);
        assertThat(testTerminal.getSecName()).isEqualTo(UPDATED_SEC_NAME);
        assertThat(testTerminal.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testTerminal.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testTerminal.getVer()).isEqualTo(UPDATED_VER);
    }

    @Test
    @Transactional
    public void deleteTerminal() throws Exception {
        // Initialize the database
        terminalRepository.saveAndFlush(terminal);

		int databaseSizeBeforeDelete = terminalRepository.findAll().size();

        // Get the terminal
        restTerminalMockMvc.perform(delete("/api/terminals/{id}", terminal.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Terminal> terminals = terminalRepository.findAll();
        assertThat(terminals).hasSize(databaseSizeBeforeDelete - 1);
    }
}
