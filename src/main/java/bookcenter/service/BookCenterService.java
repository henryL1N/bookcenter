package bookcenter.service;

import bookcenter.domain.BookCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing BookCenter.
 */
public interface BookCenterService {

    /**
     * Save a bookCenter.
     *
     * @param bookCenter the entity to save
     * @return the persisted entity
     */
    BookCenter save(BookCenter bookCenter);

    /**
     * Get all the bookCenters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BookCenter> findAll(Pageable pageable);

    /**
     * Get the "id" bookCenter.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BookCenter findOne(Long id);

    /**
     * Delete the "id" bookCenter.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
