package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ConnectionTypeMaster;
import com.callippus.water.erp.repository.ConnectionTypeMasterRepository;

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
 * Test class for the ConnectionTypeMasterResource REST controller.
 *
 * @see ConnectionTypeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ConnectionTypeMasterResourceIntTest {

    private static final String DEFAULT_CONNECTION_TYPE = "AAAAA";
    private static final String UPDATED_CONNECTION_TYPE = "BBBBB";

    @Inject
    private ConnectionTypeMasterRepository connectionTypeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restConnectionTypeMasterMockMvc;

    private ConnectionTypeMaster connectionTypeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ConnectionTypeMasterResource connectionTypeMasterResource = new ConnectionTypeMasterResource();
        ReflectionTestUtils.setField(connectionTypeMasterResource, "connectionTypeMasterRepository", connectionTypeMasterRepository);
        this.restConnectionTypeMasterMockMvc = MockMvcBuilders.standaloneSetup(connectionTypeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        connectionTypeMaster = new ConnectionTypeMaster();
        connectionTypeMaster.setConnectionType(DEFAULT_CONNECTION_TYPE);
    }

    @Test
    @Transactional
    public void createConnectionTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = connectionTypeMasterRepository.findAll().size();

        // Create the ConnectionTypeMaster

        restConnectionTypeMasterMockMvc.perform(post("/api/connectionTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(connectionTypeMaster)))
                .andExpect(status().isCreated());

        // Validate the ConnectionTypeMaster in the database
        List<ConnectionTypeMaster> connectionTypeMasters = connectionTypeMasterRepository.findAll();
        assertThat(connectionTypeMasters).hasSize(databaseSizeBeforeCreate + 1);
        ConnectionTypeMaster testConnectionTypeMaster = connectionTypeMasters.get(connectionTypeMasters.size() - 1);
        assertThat(testConnectionTypeMaster.getConnectionType()).isEqualTo(DEFAULT_CONNECTION_TYPE);
    }

    @Test
    @Transactional
    public void getAllConnectionTypeMasters() throws Exception {
        // Initialize the database
        connectionTypeMasterRepository.saveAndFlush(connectionTypeMaster);

        // Get all the connectionTypeMasters
        restConnectionTypeMasterMockMvc.perform(get("/api/connectionTypeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(connectionTypeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].connectionType").value(hasItem(DEFAULT_CONNECTION_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getConnectionTypeMaster() throws Exception {
        // Initialize the database
        connectionTypeMasterRepository.saveAndFlush(connectionTypeMaster);

        // Get the connectionTypeMaster
        restConnectionTypeMasterMockMvc.perform(get("/api/connectionTypeMasters/{id}", connectionTypeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(connectionTypeMaster.getId().intValue()))
            .andExpect(jsonPath("$.connectionType").value(DEFAULT_CONNECTION_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConnectionTypeMaster() throws Exception {
        // Get the connectionTypeMaster
        restConnectionTypeMasterMockMvc.perform(get("/api/connectionTypeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConnectionTypeMaster() throws Exception {
        // Initialize the database
        connectionTypeMasterRepository.saveAndFlush(connectionTypeMaster);

		int databaseSizeBeforeUpdate = connectionTypeMasterRepository.findAll().size();

        // Update the connectionTypeMaster
        connectionTypeMaster.setConnectionType(UPDATED_CONNECTION_TYPE);

        restConnectionTypeMasterMockMvc.perform(put("/api/connectionTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(connectionTypeMaster)))
                .andExpect(status().isOk());

        // Validate the ConnectionTypeMaster in the database
        List<ConnectionTypeMaster> connectionTypeMasters = connectionTypeMasterRepository.findAll();
        assertThat(connectionTypeMasters).hasSize(databaseSizeBeforeUpdate);
        ConnectionTypeMaster testConnectionTypeMaster = connectionTypeMasters.get(connectionTypeMasters.size() - 1);
        assertThat(testConnectionTypeMaster.getConnectionType()).isEqualTo(UPDATED_CONNECTION_TYPE);
    }

    @Test
    @Transactional
    public void deleteConnectionTypeMaster() throws Exception {
        // Initialize the database
        connectionTypeMasterRepository.saveAndFlush(connectionTypeMaster);

		int databaseSizeBeforeDelete = connectionTypeMasterRepository.findAll().size();

        // Get the connectionTypeMaster
        restConnectionTypeMasterMockMvc.perform(delete("/api/connectionTypeMasters/{id}", connectionTypeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ConnectionTypeMaster> connectionTypeMasters = connectionTypeMasterRepository.findAll();
        assertThat(connectionTypeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}
