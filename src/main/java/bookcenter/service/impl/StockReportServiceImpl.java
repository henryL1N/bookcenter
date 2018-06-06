package bookcenter.service.impl;

import bookcenter.domain.*;
import bookcenter.repository.*;
import bookcenter.service.StockReportService;
import bookcenter.service.dto.SalesReportDTO;
import bookcenter.service.dto.StockReportDTO;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
@Service
@Transactional
public class StockReportServiceImpl implements StockReportService {

    private final Logger log = LoggerFactory.getLogger(StockReportServiceImpl.class);

    private final WarehouseRepository warehouseRepository;

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final SalesOrderRepository salesOrderRepository;

    private final StockItemRepository stockItemRepository;

    public StockReportServiceImpl(WarehouseRepository warehouseRepository,
                                  PurchaseOrderRepository purchaseOrderRepository,
                                  SalesOrderRepository salesOrderRepository,
                                  StockItemRepository stockItemRepository) {
        this.warehouseRepository = warehouseRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.salesOrderRepository = salesOrderRepository;
        this.stockItemRepository = stockItemRepository;
    }

    @Override
    public Set<StockReportDTO> getStockReports(Long warehouseId,
                                               Integer year,
                                               Integer month) {
        Set<StockReportDTO> stockReportDTOS = new HashSet<>();
        Warehouse warehouse = warehouseRepository.findOne(warehouseId);
        if (null == warehouse) {
            return stockReportDTOS;
        }
        Instant from, to;
        try {
            if (null == year) {
                from = Instant.MIN;
                to = Instant.MAX;
            } else if (null == month) {
                LocalDateTime t = LocalDateTime.of(year, 1, 1, 0, 0, 0);
                from = t.atZone(ZoneId.systemDefault()).toInstant();
                to = t.plus(Period.ofYears(1)).atZone(ZoneId.systemDefault()).toInstant();
            } else {
                LocalDateTime t = LocalDateTime.of(year, month, 1, 0, 0, 0);
                from = t.atZone(ZoneId.systemDefault()).toInstant();
                to = t.plus(Period.ofMonths(1)).atZone(ZoneId.systemDefault()).toInstant();
            }
        } catch (Exception e) {
            return stockReportDTOS;
        }

        SalesOrder first = salesOrderRepository.findFirstByOrderByDateAsc();
        SalesOrder last = salesOrderRepository.findFirstByOrderByDateDesc();
        if (null == first ||
            null == last) {
            return stockReportDTOS;
        }
        if (to.isBefore(first.getDate()) ||
            from.isAfter(last.getDate())) {
            return stockReportDTOS;
        }
        if (from.isBefore(first.getDate())) {
            from = first.getDate();
        }
        if (to.isAfter(last.getDate())) {
            to = last.getDate();
        }
        Integer reportYear = LocalDateTime.ofInstant(from, ZoneId.systemDefault()).getYear();
        Integer reportMonth = LocalDateTime.ofInstant(from, ZoneId.systemDefault()).getMonthValue();

        List<StockItem> stockItems = stockItemRepository.findAllByWarehouseAndQuantityGreaterThan(warehouse, 0L);
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAllByWarehouseAndDateAfter(warehouse, to);
        List<SalesOrder> salesOrders = salesOrderRepository.findAllByWarehouseAndDateAfter(warehouse, to);

        Long id = 1L;
        for (StockItem stockItem : stockItems) {
            if(!stockItem.getQuantity().equals(0)) {
                StockReportDTO stockReportDTO = new StockReportDTO();
                stockReportDTO.setId(id);
                id++;
                stockReportDTO.setBookId(stockItem.getBook().getId());
                stockReportDTO.setBookName(stockItem.getBook().getName());
                stockReportDTO.setQuantity(stockItem.getQuantity());
                stockReportDTOS.add(stockReportDTO);
            }
        }

        for (SalesOrder salesOrder : salesOrders) {
            for (OrderItem orderItem : salesOrder.getOrderItems()) {
                Boolean bookExist=false;
                for (StockReportDTO stockReportDTO : stockReportDTOS) {
                    if (stockReportDTO.getBookId().equals(orderItem.getBook().getId())) {
                        bookExist=true;
                        stockReportDTO.setQuantity(stockReportDTO.getQuantity() + orderItem.getQuantity());
                        break;
                    }
                }
                if (!bookExist) {
                    StockReportDTO stockReportDTO = new StockReportDTO();
                    stockReportDTO.setBookId(orderItem.getBook().getId());
                    stockReportDTO.setBookName(orderItem.getBook().getName());
                    stockReportDTO.setQuantity(orderItem.getQuantity());
                    stockReportDTOS.add(stockReportDTO);
                }
            }
        }
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            for (OrderItem orderItem : purchaseOrder.getOrderItems()) {
                Boolean bookExist=false;
                for (StockReportDTO stockReportDTO : stockReportDTOS) {
                    if (stockReportDTO.getBookId().equals(orderItem.getBook().getId())) {
                        bookExist=true;
                        stockReportDTO.setQuantity(stockReportDTO.getQuantity() - orderItem.getQuantity());
                        if (stockReportDTO.getQuantity().equals(0L)) {
                            stockReportDTOS.remove(stockReportDTO);
                        } else if (stockReportDTO.getQuantity() < 0L) {
                            log.debug("Impossible 1: {}", orderItem);
                        }
                        break;
                    }
                }
                if (!bookExist) {
                    log.debug("Impossible 2: {}",orderItem);
                }
            }
        }


        return stockReportDTOS;
    }
}
