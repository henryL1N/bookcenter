package bookcenter.web.rest;

import bookcenter.service.ExpenseReportService;
import bookcenter.service.dto.ExpenseReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
@RestController
@RequestMapping("/api")
public class ExpenseReportResource {

    private final Logger log = LoggerFactory.getLogger(ExpenseReportResource.class);

    private static final String VIEW_NAME = "expenseReport";

    private final ExpenseReportService expenseReportService;

    public ExpenseReportResource(ExpenseReportService expenseReportService) {
        this.expenseReportService = expenseReportService;
    }

    @GetMapping("/expense-report/{year}/{month}")
    public ResponseEntity<Set<ExpenseReportDTO>> getAllExpenseReport(@PathVariable Integer year,
                                                                  @PathVariable Integer month) {
        log.debug("REST request to get expense report in {}/{}", year, month);
        Set<ExpenseReportDTO> expenseReportDTOS = expenseReportService.getExpenseReports(year, month);
        return new ResponseEntity<>(expenseReportDTOS, HttpStatus.OK);

    }
}
