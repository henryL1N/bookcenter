package bookcenter.web.rest;

import bookcenter.service.SalesReportService;
import bookcenter.service.dto.SalesOrderDTO;
import bookcenter.service.dto.SalesReportDTO;
import bookcenter.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
@RestController
@RequestMapping("/api")
public class SalesReportResource {

    private final Logger log = LoggerFactory.getLogger(SalesReportResource.class);

    private static final String VIEW_NAME = "salesReport";

    private final SalesReportService salesReportService;

    public SalesReportResource(SalesReportService salesReportService) {
        this.salesReportService = salesReportService;
    }

    @GetMapping("/sales-report/{departmentId}/{year}/{month}")
    public ResponseEntity<Set<SalesReportDTO>> getAllSalesReport(@PathVariable Long departmentId,
                                                                  @PathVariable Integer year,
                                                                  @PathVariable Integer month) {
        log.debug("REST request to get sales report by Department : {} in {}/{}", departmentId, year, month);
        Set<SalesReportDTO> salesReportDTOS = salesReportService.getSalesReports(departmentId, year, month);
        return new ResponseEntity<>(salesReportDTOS, HttpStatus.OK);

    }
}
