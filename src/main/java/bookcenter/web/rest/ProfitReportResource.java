package bookcenter.web.rest;

import bookcenter.service.ProfitReportService;
import bookcenter.service.dto.ProfitReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
@RestController
@RequestMapping("/api")
public class ProfitReportResource {

    private final Logger log = LoggerFactory.getLogger(ProfitReportResource.class);

    private static final String VIEW_NAME = "profitReport";

    private final ProfitReportService profitReportService;

    public ProfitReportResource(ProfitReportService profitReportService) {
        this.profitReportService = profitReportService;
    }

    @GetMapping("/profit-report")
    public ResponseEntity<Set<ProfitReportDTO>> getAllProfitReport(@RequestParam(required = false) Integer year,
                                                                   @RequestParam(required = false) Integer month) {
        log.debug("REST request to get profit report");
        Set<ProfitReportDTO> profitReportDTOS = profitReportService.getAll(year, month);
        return new ResponseEntity<>(profitReportDTOS, HttpStatus.OK);
    }
}
