package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.Url2Role;
import com.callippus.water.erp.repository.Url2RoleRepository;

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
 * Test class for the Url2RoleResource REST controller.
 *
 * @see Url2RoleResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class Url2RoleResourceIntTest {


    @Inject
    private Url2RoleRepository url2RoleRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restUrl2RoleMockMvc;

    private Url2Role url2Role;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Url2RoleResource url2RoleResource = new Url2RoleResource();
        ReflectionTestUtils.setField(url2RoleResource, "url2RoleRepository", url2RoleRepository);
        this.restUrl2RoleMockMvc = MockMvcBuilders.standaloneSetup(url2RoleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        url2Role = new Url2Role();
    }

    @Test
    @Transactional
    public void createUrl2Role() throws Exception {
        int databaseSizeBeforeCreate = url2RoleRepository.findAll().size();

        // Create the Url2Role

        restUrl2RoleMockMvc.perform(post("/api/url2Roles")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(url2Role)))
                .andExpect(status().isCreated());

        // Validate the Url2Role in the database
        List<Url2Role> url2Roles = url2RoleRepository.findAll();
        assertThat(url2Roles).hasSize(databaseSizeBeforeCreate + 1);
        Url2Role testUrl2Role = url2Roles.get(url2Roles.size() - 1);
    }

    @Test
    @Transactional
    public void getAllUrl2Roles() throws Exception {
        // Initialize the database
        url2RoleRepository.saveAndFlush(url2Role);

        // Get all the url2Roles
        restUrl2RoleMockMvc.perform(get("/api/url2Roles?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(url2Role.getId().intValue())));
    }

    @Test
    @Transactional
    public void getUrl2Role() throws Exception {
        // Initialize the database
        url2RoleRepository.saveAndFlush(url2Role);

        // Get the url2Role
        restUrl2RoleMockMvc.perform(get("/api/url2Roles/{id}", url2Role.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(url2Role.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUrl2Role() throws Exception {
        // Get the url2Role
        restUrl2RoleMockMvc.perform(get("/api/url2Roles/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUrl2Role() throws Exception {
        // Initialize the database
        url2RoleRepository.saveAndFlush(url2Role);

		int databaseSizeBeforeUpdate = url2RoleRepository.findAll().size();

        // Update the url2Role

        restUrl2RoleMockMvc.perform(put("/api/url2Roles")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(url2Role)))
                .andExpect(status().isOk());

        // Validate the Url2Role in the database
        List<Url2Role> url2Roles = url2RoleRepository.findAll();
        assertThat(url2Roles).hasSize(databaseSizeBeforeUpdate);
        Url2Role testUrl2Role = url2Roles.get(url2Roles.size() - 1);
    }

    @Test
    @Transactional
    public void deleteUrl2Role() throws Exception {
        // Initialize the database
        url2RoleRepository.saveAndFlush(url2Role);

		int databaseSizeBeforeDelete = url2RoleRepository.findAll().size();

        // Get the url2Role
        restUrl2RoleMockMvc.perform(delete("/api/url2Roles/{id}", url2Role.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Url2Role> url2Roles = url2RoleRepository.findAll();
        assertThat(url2Roles).hasSize(databaseSizeBeforeDelete - 1);
    }
}
