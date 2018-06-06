package bookcenter.web.rest;

import bookcenter.service.StockReportService;
import bookcenter.service.dto.StockReportDTO;
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
public class StockReportResource {

    private final Logger log = LoggerFactory.getLogger(StockReportResource.class);

    private static final String VIEW_NAME = "stockReport";

    private final StockReportService stockReportService;

    public StockReportResource(StockReportService stockReportService) {
        this.stockReportService = stockReportService;
    }

    @GetMapping("/stock-report/{departmentId}/{year}/{month}")
    public ResponseEntity<Set<StockReportDTO>> getAllStockReport(@PathVariable Long departmentId,
                                                                  @PathVariable Integer year,
                                                                  @PathVariable Integer month) {
        log.debug("REST request to get stock report by Department : {} in {}/{}", departmentId, year, month);
        Set<StockReportDTO> stockReportDTOS = stockReportService.getStockReports(departmentId, year, month);
        return new ResponseEntity<>(stockReportDTOS, HttpStatus.OK);

    }
}
