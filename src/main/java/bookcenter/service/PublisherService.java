package bookcenter.service;

import bookcenter.domain.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Publisher.
 */
public interface PublisherService {

    /**
     * Save a publisher.
     *
     * @param publisher the entity to save
     * @return the persisted entity
     */
    Publisher save(Publisher publisher);

    /**
     * Get all the publishers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Publisher> findAll(Pageable pageable);

    /**
     * Get the "id" publisher.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Publisher findOne(Long id);

    /**
     * Delete the "id" publisher.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
