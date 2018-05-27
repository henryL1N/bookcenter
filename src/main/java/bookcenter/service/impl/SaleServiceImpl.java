package bookcenter.service.impl;

import bookcenter.domain.*;
import bookcenter.repository.EmployeeRepository;
import bookcenter.repository.OrderItemRepository;
import bookcenter.repository.SalesOrderRepository;
import bookcenter.repository.StockItemRepository;
import bookcenter.service.SaleService;
import bookcenter.service.UserService;
import bookcenter.service.dto.SaleDTO;
import bookcenter.service.mapper.SaleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

/**
 * @author Henry Lin badcop@163.com
 */
@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final Logger log = LoggerFactory.getLogger(SaleServiceImpl.class);

    private final SalesOrderRepository salesOrderRepository;

    private final OrderItemRepository orderItemRepository;

    private final StockItemRepository stockItemRepository;

    private final EmployeeRepository employeeRepository;

    private final UserService userService;

    private final SaleMapper saleMapper;

    public SaleServiceImpl(SalesOrderRepository salesOrderRepository,
                           OrderItemRepository orderItemRepository,
                           StockItemRepository stockItemRepository,
                           EmployeeRepository employeeRepository,
                           UserService userService,
                           SaleMapper saleMapper) {
        this.salesOrderRepository = salesOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.stockItemRepository = stockItemRepository;
        this.employeeRepository =employeeRepository;
        this.userService=userService;
        this.saleMapper = saleMapper;
    }

    @Override
    public SaleDTO save(SaleDTO saleDTO) {
        log.debug("Request to save Sale : {}", saleDTO);
        SalesOrder salesOrder = saleMapper.toEntity(saleDTO);
        User currentUser=userService.getUserWithAuthorities().get();
        Employee currentEmployee = employeeRepository.findFirstByUser(currentUser);
        salesOrder.setSeller(currentEmployee);
        salesOrder = salesOrderRepository.save(salesOrder);
        if (null == salesOrder.getOrderItems()) {
            salesOrder.setOrderItems(new HashSet<>());
        }
        for (OrderItem orderItem : salesOrder.getOrderItems()) {
            orderItem.setSalesOrder(salesOrder);
            orderItem.setPurchaseOrder(null);
            StockItem stockItem = stockItemRepository.findFirstByBookAndWarehouse(orderItem.getBook(),
                salesOrder.getWarehouse());
            stockItem.setQuantity(stockItem.getQuantity() - orderItem.getQuantity());
            stockItemRepository.save(stockItem);
        }
        orderItemRepository.save(salesOrder.getOrderItems());
        return saleMapper.toDto(salesOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SaleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sales");
        return salesOrderRepository.findAll(pageable)
            .map(saleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public SaleDTO findOne(Long id) {
        log.debug("Request to get Sale : {}", id);
        SalesOrder salesOrder = salesOrderRepository.findOne(id);
        return saleMapper.toDto(salesOrder);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sale : {}", id);
        salesOrderRepository.delete(id);
    }
}
