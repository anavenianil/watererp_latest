package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.AccessList;
import com.callippus.water.erp.repository.AccessListRepository;

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
 * Test class for the AccessListResource REST controller.
 *
 * @see AccessListResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class AccessListResourceIntTest {

    private static final String DEFAULT_USER_ID = "AAAAA";
    private static final String UPDATED_USER_ID = "BBBBB";

    @Inject
    private AccessListRepository accessListRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restAccessListMockMvc;

    private AccessList accessList;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AccessListResource accessListResource = new AccessListResource();
        ReflectionTestUtils.setField(accessListResource, "accessListRepository", accessListRepository);
        this.restAccessListMockMvc = MockMvcBuilders.standaloneSetup(accessListResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        accessList = new AccessList();
        accessList.setUserId(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createAccessList() throws Exception {
        int databaseSizeBeforeCreate = accessListRepository.findAll().size();

        // Create the AccessList

        restAccessListMockMvc.perform(post("/api/accessLists")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(accessList)))
                .andExpect(status().isCreated());

        // Validate the AccessList in the database
        List<AccessList> accessLists = accessListRepository.findAll();
        assertThat(accessLists).hasSize(databaseSizeBeforeCreate + 1);
        AccessList testAccessList = accessLists.get(accessLists.size() - 1);
        assertThat(testAccessList.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = accessListRepository.findAll().size();
        // set the field null
        accessList.setUserId(null);

        // Create the AccessList, which fails.

        restAccessListMockMvc.perform(post("/api/accessLists")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(accessList)))
                .andExpect(status().isBadRequest());

        List<AccessList> accessLists = accessListRepository.findAll();
        assertThat(accessLists).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAccessLists() throws Exception {
        // Initialize the database
        accessListRepository.saveAndFlush(accessList);

        // Get all the accessLists
        restAccessListMockMvc.perform(get("/api/accessLists?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(accessList.getId().intValue())))
                .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())));
    }

    @Test
    @Transactional
    public void getAccessList() throws Exception {
        // Initialize the database
        accessListRepository.saveAndFlush(accessList);

        // Get the accessList
        restAccessListMockMvc.perform(get("/api/accessLists/{id}", accessList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(accessList.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAccessList() throws Exception {
        // Get the accessList
        restAccessListMockMvc.perform(get("/api/accessLists/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccessList() throws Exception {
        // Initialize the database
        accessListRepository.saveAndFlush(accessList);

		int databaseSizeBeforeUpdate = accessListRepository.findAll().size();

        // Update the accessList
        accessList.setUserId(UPDATED_USER_ID);

        restAccessListMockMvc.perform(put("/api/accessLists")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(accessList)))
                .andExpect(status().isOk());

        // Validate the AccessList in the database
        List<AccessList> accessLists = accessListRepository.findAll();
        assertThat(accessLists).hasSize(databaseSizeBeforeUpdate);
        AccessList testAccessList = accessLists.get(accessLists.size() - 1);
        assertThat(testAccessList.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void deleteAccessList() throws Exception {
        // Initialize the database
        accessListRepository.saveAndFlush(accessList);

		int databaseSizeBeforeDelete = accessListRepository.findAll().size();

        // Get the accessList
        restAccessListMockMvc.perform(delete("/api/accessLists/{id}", accessList.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<AccessList> accessLists = accessListRepository.findAll();
        assertThat(accessLists).hasSize(databaseSizeBeforeDelete - 1);
    }
}
