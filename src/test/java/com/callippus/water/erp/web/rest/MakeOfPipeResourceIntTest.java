package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.MakeOfPipe;
import com.callippus.water.erp.repository.MakeOfPipeRepository;

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
 * Test class for the MakeOfPipeResource REST controller.
 *
 * @see MakeOfPipeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MakeOfPipeResourceIntTest {

    private static final String DEFAULT_MAKE_NAME = "AAAAA";
    private static final String UPDATED_MAKE_NAME = "BBBBB";

    @Inject
    private MakeOfPipeRepository makeOfPipeRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMakeOfPipeMockMvc;

    private MakeOfPipe makeOfPipe;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MakeOfPipeResource makeOfPipeResource = new MakeOfPipeResource();
        ReflectionTestUtils.setField(makeOfPipeResource, "makeOfPipeRepository", makeOfPipeRepository);
        this.restMakeOfPipeMockMvc = MockMvcBuilders.standaloneSetup(makeOfPipeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        makeOfPipe = new MakeOfPipe();
        makeOfPipe.setMakeName(DEFAULT_MAKE_NAME);
    }

    @Test
    @Transactional
    public void createMakeOfPipe() throws Exception {
        int databaseSizeBeforeCreate = makeOfPipeRepository.findAll().size();

        // Create the MakeOfPipe

        restMakeOfPipeMockMvc.perform(post("/api/makeOfPipes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(makeOfPipe)))
                .andExpect(status().isCreated());

        // Validate the MakeOfPipe in the database
        List<MakeOfPipe> makeOfPipes = makeOfPipeRepository.findAll();
        assertThat(makeOfPipes).hasSize(databaseSizeBeforeCreate + 1);
        MakeOfPipe testMakeOfPipe = makeOfPipes.get(makeOfPipes.size() - 1);
        assertThat(testMakeOfPipe.getMakeName()).isEqualTo(DEFAULT_MAKE_NAME);
    }

    @Test
    @Transactional
    public void checkMakeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = makeOfPipeRepository.findAll().size();
        // set the field null
        makeOfPipe.setMakeName(null);

        // Create the MakeOfPipe, which fails.

        restMakeOfPipeMockMvc.perform(post("/api/makeOfPipes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(makeOfPipe)))
                .andExpect(status().isBadRequest());

        List<MakeOfPipe> makeOfPipes = makeOfPipeRepository.findAll();
        assertThat(makeOfPipes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMakeOfPipes() throws Exception {
        // Initialize the database
        makeOfPipeRepository.saveAndFlush(makeOfPipe);

        // Get all the makeOfPipes
        restMakeOfPipeMockMvc.perform(get("/api/makeOfPipes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(makeOfPipe.getId().intValue())))
                .andExpect(jsonPath("$.[*].makeName").value(hasItem(DEFAULT_MAKE_NAME.toString())));
    }

    @Test
    @Transactional
    public void getMakeOfPipe() throws Exception {
        // Initialize the database
        makeOfPipeRepository.saveAndFlush(makeOfPipe);

        // Get the makeOfPipe
        restMakeOfPipeMockMvc.perform(get("/api/makeOfPipes/{id}", makeOfPipe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(makeOfPipe.getId().intValue()))
            .andExpect(jsonPath("$.makeName").value(DEFAULT_MAKE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMakeOfPipe() throws Exception {
        // Get the makeOfPipe
        restMakeOfPipeMockMvc.perform(get("/api/makeOfPipes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMakeOfPipe() throws Exception {
        // Initialize the database
        makeOfPipeRepository.saveAndFlush(makeOfPipe);

		int databaseSizeBeforeUpdate = makeOfPipeRepository.findAll().size();

        // Update the makeOfPipe
        makeOfPipe.setMakeName(UPDATED_MAKE_NAME);

        restMakeOfPipeMockMvc.perform(put("/api/makeOfPipes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(makeOfPipe)))
                .andExpect(status().isOk());

        // Validate the MakeOfPipe in the database
        List<MakeOfPipe> makeOfPipes = makeOfPipeRepository.findAll();
        assertThat(makeOfPipes).hasSize(databaseSizeBeforeUpdate);
        MakeOfPipe testMakeOfPipe = makeOfPipes.get(makeOfPipes.size() - 1);
        assertThat(testMakeOfPipe.getMakeName()).isEqualTo(UPDATED_MAKE_NAME);
    }

    @Test
    @Transactional
    public void deleteMakeOfPipe() throws Exception {
        // Initialize the database
        makeOfPipeRepository.saveAndFlush(makeOfPipe);

		int databaseSizeBeforeDelete = makeOfPipeRepository.findAll().size();

        // Get the makeOfPipe
        restMakeOfPipeMockMvc.perform(delete("/api/makeOfPipes/{id}", makeOfPipe.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MakeOfPipe> makeOfPipes = makeOfPipeRepository.findAll();
        assertThat(makeOfPipes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
