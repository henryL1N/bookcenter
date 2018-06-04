package bookcenter.service.impl;

import bookcenter.domain.Employee;
import bookcenter.domain.OrderItem;
import bookcenter.domain.SalesOrder;
import bookcenter.repository.EmployeeRepository;
import bookcenter.repository.PurchaseOrderRepository;
import bookcenter.repository.SalesOrderRepository;
import bookcenter.service.SalaryReportService;
import bookcenter.service.dto.SalaryReportDTO;
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
public class SalaryReportServiceImpl implements SalaryReportService {

    private final Logger log = LoggerFactory.getLogger(SalaryReportServiceImpl.class);

    private final SalesOrderRepository salesOrderRepository;

    private final EmployeeRepository employeeRepository;

    public SalaryReportServiceImpl(SalesOrderRepository salesOrderRepository,
                                   EmployeeRepository employeeRepository) {
        this.salesOrderRepository = salesOrderRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Set<SalaryReportDTO> getAll(Integer year, Integer month) {
        Set<SalaryReportDTO> salaryReportDTOS = new HashSet<>();
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
            return salaryReportDTOS;
        }

        SalesOrder first = salesOrderRepository.findFirstByOrderByDateAsc();
        SalesOrder last = salesOrderRepository.findFirstByOrderByDateDesc();
        if (null == first ||
            null == last) {
            return salaryReportDTOS;
        }
        if (to.isBefore(first.getDate()) ||
            from.isAfter(last.getDate())) {
            return salaryReportDTOS;
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
            SalaryReportDTO salaryReportDTO = new SalaryReportDTO();
            salaryReportDTO.setId(id);
            id++;
            salaryReportDTO.setYear(reportYear);
            salaryReportDTO.setMonth(reportMonth);
            salaryReportDTO.setSalary(totalSalary);
            salaryReportDTOS.add(salaryReportDTO);
//            log.debug("month");
            LocalDateTime ldt = LocalDateTime.of(reportYear, reportMonth, 1, 0, 0, 0);
            Instant t=ldt.plus(Period.ofMonths(1)).atZone(ZoneId.systemDefault()).toInstant();
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

        return salaryReportDTOS;
    }
}
