package bookcenter.service;

import bookcenter.service.dto.StockReportDTO;

import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
public interface StockReportService {

    Set<StockReportDTO> getStockReports(Long departmentId,
                                        Integer year,
                                        Integer month);
}
