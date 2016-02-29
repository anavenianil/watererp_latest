package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.FileNumber;
import com.callippus.water.erp.repository.FileNumberRepository;

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
 * Test class for the FileNumberResource REST controller.
 *
 * @see FileNumberResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class FileNumberResourceIntTest {

    private static final String DEFAULT_FILE_NO = "AAAAA";
    private static final String UPDATED_FILE_NO = "BBBBB";

    @Inject
    private FileNumberRepository fileNumberRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restFileNumberMockMvc;

    private FileNumber fileNumber;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FileNumberResource fileNumberResource = new FileNumberResource();
        ReflectionTestUtils.setField(fileNumberResource, "fileNumberRepository", fileNumberRepository);
        this.restFileNumberMockMvc = MockMvcBuilders.standaloneSetup(fileNumberResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        fileNumber = new FileNumber();
        fileNumber.setFileNo(DEFAULT_FILE_NO);
    }

    @Test
    @Transactional
    public void createFileNumber() throws Exception {
        int databaseSizeBeforeCreate = fileNumberRepository.findAll().size();

        // Create the FileNumber

        restFileNumberMockMvc.perform(post("/api/fileNumbers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fileNumber)))
                .andExpect(status().isCreated());

        // Validate the FileNumber in the database
        List<FileNumber> fileNumbers = fileNumberRepository.findAll();
        assertThat(fileNumbers).hasSize(databaseSizeBeforeCreate + 1);
        FileNumber testFileNumber = fileNumbers.get(fileNumbers.size() - 1);
        assertThat(testFileNumber.getFileNo()).isEqualTo(DEFAULT_FILE_NO);
    }

    @Test
    @Transactional
    public void checkFileNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fileNumberRepository.findAll().size();
        // set the field null
        fileNumber.setFileNo(null);

        // Create the FileNumber, which fails.

        restFileNumberMockMvc.perform(post("/api/fileNumbers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fileNumber)))
                .andExpect(status().isBadRequest());

        List<FileNumber> fileNumbers = fileNumberRepository.findAll();
        assertThat(fileNumbers).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFileNumbers() throws Exception {
        // Initialize the database
        fileNumberRepository.saveAndFlush(fileNumber);

        // Get all the fileNumbers
        restFileNumberMockMvc.perform(get("/api/fileNumbers?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(fileNumber.getId().intValue())))
                .andExpect(jsonPath("$.[*].fileNo").value(hasItem(DEFAULT_FILE_NO.toString())));
    }

    @Test
    @Transactional
    public void getFileNumber() throws Exception {
        // Initialize the database
        fileNumberRepository.saveAndFlush(fileNumber);

        // Get the fileNumber
        restFileNumberMockMvc.perform(get("/api/fileNumbers/{id}", fileNumber.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(fileNumber.getId().intValue()))
            .andExpect(jsonPath("$.fileNo").value(DEFAULT_FILE_NO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFileNumber() throws Exception {
        // Get the fileNumber
        restFileNumberMockMvc.perform(get("/api/fileNumbers/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFileNumber() throws Exception {
        // Initialize the database
        fileNumberRepository.saveAndFlush(fileNumber);

		int databaseSizeBeforeUpdate = fileNumberRepository.findAll().size();

        // Update the fileNumber
        fileNumber.setFileNo(UPDATED_FILE_NO);

        restFileNumberMockMvc.perform(put("/api/fileNumbers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fileNumber)))
                .andExpect(status().isOk());

        // Validate the FileNumber in the database
        List<FileNumber> fileNumbers = fileNumberRepository.findAll();
        assertThat(fileNumbers).hasSize(databaseSizeBeforeUpdate);
        FileNumber testFileNumber = fileNumbers.get(fileNumbers.size() - 1);
        assertThat(testFileNumber.getFileNo()).isEqualTo(UPDATED_FILE_NO);
    }

    @Test
    @Transactional
    public void deleteFileNumber() throws Exception {
        // Initialize the database
        fileNumberRepository.saveAndFlush(fileNumber);

		int databaseSizeBeforeDelete = fileNumberRepository.findAll().size();

        // Get the fileNumber
        restFileNumberMockMvc.perform(delete("/api/fileNumbers/{id}", fileNumber.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<FileNumber> fileNumbers = fileNumberRepository.findAll();
        assertThat(fileNumbers).hasSize(databaseSizeBeforeDelete - 1);
    }
}
