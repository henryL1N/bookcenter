package bookcenter.service.impl;

import bookcenter.service.SalesOrderService;
import bookcenter.domain.SalesOrder;
import bookcenter.repository.SalesOrderRepository;
import bookcenter.service.dto.SalesOrderDTO;
import bookcenter.service.mapper.SalesOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing SalesOrder.
 */
@Service
@Transactional
public class SalesOrderServiceImpl implements SalesOrderService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderServiceImpl.class);

    private final SalesOrderRepository salesOrderRepository;

    private final SalesOrderMapper salesOrderMapper;

    public SalesOrderServiceImpl(SalesOrderRepository salesOrderRepository, SalesOrderMapper salesOrderMapper) {
        this.salesOrderRepository = salesOrderRepository;
        this.salesOrderMapper = salesOrderMapper;
    }

    /**
     * Save a salesOrder.
     *
     * @param salesOrderDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SalesOrderDTO save(SalesOrderDTO salesOrderDTO) {
        log.debug("Request to save SalesOrder : {}", salesOrderDTO);
        SalesOrder salesOrder = salesOrderMapper.toEntity(salesOrderDTO);
        salesOrder = salesOrderRepository.save(salesOrder);
        return salesOrderMapper.toDto(salesOrder);
    }

    /**
     * Get all the salesOrders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SalesOrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrders");
        return salesOrderRepository.findAll(pageable)
            .map(salesOrderMapper::toDto);
    }

    /**
     * Get one salesOrder by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SalesOrderDTO findOne(Long id) {
        log.debug("Request to get SalesOrder : {}", id);
        SalesOrder salesOrder = salesOrderRepository.findOne(id);
        return salesOrderMapper.toDto(salesOrder);
    }

    /**
     * Delete the salesOrder by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesOrder : {}", id);
        salesOrderRepository.delete(id);
    }
}
