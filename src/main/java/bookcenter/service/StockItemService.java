package bookcenter.service;

import bookcenter.service.dto.StockItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing StockItem.
 */
public interface StockItemService {

    /**
     * Save a stockItem.
     *
     * @param stockItemDTO the entity to save
     * @return the persisted entity
     */
    StockItemDTO save(StockItemDTO stockItemDTO);

    /**
     * Get all the stockItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StockItemDTO> findAll(Pageable pageable);

    /**
     * Get the "id" stockItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    StockItemDTO findOne(Long id);

    /**
     * Delete the "id" stockItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
