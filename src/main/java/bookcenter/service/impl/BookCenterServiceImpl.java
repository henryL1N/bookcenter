package bookcenter.service.impl;

import bookcenter.service.BookCenterService;
import bookcenter.domain.BookCenter;
import bookcenter.repository.BookCenterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing BookCenter.
 */
@Service
@Transactional
public class BookCenterServiceImpl implements BookCenterService {

    private final Logger log = LoggerFactory.getLogger(BookCenterServiceImpl.class);

    private final BookCenterRepository bookCenterRepository;

    public BookCenterServiceImpl(BookCenterRepository bookCenterRepository) {
        this.bookCenterRepository = bookCenterRepository;
    }

    /**
     * Save a bookCenter.
     *
     * @param bookCenter the entity to save
     * @return the persisted entity
     */
    @Override
    public BookCenter save(BookCenter bookCenter) {
        log.debug("Request to save BookCenter : {}", bookCenter);
        return bookCenterRepository.save(bookCenter);
    }

    /**
     * Get all the bookCenters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BookCenter> findAll(Pageable pageable) {
        log.debug("Request to get all BookCenters");
        return bookCenterRepository.findAll(pageable);
    }

    /**
     * Get one bookCenter by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BookCenter findOne(Long id) {
        log.debug("Request to get BookCenter : {}", id);
        return bookCenterRepository.findOne(id);
    }

    /**
     * Delete the bookCenter by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BookCenter : {}", id);
        bookCenterRepository.delete(id);
    }
}
