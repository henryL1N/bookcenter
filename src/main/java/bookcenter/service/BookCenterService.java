package bookcenter.service;

import bookcenter.service.dto.BookCenterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing BookCenter.
 */
public interface BookCenterService {

    /**
     * Save a bookCenter.
     *
     * @param bookCenterDTO the entity to save
     * @return the persisted entity
     */
    BookCenterDTO save(BookCenterDTO bookCenterDTO);

    /**
     * Get all the bookCenters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BookCenterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" bookCenter.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BookCenterDTO findOne(Long id);

    /**
     * Delete the "id" bookCenter.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
