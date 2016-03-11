package com.callippus.water.erp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.CategoryMaster;
import com.callippus.water.erp.repository.CategoryMasterRepository;


/**
 * Test class for the CategoryMasterResource REST controller.
 *
 * @see CategoryMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CategoryMasterResourceIntTest {

    private static final String DEFAULT_CATEGORY_NAME = "AAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBB";

    @Inject
    private CategoryMasterRepository categoryMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCategoryMasterMockMvc;

    private CategoryMaster categoryMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CategoryMasterResource categoryMasterResource = new CategoryMasterResource();
        ReflectionTestUtils.setField(categoryMasterResource, "categoryMasterRepository", categoryMasterRepository);
        this.restCategoryMasterMockMvc = MockMvcBuilders.standaloneSetup(categoryMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        categoryMaster = new CategoryMaster();
        categoryMaster.setCategoryName(DEFAULT_CATEGORY_NAME);
    }

    @Test
    @Transactional
    public void createCategoryMaster() throws Exception {
        int databaseSizeBeforeCreate = categoryMasterRepository.findAll().size();

        // Create the CategoryMaster

        restCategoryMasterMockMvc.perform(post("/api/categoryMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(categoryMaster)))
                .andExpect(status().isCreated());

        // Validate the CategoryMaster in the database
        List<CategoryMaster> categoryMasters = categoryMasterRepository.findAll();
        assertThat(categoryMasters).hasSize(databaseSizeBeforeCreate + 1);
        CategoryMaster testCategoryMaster = categoryMasters.get(categoryMasters.size() - 1);
        assertThat(testCategoryMaster.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
    }

    @Test
    @Transactional
    public void getAllCategoryMasters() throws Exception {
        // Initialize the database
        categoryMasterRepository.saveAndFlush(categoryMaster);

        // Get all the categoryMasters
        restCategoryMasterMockMvc.perform(get("/api/categoryMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(categoryMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME.toString())));
    }

    @Test
    @Transactional
    public void getCategoryMaster() throws Exception {
        // Initialize the database
        categoryMasterRepository.saveAndFlush(categoryMaster);

        // Get the categoryMaster
        restCategoryMasterMockMvc.perform(get("/api/categoryMasters/{id}", categoryMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(categoryMaster.getId().intValue()))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCategoryMaster() throws Exception {
        // Get the categoryMaster
        restCategoryMasterMockMvc.perform(get("/api/categoryMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategoryMaster() throws Exception {
        // Initialize the database
        categoryMasterRepository.saveAndFlush(categoryMaster);

		int databaseSizeBeforeUpdate = categoryMasterRepository.findAll().size();

        // Update the categoryMaster
        categoryMaster.setCategoryName(UPDATED_CATEGORY_NAME);

        restCategoryMasterMockMvc.perform(put("/api/categoryMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(categoryMaster)))
                .andExpect(status().isOk());

        // Validate the CategoryMaster in the database
        List<CategoryMaster> categoryMasters = categoryMasterRepository.findAll();
        assertThat(categoryMasters).hasSize(databaseSizeBeforeUpdate);
        CategoryMaster testCategoryMaster = categoryMasters.get(categoryMasters.size() - 1);
        assertThat(testCategoryMaster.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
    }

    @Test
    @Transactional
    public void deleteCategoryMaster() throws Exception {
        // Initialize the database
        categoryMasterRepository.saveAndFlush(categoryMaster);

		int databaseSizeBeforeDelete = categoryMasterRepository.findAll().size();

        // Get the categoryMaster
        restCategoryMasterMockMvc.perform(delete("/api/categoryMasters/{id}", categoryMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CategoryMaster> categoryMasters = categoryMasterRepository.findAll();
        assertThat(categoryMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}
