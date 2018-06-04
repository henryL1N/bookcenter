package bookcenter.service.impl;

import bookcenter.domain.Employee;
import bookcenter.domain.OrderItem;
import bookcenter.domain.PurchaseOrder;
import bookcenter.domain.SalesOrder;
import bookcenter.repository.EmployeeRepository;
import bookcenter.repository.PurchaseOrderRepository;
import bookcenter.repository.SalesOrderRepository;
import bookcenter.service.ProfitReportService;
import bookcenter.service.dto.ProfitReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
@Service
@Transactional
public class ProfitReportServiceImpl implements ProfitReportService {

    private final Logger log = LoggerFactory.getLogger(ProfitReportServiceImpl.class);

    private final SalesOrderRepository salesOrderRepository;

    private final EmployeeRepository employeeRepository;

    public ProfitReportServiceImpl(SalesOrderRepository salesOrderRepository,
                                   EmployeeRepository employeeRepository) {
        this.salesOrderRepository = salesOrderRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Set<ProfitReportDTO> getAll(Integer year, Integer month) {
        Set<ProfitReportDTO> profitReportDTOS = new HashSet<>();
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
            return profitReportDTOS;
        }

        SalesOrder first = salesOrderRepository.findFirstByOrderByDateAsc();
        SalesOrder last = salesOrderRepository.findFirstByOrderByDateDesc();
        if (null == first ||
            null == last) {
            return profitReportDTOS;
        }
        if (to.isBefore(first.getDate()) ||
            from.isAfter(last.getDate())) {
            return profitReportDTOS;
        }
        if (from.isBefore(first.getDate())) {
            from = first.getDate();
        }
        if (to.isAfter(last.getDate())) {
            to = last.getDate();
        }
        Integer reportYear = LocalDateTime.ofInstant(from, ZoneId.systemDefault()).getYear();
        Integer reportMonth = LocalDateTime.ofInstant(from, ZoneId.systemDefault()).getMonthValue();
        BigDecimal totalSalary = new BigDecimal(0);
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            totalSalary = totalSalary.add(employee.getSalary());
        }
        Long id = 1L;
        do {
            ProfitReportDTO profitReportDTO = new ProfitReportDTO();
            profitReportDTO.setId(id);
            id++;
            profitReportDTO.setYear(reportYear);
            profitReportDTO.setMonth(reportMonth);
            Instant f, t;
            LocalDateTime ldt = LocalDateTime.of(reportYear, reportMonth, 1, 0, 0, 0);
            f = ldt.atZone(ZoneId.systemDefault()).toInstant();
            t = ldt.plus(Period.ofMonths(1)).atZone(ZoneId.systemDefault()).toInstant();
            List<SalesOrder> salesOrders = salesOrderRepository.findAllByDateBetween(f, t);
//            log.debug("salesOrderRepository");
            BigDecimal profit = new BigDecimal(0);
            for (SalesOrder salesOrder : salesOrders) {
                profit = profit.add(salesOrder.getTotalAmount());
//                log.debug("order-for");
                for (OrderItem orderItem : salesOrder.getOrderItems()) {
                    profit = profit.subtract(
                        orderItem.getBook().getCost().multiply(
                            BigDecimal.valueOf(
                                orderItem.getQuantity()
                            )
                        )
                    );
//                    log.debug("item");
                }
//                log.debug("order");
            }
            profit = profit.subtract(totalSalary);
            profitReportDTO.setProfit(profit);
            profitReportDTOS.add(profitReportDTO);
//            log.debug("month");
            if (t.equals(to) || t.isAfter(to)) {
                break;
            }else{
                reportMonth++;
                if (reportMonth > 12) {
                    reportYear++;
                    reportMonth=1;
                }
            }


        } while (true);

        return profitReportDTOS;
    }
}
