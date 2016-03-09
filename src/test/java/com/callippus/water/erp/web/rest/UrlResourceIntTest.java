package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.Url;
import com.callippus.water.erp.repository.UrlRepository;

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
 * Test class for the UrlResource REST controller.
 *
 * @see UrlResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class UrlResourceIntTest {

    private static final String DEFAULT_URL_PATTERN = "AAAAA";
    private static final String UPDATED_URL_PATTERN = "BBBBB";

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    @Inject
    private UrlRepository urlRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restUrlMockMvc;

    private Url url;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UrlResource urlResource = new UrlResource();
        ReflectionTestUtils.setField(urlResource, "urlRepository", urlRepository);
        this.restUrlMockMvc = MockMvcBuilders.standaloneSetup(urlResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        url = new Url();
        url.setUrlPattern(DEFAULT_URL_PATTERN);
        url.setVersion(DEFAULT_VERSION);
    }

    @Test
    @Transactional
    public void createUrl() throws Exception {
        int databaseSizeBeforeCreate = urlRepository.findAll().size();

        // Create the Url

        restUrlMockMvc.perform(post("/api/urls")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(url)))
                .andExpect(status().isCreated());

        // Validate the Url in the database
        List<Url> urls = urlRepository.findAll();
        assertThat(urls).hasSize(databaseSizeBeforeCreate + 1);
        Url testUrl = urls.get(urls.size() - 1);
        assertThat(testUrl.getUrlPattern()).isEqualTo(DEFAULT_URL_PATTERN);
        assertThat(testUrl.getVersion()).isEqualTo(DEFAULT_VERSION);
    }

    @Test
    @Transactional
    public void checkUrlPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = urlRepository.findAll().size();
        // set the field null
        url.setUrlPattern(null);

        // Create the Url, which fails.

        restUrlMockMvc.perform(post("/api/urls")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(url)))
                .andExpect(status().isBadRequest());

        List<Url> urls = urlRepository.findAll();
        assertThat(urls).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUrls() throws Exception {
        // Initialize the database
        urlRepository.saveAndFlush(url);

        // Get all the urls
        restUrlMockMvc.perform(get("/api/urls?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(url.getId().intValue())))
                .andExpect(jsonPath("$.[*].urlPattern").value(hasItem(DEFAULT_URL_PATTERN.toString())))
                .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }

    @Test
    @Transactional
    public void getUrl() throws Exception {
        // Initialize the database
        urlRepository.saveAndFlush(url);

        // Get the url
        restUrlMockMvc.perform(get("/api/urls/{id}", url.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(url.getId().intValue()))
            .andExpect(jsonPath("$.urlPattern").value(DEFAULT_URL_PATTERN.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION));
    }

    @Test
    @Transactional
    public void getNonExistingUrl() throws Exception {
        // Get the url
        restUrlMockMvc.perform(get("/api/urls/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUrl() throws Exception {
        // Initialize the database
        urlRepository.saveAndFlush(url);

		int databaseSizeBeforeUpdate = urlRepository.findAll().size();

        // Update the url
        url.setUrlPattern(UPDATED_URL_PATTERN);
        url.setVersion(UPDATED_VERSION);

        restUrlMockMvc.perform(put("/api/urls")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(url)))
                .andExpect(status().isOk());

        // Validate the Url in the database
        List<Url> urls = urlRepository.findAll();
        assertThat(urls).hasSize(databaseSizeBeforeUpdate);
        Url testUrl = urls.get(urls.size() - 1);
        assertThat(testUrl.getUrlPattern()).isEqualTo(UPDATED_URL_PATTERN);
        assertThat(testUrl.getVersion()).isEqualTo(UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void deleteUrl() throws Exception {
        // Initialize the database
        urlRepository.saveAndFlush(url);

		int databaseSizeBeforeDelete = urlRepository.findAll().size();

        // Get the url
        restUrlMockMvc.perform(delete("/api/urls/{id}", url.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Url> urls = urlRepository.findAll();
        assertThat(urls).hasSize(databaseSizeBeforeDelete - 1);
    }
}
