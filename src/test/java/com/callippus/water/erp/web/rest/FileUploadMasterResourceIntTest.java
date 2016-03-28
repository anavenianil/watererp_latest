package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.FileUploadMaster;
import com.callippus.water.erp.repository.FileUploadMasterRepository;

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
import org.springframework.util.Base64Utils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the FileUploadMasterResource REST controller.
 *
 * @see FileUploadMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class FileUploadMasterResourceIntTest {


    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";
    
    private static final String DEFAULT_TEXT_FILE = "";
    private static final String UPDATED_TEXT_FILE = "";

    private static final byte[] DEFAULT_BINARY_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BINARY_FILE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BINARY_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BINARY_FILE_CONTENT_TYPE = "image/png";

    @Inject
    private FileUploadMasterRepository fileUploadMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restFileUploadMasterMockMvc;

    private FileUploadMaster fileUploadMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FileUploadMasterResource fileUploadMasterResource = new FileUploadMasterResource();
        ReflectionTestUtils.setField(fileUploadMasterResource, "fileUploadMasterRepository", fileUploadMasterRepository);
        this.restFileUploadMasterMockMvc = MockMvcBuilders.standaloneSetup(fileUploadMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        fileUploadMaster = new FileUploadMaster();
        fileUploadMaster.setPhoto(DEFAULT_PHOTO);
        fileUploadMaster.setPhotoContentType(DEFAULT_PHOTO_CONTENT_TYPE);
        fileUploadMaster.setTextFile(DEFAULT_TEXT_FILE);
        fileUploadMaster.setBinaryFile(DEFAULT_BINARY_FILE);
        fileUploadMaster.setBinaryFileContentType(DEFAULT_BINARY_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createFileUploadMaster() throws Exception {
        int databaseSizeBeforeCreate = fileUploadMasterRepository.findAll().size();

        // Create the FileUploadMaster

        restFileUploadMasterMockMvc.perform(post("/api/fileUploadMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fileUploadMaster)))
                .andExpect(status().isCreated());

        // Validate the FileUploadMaster in the database
        List<FileUploadMaster> fileUploadMasters = fileUploadMasterRepository.findAll();
        assertThat(fileUploadMasters).hasSize(databaseSizeBeforeCreate + 1);
        FileUploadMaster testFileUploadMaster = fileUploadMasters.get(fileUploadMasters.size() - 1);
        assertThat(testFileUploadMaster.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testFileUploadMaster.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testFileUploadMaster.getTextFile()).isEqualTo(DEFAULT_TEXT_FILE);
        assertThat(testFileUploadMaster.getBinaryFile()).isEqualTo(DEFAULT_BINARY_FILE);
        assertThat(testFileUploadMaster.getBinaryFileContentType()).isEqualTo(DEFAULT_BINARY_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllFileUploadMasters() throws Exception {
        // Initialize the database
        fileUploadMasterRepository.saveAndFlush(fileUploadMaster);

        // Get all the fileUploadMasters
        restFileUploadMasterMockMvc.perform(get("/api/fileUploadMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(fileUploadMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
                .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
                .andExpect(jsonPath("$.[*].textFile").value(hasItem(DEFAULT_TEXT_FILE.toString())))
                .andExpect(jsonPath("$.[*].binaryFileContentType").value(hasItem(DEFAULT_BINARY_FILE_CONTENT_TYPE)))
                .andExpect(jsonPath("$.[*].binaryFile").value(hasItem(Base64Utils.encodeToString(DEFAULT_BINARY_FILE))));
    }

    @Test
    @Transactional
    public void getFileUploadMaster() throws Exception {
        // Initialize the database
        fileUploadMasterRepository.saveAndFlush(fileUploadMaster);

        // Get the fileUploadMaster
        restFileUploadMasterMockMvc.perform(get("/api/fileUploadMasters/{id}", fileUploadMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(fileUploadMaster.getId().intValue()))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.textFile").value(DEFAULT_TEXT_FILE.toString()))
            .andExpect(jsonPath("$.binaryFileContentType").value(DEFAULT_BINARY_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.binaryFile").value(Base64Utils.encodeToString(DEFAULT_BINARY_FILE)));
    }

    @Test
    @Transactional
    public void getNonExistingFileUploadMaster() throws Exception {
        // Get the fileUploadMaster
        restFileUploadMasterMockMvc.perform(get("/api/fileUploadMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFileUploadMaster() throws Exception {
        // Initialize the database
        fileUploadMasterRepository.saveAndFlush(fileUploadMaster);

		int databaseSizeBeforeUpdate = fileUploadMasterRepository.findAll().size();

        // Update the fileUploadMaster
        fileUploadMaster.setPhoto(UPDATED_PHOTO);
        fileUploadMaster.setPhotoContentType(UPDATED_PHOTO_CONTENT_TYPE);
        fileUploadMaster.setTextFile(UPDATED_TEXT_FILE);
        fileUploadMaster.setBinaryFile(UPDATED_BINARY_FILE);
        fileUploadMaster.setBinaryFileContentType(UPDATED_BINARY_FILE_CONTENT_TYPE);

        restFileUploadMasterMockMvc.perform(put("/api/fileUploadMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fileUploadMaster)))
                .andExpect(status().isOk());

        // Validate the FileUploadMaster in the database
        List<FileUploadMaster> fileUploadMasters = fileUploadMasterRepository.findAll();
        assertThat(fileUploadMasters).hasSize(databaseSizeBeforeUpdate);
        FileUploadMaster testFileUploadMaster = fileUploadMasters.get(fileUploadMasters.size() - 1);
        assertThat(testFileUploadMaster.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testFileUploadMaster.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testFileUploadMaster.getTextFile()).isEqualTo(UPDATED_TEXT_FILE);
        assertThat(testFileUploadMaster.getBinaryFile()).isEqualTo(UPDATED_BINARY_FILE);
        assertThat(testFileUploadMaster.getBinaryFileContentType()).isEqualTo(UPDATED_BINARY_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void deleteFileUploadMaster() throws Exception {
        // Initialize the database
        fileUploadMasterRepository.saveAndFlush(fileUploadMaster);

		int databaseSizeBeforeDelete = fileUploadMasterRepository.findAll().size();

        // Get the fileUploadMaster
        restFileUploadMasterMockMvc.perform(delete("/api/fileUploadMasters/{id}", fileUploadMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<FileUploadMaster> fileUploadMasters = fileUploadMasterRepository.findAll();
        assertThat(fileUploadMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}
