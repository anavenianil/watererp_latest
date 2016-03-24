package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ZoneMaster;
import com.callippus.water.erp.repository.ZoneMasterRepository;

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
 * Test class for the ZoneMasterResource REST controller.
 *
 * @see ZoneMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ZoneMasterResourceIntTest {

    private static final String DEFAULT_ZONE_NAME = "AAAAA";
    private static final String UPDATED_ZONE_NAME = "BBBBB";
    private static final String DEFAULT_ZONE_CODE = "AAAAA";
    private static final String UPDATED_ZONE_CODE = "BBBBB";

    @Inject
    private ZoneMasterRepository zoneMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restZoneMasterMockMvc;

    private ZoneMaster zoneMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ZoneMasterResource zoneMasterResource = new ZoneMasterResource();
        ReflectionTestUtils.setField(zoneMasterResource, "zoneMasterRepository", zoneMasterRepository);
        this.restZoneMasterMockMvc = MockMvcBuilders.standaloneSetup(zoneMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        zoneMaster = new ZoneMaster();
        zoneMaster.setZoneName(DEFAULT_ZONE_NAME);
        zoneMaster.setZoneCode(DEFAULT_ZONE_CODE);
    }

    @Test
    @Transactional
    public void createZoneMaster() throws Exception {
        int databaseSizeBeforeCreate = zoneMasterRepository.findAll().size();

        // Create the ZoneMaster

        restZoneMasterMockMvc.perform(post("/api/zoneMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(zoneMaster)))
                .andExpect(status().isCreated());

        // Validate the ZoneMaster in the database
        List<ZoneMaster> zoneMasters = zoneMasterRepository.findAll();
        assertThat(zoneMasters).hasSize(databaseSizeBeforeCreate + 1);
        ZoneMaster testZoneMaster = zoneMasters.get(zoneMasters.size() - 1);
        assertThat(testZoneMaster.getZoneName()).isEqualTo(DEFAULT_ZONE_NAME);
        assertThat(testZoneMaster.getZoneCode()).isEqualTo(DEFAULT_ZONE_CODE);
    }

    @Test
    @Transactional
    public void getAllZoneMasters() throws Exception {
        // Initialize the database
        zoneMasterRepository.saveAndFlush(zoneMaster);

        // Get all the zoneMasters
        restZoneMasterMockMvc.perform(get("/api/zoneMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(zoneMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].zoneName").value(hasItem(DEFAULT_ZONE_NAME.toString())))
                .andExpect(jsonPath("$.[*].zoneCode").value(hasItem(DEFAULT_ZONE_CODE.toString())));
    }

    @Test
    @Transactional
    public void getZoneMaster() throws Exception {
        // Initialize the database
        zoneMasterRepository.saveAndFlush(zoneMaster);

        // Get the zoneMaster
        restZoneMasterMockMvc.perform(get("/api/zoneMasters/{id}", zoneMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(zoneMaster.getId().intValue()))
            .andExpect(jsonPath("$.zoneName").value(DEFAULT_ZONE_NAME.toString()))
            .andExpect(jsonPath("$.zoneCode").value(DEFAULT_ZONE_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingZoneMaster() throws Exception {
        // Get the zoneMaster
        restZoneMasterMockMvc.perform(get("/api/zoneMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateZoneMaster() throws Exception {
        // Initialize the database
        zoneMasterRepository.saveAndFlush(zoneMaster);

		int databaseSizeBeforeUpdate = zoneMasterRepository.findAll().size();

        // Update the zoneMaster
        zoneMaster.setZoneName(UPDATED_ZONE_NAME);
        zoneMaster.setZoneCode(UPDATED_ZONE_CODE);

        restZoneMasterMockMvc.perform(put("/api/zoneMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(zoneMaster)))
                .andExpect(status().isOk());

        // Validate the ZoneMaster in the database
        List<ZoneMaster> zoneMasters = zoneMasterRepository.findAll();
        assertThat(zoneMasters).hasSize(databaseSizeBeforeUpdate);
        ZoneMaster testZoneMaster = zoneMasters.get(zoneMasters.size() - 1);
        assertThat(testZoneMaster.getZoneName()).isEqualTo(UPDATED_ZONE_NAME);
        assertThat(testZoneMaster.getZoneCode()).isEqualTo(UPDATED_ZONE_CODE);
    }

    @Test
    @Transactional
    public void deleteZoneMaster() throws Exception {
        // Initialize the database
        zoneMasterRepository.saveAndFlush(zoneMaster);

		int databaseSizeBeforeDelete = zoneMasterRepository.findAll().size();

        // Get the zoneMaster
        restZoneMasterMockMvc.perform(delete("/api/zoneMasters/{id}", zoneMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ZoneMaster> zoneMasters = zoneMasterRepository.findAll();
        assertThat(zoneMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}
