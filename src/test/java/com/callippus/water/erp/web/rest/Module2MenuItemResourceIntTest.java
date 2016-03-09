package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.Module2MenuItem;
import com.callippus.water.erp.repository.Module2MenuItemRepository;

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
 * Test class for the Module2MenuItemResource REST controller.
 *
 * @see Module2MenuItemResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class Module2MenuItemResourceIntTest {


    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    @Inject
    private Module2MenuItemRepository module2MenuItemRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restModule2MenuItemMockMvc;

    private Module2MenuItem module2MenuItem;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Module2MenuItemResource module2MenuItemResource = new Module2MenuItemResource();
        ReflectionTestUtils.setField(module2MenuItemResource, "module2MenuItemRepository", module2MenuItemRepository);
        this.restModule2MenuItemMockMvc = MockMvcBuilders.standaloneSetup(module2MenuItemResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        module2MenuItem = new Module2MenuItem();
        module2MenuItem.setPriority(DEFAULT_PRIORITY);
    }

    @Test
    @Transactional
    public void createModule2MenuItem() throws Exception {
        int databaseSizeBeforeCreate = module2MenuItemRepository.findAll().size();

        // Create the Module2MenuItem

        restModule2MenuItemMockMvc.perform(post("/api/module2MenuItems")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(module2MenuItem)))
                .andExpect(status().isCreated());

        // Validate the Module2MenuItem in the database
        List<Module2MenuItem> module2MenuItems = module2MenuItemRepository.findAll();
        assertThat(module2MenuItems).hasSize(databaseSizeBeforeCreate + 1);
        Module2MenuItem testModule2MenuItem = module2MenuItems.get(module2MenuItems.size() - 1);
        assertThat(testModule2MenuItem.getPriority()).isEqualTo(DEFAULT_PRIORITY);
    }

    @Test
    @Transactional
    public void getAllModule2MenuItems() throws Exception {
        // Initialize the database
        module2MenuItemRepository.saveAndFlush(module2MenuItem);

        // Get all the module2MenuItems
        restModule2MenuItemMockMvc.perform(get("/api/module2MenuItems?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(module2MenuItem.getId().intValue())))
                .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)));
    }

    @Test
    @Transactional
    public void getModule2MenuItem() throws Exception {
        // Initialize the database
        module2MenuItemRepository.saveAndFlush(module2MenuItem);

        // Get the module2MenuItem
        restModule2MenuItemMockMvc.perform(get("/api/module2MenuItems/{id}", module2MenuItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(module2MenuItem.getId().intValue()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY));
    }

    @Test
    @Transactional
    public void getNonExistingModule2MenuItem() throws Exception {
        // Get the module2MenuItem
        restModule2MenuItemMockMvc.perform(get("/api/module2MenuItems/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModule2MenuItem() throws Exception {
        // Initialize the database
        module2MenuItemRepository.saveAndFlush(module2MenuItem);

		int databaseSizeBeforeUpdate = module2MenuItemRepository.findAll().size();

        // Update the module2MenuItem
        module2MenuItem.setPriority(UPDATED_PRIORITY);

        restModule2MenuItemMockMvc.perform(put("/api/module2MenuItems")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(module2MenuItem)))
                .andExpect(status().isOk());

        // Validate the Module2MenuItem in the database
        List<Module2MenuItem> module2MenuItems = module2MenuItemRepository.findAll();
        assertThat(module2MenuItems).hasSize(databaseSizeBeforeUpdate);
        Module2MenuItem testModule2MenuItem = module2MenuItems.get(module2MenuItems.size() - 1);
        assertThat(testModule2MenuItem.getPriority()).isEqualTo(UPDATED_PRIORITY);
    }

    @Test
    @Transactional
    public void deleteModule2MenuItem() throws Exception {
        // Initialize the database
        module2MenuItemRepository.saveAndFlush(module2MenuItem);

		int databaseSizeBeforeDelete = module2MenuItemRepository.findAll().size();

        // Get the module2MenuItem
        restModule2MenuItemMockMvc.perform(delete("/api/module2MenuItems/{id}", module2MenuItem.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Module2MenuItem> module2MenuItems = module2MenuItemRepository.findAll();
        assertThat(module2MenuItems).hasSize(databaseSizeBeforeDelete - 1);
    }
}
