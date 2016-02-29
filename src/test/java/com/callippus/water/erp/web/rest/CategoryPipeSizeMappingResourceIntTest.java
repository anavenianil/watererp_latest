package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.CategoryPipeSizeMapping;
import com.callippus.water.erp.repository.CategoryPipeSizeMappingRepository;

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
 * Test class for the CategoryPipeSizeMappingResource REST controller.
 *
 * @see CategoryPipeSizeMappingResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CategoryPipeSizeMappingResourceIntTest {


    @Inject
    private CategoryPipeSizeMappingRepository categoryPipeSizeMappingRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCategoryPipeSizeMappingMockMvc;

    private CategoryPipeSizeMapping categoryPipeSizeMapping;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CategoryPipeSizeMappingResource categoryPipeSizeMappingResource = new CategoryPipeSizeMappingResource();
        ReflectionTestUtils.setField(categoryPipeSizeMappingResource, "categoryPipeSizeMappingRepository", categoryPipeSizeMappingRepository);
        this.restCategoryPipeSizeMappingMockMvc = MockMvcBuilders.standaloneSetup(categoryPipeSizeMappingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        categoryPipeSizeMapping = new CategoryPipeSizeMapping();
    }

    @Test
    @Transactional
    public void createCategoryPipeSizeMapping() throws Exception {
        int databaseSizeBeforeCreate = categoryPipeSizeMappingRepository.findAll().size();

        // Create the CategoryPipeSizeMapping

        restCategoryPipeSizeMappingMockMvc.perform(post("/api/categoryPipeSizeMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(categoryPipeSizeMapping)))
                .andExpect(status().isCreated());

        // Validate the CategoryPipeSizeMapping in the database
        List<CategoryPipeSizeMapping> categoryPipeSizeMappings = categoryPipeSizeMappingRepository.findAll();
        assertThat(categoryPipeSizeMappings).hasSize(databaseSizeBeforeCreate + 1);
        CategoryPipeSizeMapping testCategoryPipeSizeMapping = categoryPipeSizeMappings.get(categoryPipeSizeMappings.size() - 1);
    }

    @Test
    @Transactional
    public void getAllCategoryPipeSizeMappings() throws Exception {
        // Initialize the database
        categoryPipeSizeMappingRepository.saveAndFlush(categoryPipeSizeMapping);

        // Get all the categoryPipeSizeMappings
        restCategoryPipeSizeMappingMockMvc.perform(get("/api/categoryPipeSizeMappings?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(categoryPipeSizeMapping.getId().intValue())));
    }

    @Test
    @Transactional
    public void getCategoryPipeSizeMapping() throws Exception {
        // Initialize the database
        categoryPipeSizeMappingRepository.saveAndFlush(categoryPipeSizeMapping);

        // Get the categoryPipeSizeMapping
        restCategoryPipeSizeMappingMockMvc.perform(get("/api/categoryPipeSizeMappings/{id}", categoryPipeSizeMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(categoryPipeSizeMapping.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCategoryPipeSizeMapping() throws Exception {
        // Get the categoryPipeSizeMapping
        restCategoryPipeSizeMappingMockMvc.perform(get("/api/categoryPipeSizeMappings/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategoryPipeSizeMapping() throws Exception {
        // Initialize the database
        categoryPipeSizeMappingRepository.saveAndFlush(categoryPipeSizeMapping);

		int databaseSizeBeforeUpdate = categoryPipeSizeMappingRepository.findAll().size();

        // Update the categoryPipeSizeMapping

        restCategoryPipeSizeMappingMockMvc.perform(put("/api/categoryPipeSizeMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(categoryPipeSizeMapping)))
                .andExpect(status().isOk());

        // Validate the CategoryPipeSizeMapping in the database
        List<CategoryPipeSizeMapping> categoryPipeSizeMappings = categoryPipeSizeMappingRepository.findAll();
        assertThat(categoryPipeSizeMappings).hasSize(databaseSizeBeforeUpdate);
        CategoryPipeSizeMapping testCategoryPipeSizeMapping = categoryPipeSizeMappings.get(categoryPipeSizeMappings.size() - 1);
    }

    @Test
    @Transactional
    public void deleteCategoryPipeSizeMapping() throws Exception {
        // Initialize the database
        categoryPipeSizeMappingRepository.saveAndFlush(categoryPipeSizeMapping);

		int databaseSizeBeforeDelete = categoryPipeSizeMappingRepository.findAll().size();

        // Get the categoryPipeSizeMapping
        restCategoryPipeSizeMappingMockMvc.perform(delete("/api/categoryPipeSizeMappings/{id}", categoryPipeSizeMapping.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CategoryPipeSizeMapping> categoryPipeSizeMappings = categoryPipeSizeMappingRepository.findAll();
        assertThat(categoryPipeSizeMappings).hasSize(databaseSizeBeforeDelete - 1);
    }
}
