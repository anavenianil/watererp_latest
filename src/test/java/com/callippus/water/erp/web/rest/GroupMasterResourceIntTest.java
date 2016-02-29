package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.GroupMaster;
import com.callippus.water.erp.repository.GroupMasterRepository;

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
 * Test class for the GroupMasterResource REST controller.
 *
 * @see GroupMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class GroupMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    @Inject
    private GroupMasterRepository groupMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restGroupMasterMockMvc;

    private GroupMaster groupMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GroupMasterResource groupMasterResource = new GroupMasterResource();
        ReflectionTestUtils.setField(groupMasterResource, "groupMasterRepository", groupMasterRepository);
        this.restGroupMasterMockMvc = MockMvcBuilders.standaloneSetup(groupMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        groupMaster = new GroupMaster();
        groupMaster.setName(DEFAULT_NAME);
        groupMaster.setCreationDate(DEFAULT_CREATION_DATE);
        groupMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        groupMaster.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createGroupMaster() throws Exception {
        int databaseSizeBeforeCreate = groupMasterRepository.findAll().size();

        // Create the GroupMaster

        restGroupMasterMockMvc.perform(post("/api/groupMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(groupMaster)))
                .andExpect(status().isCreated());

        // Validate the GroupMaster in the database
        List<GroupMaster> groupMasters = groupMasterRepository.findAll();
        assertThat(groupMasters).hasSize(databaseSizeBeforeCreate + 1);
        GroupMaster testGroupMaster = groupMasters.get(groupMasters.size() - 1);
        assertThat(testGroupMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGroupMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testGroupMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testGroupMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllGroupMasters() throws Exception {
        // Initialize the database
        groupMasterRepository.saveAndFlush(groupMaster);

        // Get all the groupMasters
        restGroupMasterMockMvc.perform(get("/api/groupMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(groupMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getGroupMaster() throws Exception {
        // Initialize the database
        groupMasterRepository.saveAndFlush(groupMaster);

        // Get the groupMaster
        restGroupMasterMockMvc.perform(get("/api/groupMasters/{id}", groupMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(groupMaster.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGroupMaster() throws Exception {
        // Get the groupMaster
        restGroupMasterMockMvc.perform(get("/api/groupMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGroupMaster() throws Exception {
        // Initialize the database
        groupMasterRepository.saveAndFlush(groupMaster);

		int databaseSizeBeforeUpdate = groupMasterRepository.findAll().size();

        // Update the groupMaster
        groupMaster.setName(UPDATED_NAME);
        groupMaster.setCreationDate(UPDATED_CREATION_DATE);
        groupMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        groupMaster.setDescription(UPDATED_DESCRIPTION);

        restGroupMasterMockMvc.perform(put("/api/groupMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(groupMaster)))
                .andExpect(status().isOk());

        // Validate the GroupMaster in the database
        List<GroupMaster> groupMasters = groupMasterRepository.findAll();
        assertThat(groupMasters).hasSize(databaseSizeBeforeUpdate);
        GroupMaster testGroupMaster = groupMasters.get(groupMasters.size() - 1);
        assertThat(testGroupMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGroupMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testGroupMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testGroupMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteGroupMaster() throws Exception {
        // Initialize the database
        groupMasterRepository.saveAndFlush(groupMaster);

		int databaseSizeBeforeDelete = groupMasterRepository.findAll().size();

        // Get the groupMaster
        restGroupMasterMockMvc.perform(delete("/api/groupMasters/{id}", groupMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<GroupMaster> groupMasters = groupMasterRepository.findAll();
        assertThat(groupMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}
