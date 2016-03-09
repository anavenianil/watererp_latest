package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.MenuItem2Url;
import com.callippus.water.erp.repository.MenuItem2UrlRepository;

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
 * Test class for the MenuItem2UrlResource REST controller.
 *
 * @see MenuItem2UrlResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MenuItem2UrlResourceIntTest {


    @Inject
    private MenuItem2UrlRepository menuItem2UrlRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMenuItem2UrlMockMvc;

    private MenuItem2Url menuItem2Url;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MenuItem2UrlResource menuItem2UrlResource = new MenuItem2UrlResource();
        ReflectionTestUtils.setField(menuItem2UrlResource, "menuItem2UrlRepository", menuItem2UrlRepository);
        this.restMenuItem2UrlMockMvc = MockMvcBuilders.standaloneSetup(menuItem2UrlResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        menuItem2Url = new MenuItem2Url();
    }

    @Test
    @Transactional
    public void createMenuItem2Url() throws Exception {
        int databaseSizeBeforeCreate = menuItem2UrlRepository.findAll().size();

        // Create the MenuItem2Url

        restMenuItem2UrlMockMvc.perform(post("/api/menuItem2Urls")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(menuItem2Url)))
                .andExpect(status().isCreated());

        // Validate the MenuItem2Url in the database
        List<MenuItem2Url> menuItem2Urls = menuItem2UrlRepository.findAll();
        assertThat(menuItem2Urls).hasSize(databaseSizeBeforeCreate + 1);
        MenuItem2Url testMenuItem2Url = menuItem2Urls.get(menuItem2Urls.size() - 1);
    }

    @Test
    @Transactional
    public void getAllMenuItem2Urls() throws Exception {
        // Initialize the database
        menuItem2UrlRepository.saveAndFlush(menuItem2Url);

        // Get all the menuItem2Urls
        restMenuItem2UrlMockMvc.perform(get("/api/menuItem2Urls?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(menuItem2Url.getId().intValue())));
    }

    @Test
    @Transactional
    public void getMenuItem2Url() throws Exception {
        // Initialize the database
        menuItem2UrlRepository.saveAndFlush(menuItem2Url);

        // Get the menuItem2Url
        restMenuItem2UrlMockMvc.perform(get("/api/menuItem2Urls/{id}", menuItem2Url.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(menuItem2Url.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMenuItem2Url() throws Exception {
        // Get the menuItem2Url
        restMenuItem2UrlMockMvc.perform(get("/api/menuItem2Urls/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMenuItem2Url() throws Exception {
        // Initialize the database
        menuItem2UrlRepository.saveAndFlush(menuItem2Url);

		int databaseSizeBeforeUpdate = menuItem2UrlRepository.findAll().size();

        // Update the menuItem2Url

        restMenuItem2UrlMockMvc.perform(put("/api/menuItem2Urls")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(menuItem2Url)))
                .andExpect(status().isOk());

        // Validate the MenuItem2Url in the database
        List<MenuItem2Url> menuItem2Urls = menuItem2UrlRepository.findAll();
        assertThat(menuItem2Urls).hasSize(databaseSizeBeforeUpdate);
        MenuItem2Url testMenuItem2Url = menuItem2Urls.get(menuItem2Urls.size() - 1);
    }

    @Test
    @Transactional
    public void deleteMenuItem2Url() throws Exception {
        // Initialize the database
        menuItem2UrlRepository.saveAndFlush(menuItem2Url);

		int databaseSizeBeforeDelete = menuItem2UrlRepository.findAll().size();

        // Get the menuItem2Url
        restMenuItem2UrlMockMvc.perform(delete("/api/menuItem2Urls/{id}", menuItem2Url.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MenuItem2Url> menuItem2Urls = menuItem2UrlRepository.findAll();
        assertThat(menuItem2Urls).hasSize(databaseSizeBeforeDelete - 1);
    }
}
