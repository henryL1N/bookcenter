package bookcenter.service.impl;

import bookcenter.domain.Warehouse;
import bookcenter.repository.WarehouseRepository;
import bookcenter.service.StockItemService;
import bookcenter.domain.StockItem;
import bookcenter.repository.StockItemRepository;
import bookcenter.service.dto.StockItemDTO;
import bookcenter.service.mapper.StockItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing StockItem.
 */
@Service
@Transactional
public class StockItemServiceImpl implements StockItemService {

    private final Logger log = LoggerFactory.getLogger(StockItemServiceImpl.class);

    private final StockItemRepository stockItemRepository;

    private final WarehouseRepository warehouseRepository;

    private final StockItemMapper stockItemMapper;

    public StockItemServiceImpl(StockItemRepository stockItemRepository,
                                WarehouseRepository warehouseRepository,
                                StockItemMapper stockItemMapper) {
        this.stockItemRepository = stockItemRepository;
        this.warehouseRepository = warehouseRepository;
        this.stockItemMapper = stockItemMapper;
    }

    /**
     * Save a stockItem.
     *
     * @param stockItemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StockItemDTO save(StockItemDTO stockItemDTO) {
        log.debug("Request to save StockItem : {}", stockItemDTO);
        StockItem stockItem = stockItemMapper.toEntity(stockItemDTO);
        stockItem = stockItemRepository.save(stockItem);
        return stockItemMapper.toDto(stockItem);
    }

    /**
     * Get all the stockItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StockItems");
        return stockItemRepository.findAll(pageable)
            .map(stockItemMapper::toDto);
    }

    /**
     * Get one stockItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StockItemDTO findOne(Long id) {
        log.debug("Request to get StockItem : {}", id);
        StockItem stockItem = stockItemRepository.findOne(id);
        return stockItemMapper.toDto(stockItem);
    }

    /**
     * Delete the stockItem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockItem : {}", id);
        stockItemRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StockItemDTO> findAllByWarehouseId(Long id, Pageable pageable) {
        log.debug("Request to get all StockItems by Warehouse id");
        return stockItemRepository.findAllByWarehouseIdAndQuantityGreaterThan(id, 0L, pageable)
            .map(stockItemMapper::toDto);
    }
}
