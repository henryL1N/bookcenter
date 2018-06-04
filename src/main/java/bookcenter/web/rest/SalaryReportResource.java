package bookcenter.web.rest;

import bookcenter.service.SalaryReportService;
import bookcenter.service.dto.SalaryReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
@RestController
@RequestMapping("/api")
public class SalaryReportResource {

    private final Logger log = LoggerFactory.getLogger(SalaryReportResource.class);

    private static final String VIEW_NAME = "salaryReport";

    private final SalaryReportService salaryReportService;

    public SalaryReportResource(SalaryReportService salaryReportService) {
        this.salaryReportService = salaryReportService;
    }

    @GetMapping("/salary-report")
    public ResponseEntity<Set<SalaryReportDTO>> getAllSalaryReport(@RequestParam(required = false) Integer year,
                                                                   @RequestParam(required = false) Integer month) {
        log.debug("REST request to get salary report");
        Set<SalaryReportDTO> salaryReportDTOS = salaryReportService.getAll(year, month);
        return new ResponseEntity<>(salaryReportDTOS, HttpStatus.OK);
    }
}
