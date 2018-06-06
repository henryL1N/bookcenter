package bookcenter.service;

import bookcenter.service.dto.ExpenseReportDTO;

import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
public interface ExpenseReportService {

    Set<ExpenseReportDTO> getExpenseReports(Integer year,
                                        Integer month);
}
