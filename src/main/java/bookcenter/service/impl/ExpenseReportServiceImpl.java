package bookcenter.service.impl;

import bookcenter.domain.Department;
import bookcenter.domain.Employee;
import bookcenter.repository.DepartmentRepository;
import bookcenter.repository.EmployeeRepository;
import bookcenter.service.ExpenseReportService;
import bookcenter.service.dto.ExpenseReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
@Service
@Transactional
public class ExpenseReportServiceImpl implements ExpenseReportService {

    private final Logger log = LoggerFactory.getLogger(ExpenseReportServiceImpl.class);

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    public ExpenseReportServiceImpl(DepartmentRepository departmentRepository,
                                    EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Set<ExpenseReportDTO> getExpenseReports(Integer year,
                                               Integer month) {
        Set<ExpenseReportDTO> expenseReportDTOS = new HashSet<>();

        Long id = 1L;

        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            BigDecimal totalSalary=new BigDecimal(0);
            for (Employee employee : department.getEmployees()) {
                totalSalary = totalSalary.add(employee.getSalary());
            }
            ExpenseReportDTO expenseReportDTO = new ExpenseReportDTO();
            expenseReportDTO.setId(id);
            id++;
            expenseReportDTO.setDepartmentId(department.getId());
            expenseReportDTO.setDepartmentName(department.getName());
            expenseReportDTO.setExpense(totalSalary);
            expenseReportDTOS.add(expenseReportDTO);
        }
        List<Employee> employees=employeeRepository.findAll();
        BigDecimal totalSalary=new BigDecimal(0);
        for (Employee employee : employees) {
            totalSalary = totalSalary.add(employee.getSalary());
        }
        ExpenseReportDTO expenseReportDTO = new ExpenseReportDTO();
        expenseReportDTO.setId(id);
        expenseReportDTO.setDepartmentId(0L);
        expenseReportDTO.setDepartmentName(departments.get(0).getBookCenter().getName());
        expenseReportDTO.setExpense(totalSalary);
        expenseReportDTOS.add(expenseReportDTO);


        return expenseReportDTOS;
    }
}
