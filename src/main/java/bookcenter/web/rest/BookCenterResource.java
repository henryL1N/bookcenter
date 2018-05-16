package bookcenter.web.rest;

import com.codahale.metrics.annotation.Timed;
import bookcenter.service.BookCenterService;
import bookcenter.web.rest.errors.BadRequestAlertException;
import bookcenter.web.rest.util.HeaderUtil;
import bookcenter.web.rest.util.PaginationUtil;
import bookcenter.service.dto.BookCenterDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BookCenter.
 */
@RestController
@RequestMapping("/api")
public class BookCenterResource {

    private final Logger log = LoggerFactory.getLogger(BookCenterResource.class);

    private static final String ENTITY_NAME = "bookCenter";

    private final BookCenterService bookCenterService;

    public BookCenterResource(BookCenterService bookCenterService) {
        this.bookCenterService = bookCenterService;
    }

    /**
     * POST  /book-centers : Create a new bookCenter.
     *
     * @param bookCenterDTO the bookCenterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bookCenterDTO, or with status 400 (Bad Request) if the bookCenter has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/book-centers")
    @Timed
    public ResponseEntity<BookCenterDTO> createBookCenter(@Valid @RequestBody BookCenterDTO bookCenterDTO) throws URISyntaxException {
        log.debug("REST request to save BookCenter : {}", bookCenterDTO);
        if (bookCenterDTO.getId() != null) {
            throw new BadRequestAlertException("A new bookCenter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BookCenterDTO result = bookCenterService.save(bookCenterDTO);
        return ResponseEntity.created(new URI("/api/book-centers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /book-centers : Updates an existing bookCenter.
     *
     * @param bookCenterDTO the bookCenterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bookCenterDTO,
     * or with status 400 (Bad Request) if the bookCenterDTO is not valid,
     * or with status 500 (Internal Server Error) if the bookCenterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/book-centers")
    @Timed
    public ResponseEntity<BookCenterDTO> updateBookCenter(@Valid @RequestBody BookCenterDTO bookCenterDTO) throws URISyntaxException {
        log.debug("REST request to update BookCenter : {}", bookCenterDTO);
        if (bookCenterDTO.getId() == null) {
            return createBookCenter(bookCenterDTO);
        }
        BookCenterDTO result = bookCenterService.save(bookCenterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bookCenterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /book-centers : get all the bookCenters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bookCenters in body
     */
    @GetMapping("/book-centers")
    @Timed
    public ResponseEntity<List<BookCenterDTO>> getAllBookCenters(Pageable pageable) {
        log.debug("REST request to get a page of BookCenters");
        Page<BookCenterDTO> page = bookCenterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/book-centers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /book-centers/:id : get the "id" bookCenter.
     *
     * @param id the id of the bookCenterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bookCenterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/book-centers/{id}")
    @Timed
    public ResponseEntity<BookCenterDTO> getBookCenter(@PathVariable Long id) {
        log.debug("REST request to get BookCenter : {}", id);
        BookCenterDTO bookCenterDTO = bookCenterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bookCenterDTO));
    }

    /**
     * DELETE  /book-centers/:id : delete the "id" bookCenter.
     *
     * @param id the id of the bookCenterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/book-centers/{id}")
    @Timed
    public ResponseEntity<Void> deleteBookCenter(@PathVariable Long id) {
        log.debug("REST request to delete BookCenter : {}", id);
        bookCenterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
