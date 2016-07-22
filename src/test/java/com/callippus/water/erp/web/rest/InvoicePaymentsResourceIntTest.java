package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.InvoicePayments;
import com.callippus.water.erp.repository.InvoicePaymentsRepository;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the InvoicePaymentsResource REST controller.
 *
 * @see InvoicePaymentsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class InvoicePaymentsResourceIntTest {


    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    @Inject
    private InvoicePaymentsRepository invoicePaymentsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restInvoicePaymentsMockMvc;

    private InvoicePayments invoicePayments;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InvoicePaymentsResource invoicePaymentsResource = new InvoicePaymentsResource();
        ReflectionTestUtils.setField(invoicePaymentsResource, "invoicePaymentsRepository", invoicePaymentsRepository);
        this.restInvoicePaymentsMockMvc = MockMvcBuilders.standaloneSetup(invoicePaymentsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        invoicePayments = new InvoicePayments();
        invoicePayments.setAmount(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createInvoicePayments() throws Exception {
        int databaseSizeBeforeCreate = invoicePaymentsRepository.findAll().size();

        // Create the InvoicePayments

        restInvoicePaymentsMockMvc.perform(post("/api/invoicePaymentss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(invoicePayments)))
                .andExpect(status().isCreated());

        // Validate the InvoicePayments in the database
        List<InvoicePayments> invoicePaymentss = invoicePaymentsRepository.findAll();
        assertThat(invoicePaymentss).hasSize(databaseSizeBeforeCreate + 1);
        InvoicePayments testInvoicePayments = invoicePaymentss.get(invoicePaymentss.size() - 1);
        assertThat(testInvoicePayments.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoicePaymentsRepository.findAll().size();
        // set the field null
        invoicePayments.setAmount(null);

        // Create the InvoicePayments, which fails.

        restInvoicePaymentsMockMvc.perform(post("/api/invoicePaymentss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(invoicePayments)))
                .andExpect(status().isBadRequest());

        List<InvoicePayments> invoicePaymentss = invoicePaymentsRepository.findAll();
        assertThat(invoicePaymentss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInvoicePaymentss() throws Exception {
        // Initialize the database
        invoicePaymentsRepository.saveAndFlush(invoicePayments);

        // Get all the invoicePaymentss
        restInvoicePaymentsMockMvc.perform(get("/api/invoicePaymentss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(invoicePayments.getId().intValue())))
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())));
    }

    @Test
    @Transactional
    public void getInvoicePayments() throws Exception {
        // Initialize the database
        invoicePaymentsRepository.saveAndFlush(invoicePayments);

        // Get the invoicePayments
        restInvoicePaymentsMockMvc.perform(get("/api/invoicePaymentss/{id}", invoicePayments.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(invoicePayments.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInvoicePayments() throws Exception {
        // Get the invoicePayments
        restInvoicePaymentsMockMvc.perform(get("/api/invoicePaymentss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoicePayments() throws Exception {
        // Initialize the database
        invoicePaymentsRepository.saveAndFlush(invoicePayments);

		int databaseSizeBeforeUpdate = invoicePaymentsRepository.findAll().size();

        // Update the invoicePayments
        invoicePayments.setAmount(UPDATED_AMOUNT);

        restInvoicePaymentsMockMvc.perform(put("/api/invoicePaymentss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(invoicePayments)))
                .andExpect(status().isOk());

        // Validate the InvoicePayments in the database
        List<InvoicePayments> invoicePaymentss = invoicePaymentsRepository.findAll();
        assertThat(invoicePaymentss).hasSize(databaseSizeBeforeUpdate);
        InvoicePayments testInvoicePayments = invoicePaymentss.get(invoicePaymentss.size() - 1);
        assertThat(testInvoicePayments.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void deleteInvoicePayments() throws Exception {
        // Initialize the database
        invoicePaymentsRepository.saveAndFlush(invoicePayments);

		int databaseSizeBeforeDelete = invoicePaymentsRepository.findAll().size();

        // Get the invoicePayments
        restInvoicePaymentsMockMvc.perform(delete("/api/invoicePaymentss/{id}", invoicePayments.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<InvoicePayments> invoicePaymentss = invoicePaymentsRepository.findAll();
        assertThat(invoicePaymentss).hasSize(databaseSizeBeforeDelete - 1);
    }
}
