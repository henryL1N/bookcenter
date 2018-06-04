package bookcenter.service;

import bookcenter.service.dto.ProfitReportDTO;

import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
public interface ProfitReportService {

    Set<ProfitReportDTO> getAll(Integer year,
                                Integer month);
}
