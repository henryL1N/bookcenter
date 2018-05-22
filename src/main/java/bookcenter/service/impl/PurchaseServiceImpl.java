package bookcenter.service.impl;

import bookcenter.domain.OrderItem;
import bookcenter.domain.PurchaseOrder;
import bookcenter.repository.BookRepository;
import bookcenter.repository.OrderItemRepository;
import bookcenter.repository.PurchaseOrderRepository;
import bookcenter.service.PurchaseService;
import bookcenter.service.dto.PurchaseDTO;
import bookcenter.service.mapper.PurchaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author Henry Lin badcop@163.com
 */
@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final OrderItemRepository orderItemRepository;

    private final PurchaseMapper purchaseMapper;

    public PurchaseServiceImpl(PurchaseOrderRepository purchaseOrderRepository, OrderItemRepository orderItemRepository, PurchaseMapper purchaseMapper) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    public PurchaseDTO save(PurchaseDTO purchaseDTO) {
        log.debug("Request to save Purchase : {}", purchaseDTO);
        PurchaseOrder purchaseOrder = purchaseMapper.toEntity(purchaseDTO);
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        for (OrderItem orderItem : purchaseOrder.getOrderItems()) {
            orderItem.setPurchaseOrder(purchaseOrder);
        }
        orderItemRepository.save(purchaseOrder.getOrderItems());
        return purchaseMapper.toDto(purchaseOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PurchaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Purchases");
        return purchaseOrderRepository.findAll(pageable)
            .map(purchaseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseDTO findOne(Long id) {
        log.debug("Request to get Purchase : {}", id);
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(id);
        return purchaseMapper.toDto(purchaseOrder);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Purchase : {}", id);
        purchaseOrderRepository.delete(id);
    }
}
