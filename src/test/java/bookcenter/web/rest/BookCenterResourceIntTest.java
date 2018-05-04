package bookcenter.web.rest;

import bookcenter.BookCenterApp;

import bookcenter.domain.BookCenter;
import bookcenter.domain.Employee;
import bookcenter.repository.BookCenterRepository;
import bookcenter.service.BookCenterService;
import bookcenter.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static bookcenter.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BookCenterResource REST controller.
 *
 * @see BookCenterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookCenterApp.class)
public class BookCenterResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    @Autowired
    private BookCenterRepository bookCenterRepository;

    @Autowired
    private BookCenterService bookCenterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBookCenterMockMvc;

    private BookCenter bookCenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BookCenterResource bookCenterResource = new BookCenterResource(bookCenterService);
        this.restBookCenterMockMvc = MockMvcBuilders.standaloneSetup(bookCenterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BookCenter createEntity(EntityManager em) {
        BookCenter bookCenter = new BookCenter()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS);
        // Add required entity
        Employee generalManager = EmployeeResourceIntTest.createEntity(em);
        em.persist(generalManager);
        em.flush();
        bookCenter.setGeneralManager(generalManager);
        return bookCenter;
    }

    @Before
    public void initTest() {
        bookCenter = createEntity(em);
    }

    @Test
    @Transactional
    public void createBookCenter() throws Exception {
        int databaseSizeBeforeCreate = bookCenterRepository.findAll().size();

        // Create the BookCenter
        restBookCenterMockMvc.perform(post("/api/book-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookCenter)))
            .andExpect(status().isCreated());

        // Validate the BookCenter in the database
        List<BookCenter> bookCenterList = bookCenterRepository.findAll();
        assertThat(bookCenterList).hasSize(databaseSizeBeforeCreate + 1);
        BookCenter testBookCenter = bookCenterList.get(bookCenterList.size() - 1);
        assertThat(testBookCenter.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBookCenter.getAddress()).isEqualTo(DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    public void createBookCenterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bookCenterRepository.findAll().size();

        // Create the BookCenter with an existing ID
        bookCenter.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookCenterMockMvc.perform(post("/api/book-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookCenter)))
            .andExpect(status().isBadRequest());

        // Validate the BookCenter in the database
        List<BookCenter> bookCenterList = bookCenterRepository.findAll();
        assertThat(bookCenterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookCenterRepository.findAll().size();
        // set the field null
        bookCenter.setName(null);

        // Create the BookCenter, which fails.

        restBookCenterMockMvc.perform(post("/api/book-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookCenter)))
            .andExpect(status().isBadRequest());

        List<BookCenter> bookCenterList = bookCenterRepository.findAll();
        assertThat(bookCenterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBookCenters() throws Exception {
        // Initialize the database
        bookCenterRepository.saveAndFlush(bookCenter);

        // Get all the bookCenterList
        restBookCenterMockMvc.perform(get("/api/book-centers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bookCenter.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())));
    }

    @Test
    @Transactional
    public void getBookCenter() throws Exception {
        // Initialize the database
        bookCenterRepository.saveAndFlush(bookCenter);

        // Get the bookCenter
        restBookCenterMockMvc.perform(get("/api/book-centers/{id}", bookCenter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bookCenter.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBookCenter() throws Exception {
        // Get the bookCenter
        restBookCenterMockMvc.perform(get("/api/book-centers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBookCenter() throws Exception {
        // Initialize the database
        bookCenterService.save(bookCenter);

        int databaseSizeBeforeUpdate = bookCenterRepository.findAll().size();

        // Update the bookCenter
        BookCenter updatedBookCenter = bookCenterRepository.findOne(bookCenter.getId());
        // Disconnect from session so that the updates on updatedBookCenter are not directly saved in db
        em.detach(updatedBookCenter);
        updatedBookCenter
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS);

        restBookCenterMockMvc.perform(put("/api/book-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBookCenter)))
            .andExpect(status().isOk());

        // Validate the BookCenter in the database
        List<BookCenter> bookCenterList = bookCenterRepository.findAll();
        assertThat(bookCenterList).hasSize(databaseSizeBeforeUpdate);
        BookCenter testBookCenter = bookCenterList.get(bookCenterList.size() - 1);
        assertThat(testBookCenter.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBookCenter.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingBookCenter() throws Exception {
        int databaseSizeBeforeUpdate = bookCenterRepository.findAll().size();

        // Create the BookCenter

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBookCenterMockMvc.perform(put("/api/book-centers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookCenter)))
            .andExpect(status().isCreated());

        // Validate the BookCenter in the database
        List<BookCenter> bookCenterList = bookCenterRepository.findAll();
        assertThat(bookCenterList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBookCenter() throws Exception {
        // Initialize the database
        bookCenterService.save(bookCenter);

        int databaseSizeBeforeDelete = bookCenterRepository.findAll().size();

        // Get the bookCenter
        restBookCenterMockMvc.perform(delete("/api/book-centers/{id}", bookCenter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BookCenter> bookCenterList = bookCenterRepository.findAll();
        assertThat(bookCenterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookCenter.class);
        BookCenter bookCenter1 = new BookCenter();
        bookCenter1.setId(1L);
        BookCenter bookCenter2 = new BookCenter();
        bookCenter2.setId(bookCenter1.getId());
        assertThat(bookCenter1).isEqualTo(bookCenter2);
        bookCenter2.setId(2L);
        assertThat(bookCenter1).isNotEqualTo(bookCenter2);
        bookCenter1.setId(null);
        assertThat(bookCenter1).isNotEqualTo(bookCenter2);
    }
}
