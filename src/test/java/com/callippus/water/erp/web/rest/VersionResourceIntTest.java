package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.Version;
import com.callippus.water.erp.repository.VersionRepository;

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
 * Test class for the VersionResource REST controller.
 *
 * @see VersionResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class VersionResourceIntTest {

    private static final String DEFAULT_VERSION_LOW = "AAAAA";
    private static final String UPDATED_VERSION_LOW = "BBBBB";
    private static final String DEFAULT_VERSION_HIGH = "AAAAA";
    private static final String UPDATED_VERSION_HIGH = "BBBBB";

    @Inject
    private VersionRepository versionRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restVersionMockMvc;

    private Version version;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        VersionResource versionResource = new VersionResource();
        ReflectionTestUtils.setField(versionResource, "versionRepository", versionRepository);
        this.restVersionMockMvc = MockMvcBuilders.standaloneSetup(versionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        version = new Version();
        version.setVersionLow(DEFAULT_VERSION_LOW);
        version.setVersionHigh(DEFAULT_VERSION_HIGH);
    }

    @Test
    @Transactional
    public void createVersion() throws Exception {
        int databaseSizeBeforeCreate = versionRepository.findAll().size();

        // Create the Version

        restVersionMockMvc.perform(post("/api/versions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(version)))
                .andExpect(status().isCreated());

        // Validate the Version in the database
        List<Version> versions = versionRepository.findAll();
        assertThat(versions).hasSize(databaseSizeBeforeCreate + 1);
        Version testVersion = versions.get(versions.size() - 1);
        assertThat(testVersion.getVersionLow()).isEqualTo(DEFAULT_VERSION_LOW);
        assertThat(testVersion.getVersionHigh()).isEqualTo(DEFAULT_VERSION_HIGH);
    }

    @Test
    @Transactional
    public void getAllVersions() throws Exception {
        // Initialize the database
        versionRepository.saveAndFlush(version);

        // Get all the versions
        restVersionMockMvc.perform(get("/api/versions?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(version.getId().intValue())))
                .andExpect(jsonPath("$.[*].versionLow").value(hasItem(DEFAULT_VERSION_LOW.toString())))
                .andExpect(jsonPath("$.[*].versionHigh").value(hasItem(DEFAULT_VERSION_HIGH.toString())));
    }

    @Test
    @Transactional
    public void getVersion() throws Exception {
        // Initialize the database
        versionRepository.saveAndFlush(version);

        // Get the version
        restVersionMockMvc.perform(get("/api/versions/{id}", version.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(version.getId().intValue()))
            .andExpect(jsonPath("$.versionLow").value(DEFAULT_VERSION_LOW.toString()))
            .andExpect(jsonPath("$.versionHigh").value(DEFAULT_VERSION_HIGH.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVersion() throws Exception {
        // Get the version
        restVersionMockMvc.perform(get("/api/versions/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVersion() throws Exception {
        // Initialize the database
        versionRepository.saveAndFlush(version);

		int databaseSizeBeforeUpdate = versionRepository.findAll().size();

        // Update the version
        version.setVersionLow(UPDATED_VERSION_LOW);
        version.setVersionHigh(UPDATED_VERSION_HIGH);

        restVersionMockMvc.perform(put("/api/versions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(version)))
                .andExpect(status().isOk());

        // Validate the Version in the database
        List<Version> versions = versionRepository.findAll();
        assertThat(versions).hasSize(databaseSizeBeforeUpdate);
        Version testVersion = versions.get(versions.size() - 1);
        assertThat(testVersion.getVersionLow()).isEqualTo(UPDATED_VERSION_LOW);
        assertThat(testVersion.getVersionHigh()).isEqualTo(UPDATED_VERSION_HIGH);
    }

    @Test
    @Transactional
    public void deleteVersion() throws Exception {
        // Initialize the database
        versionRepository.saveAndFlush(version);

		int databaseSizeBeforeDelete = versionRepository.findAll().size();

        // Get the version
        restVersionMockMvc.perform(delete("/api/versions/{id}", version.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Version> versions = versionRepository.findAll();
        assertThat(versions).hasSize(databaseSizeBeforeDelete - 1);
    }
}
