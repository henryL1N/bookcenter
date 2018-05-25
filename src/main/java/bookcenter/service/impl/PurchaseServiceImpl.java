package bookcenter.service.impl;

import bookcenter.domain.*;
import bookcenter.repository.EmployeeRepository;
import bookcenter.repository.OrderItemRepository;
import bookcenter.repository.PurchaseOrderRepository;
import bookcenter.repository.StockItemRepository;
import bookcenter.service.PurchaseService;
import bookcenter.service.UserService;
import bookcenter.service.dto.PurchaseDTO;
import bookcenter.service.mapper.PurchaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final OrderItemRepository orderItemRepository;

    private final StockItemRepository stockItemRepository;

    private final EmployeeRepository employeeRepository;

    private final UserService userService;

    private final PurchaseMapper purchaseMapper;

    public PurchaseServiceImpl(PurchaseOrderRepository purchaseOrderRepository,
                               OrderItemRepository orderItemRepository,
                               StockItemRepository stockItemRepository,
                               EmployeeRepository employeeRepository,
                               UserService userService,
                               PurchaseMapper purchaseMapper) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.stockItemRepository = stockItemRepository;
        this.employeeRepository =employeeRepository;
        this.userService=userService;
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    public PurchaseDTO save(PurchaseDTO purchaseDTO) {
        log.debug("Request to save Purchase : {}", purchaseDTO);
        PurchaseOrder purchaseOrder = purchaseMapper.toEntity(purchaseDTO);
        User currentUser=userService.getUserWithAuthorities().get();
        Employee currentEmployee = employeeRepository.findFirstByUser(currentUser);
        purchaseOrder.setBuyer(currentEmployee);
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        if (null == purchaseOrder.getOrderItems()) {
            purchaseOrder.setOrderItems(new HashSet<>());
        }
        for (OrderItem orderItem : purchaseOrder.getOrderItems()) {
            orderItem.setPurchaseOrder(purchaseOrder);
            orderItem.setSalesOrder(null);
            StockItem stockItem = stockItemRepository.findFirstByBookAndWarehouse(orderItem.getBook(),
                purchaseOrder.getWarehouse());
            if (null == stockItem) {
                stockItem = new StockItem();
                stockItem.setWarehouse(purchaseOrder.getWarehouse());
                stockItem.setBook(orderItem.getBook());
                stockItem.setQuantity(orderItem.getQuantity());
            } else {
                stockItem.setQuantity(stockItem.getQuantity() + orderItem.getQuantity());
            }
            stockItemRepository.save(stockItem);
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
