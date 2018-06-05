package bookcenter.service;

import bookcenter.service.dto.SalesReportDTO;

import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
public interface SalesReportService {

    Set<SalesReportDTO> getSalesReports(Long departmentId,
                                        Integer year,
                                        Integer month);
}
