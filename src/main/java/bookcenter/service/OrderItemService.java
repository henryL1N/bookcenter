package bookcenter.service;

import bookcenter.domain.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing OrderItem.
 */
public interface OrderItemService {

    /**
     * Save a orderItem.
     *
     * @param orderItem the entity to save
     * @return the persisted entity
     */
    OrderItem save(OrderItem orderItem);

    /**
     * Get all the orderItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderItem> findAll(Pageable pageable);

    /**
     * Get the "id" orderItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderItem findOne(Long id);

    /**
     * Delete the "id" orderItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
