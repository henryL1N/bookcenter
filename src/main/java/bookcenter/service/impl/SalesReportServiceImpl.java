package bookcenter.service.impl;

import bookcenter.domain.*;
import bookcenter.repository.DepartmentRepository;
import bookcenter.repository.EmployeeRepository;
import bookcenter.repository.SalesOrderRepository;
import bookcenter.service.SalesReportService;
import bookcenter.service.dto.SalesReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
public class SalesReportServiceImpl implements SalesReportService {

    private final Logger log = LoggerFactory.getLogger(SalesReportServiceImpl.class);

    private final SalesOrderRepository salesOrderRepository;

    private final DepartmentRepository departmentRepository;

    public SalesReportServiceImpl(SalesOrderRepository salesOrderRepository,
                                  DepartmentRepository departmentRepository) {
        this.salesOrderRepository = salesOrderRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Set<SalesReportDTO> getSalesReports(Long departmentId,
                                               Integer year,
                                               Integer month) {
        Set<SalesReportDTO> salesReportDTOS = new HashSet<>();
        Department department = departmentRepository.findOne(departmentId);
        if (null == department) {
            return salesReportDTOS;
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
            return salesReportDTOS;
        }

        SalesOrder first = salesOrderRepository.findFirstByOrderByDateAsc();
        SalesOrder last = salesOrderRepository.findFirstByOrderByDateDesc();
        if (null == first ||
            null == last) {
            return salesReportDTOS;
        }
        if (to.isBefore(first.getDate()) ||
            from.isAfter(last.getDate())) {
            return salesReportDTOS;
        }
        if (from.isBefore(first.getDate())) {
            from = first.getDate();
        }
        if (to.isAfter(last.getDate())) {
            to = last.getDate();
        }
        Integer reportYear = LocalDateTime.ofInstant(from, ZoneId.systemDefault()).getYear();
        Integer reportMonth = LocalDateTime.ofInstant(from, ZoneId.systemDefault()).getMonthValue();


        List<SalesOrder> salesOrders = salesOrderRepository.findAllByDateBetweenAndSellerIn(from, to, department.getEmployees());

        Long id = 1L;

        for (SalesOrder salesOrder : salesOrders) {
            for (OrderItem orderItem : salesOrder.getOrderItems()) {
                Boolean bookExist = false;
                for (SalesReportDTO salesReportDTO : salesReportDTOS) {
                    if (salesReportDTO.getBookId().equals(orderItem.getBook().getId())) {
                        bookExist=true;
                        salesReportDTO.setQuantity(salesReportDTO.getQuantity() + orderItem.getQuantity());
                        break;
                    }
                }
                if (!bookExist) {
                    SalesReportDTO salesReportDTO=new SalesReportDTO();
                    salesReportDTO.setId(id);
                    id++;
                    salesReportDTO.setBookId(orderItem.getBook().getId());
                    salesReportDTO.setBookName(orderItem.getBook().getName());
                    salesReportDTO.setQuantity(orderItem.getQuantity());
                    salesReportDTOS.add(salesReportDTO);
                }
            }
        }


        return salesReportDTOS;
    }
}
