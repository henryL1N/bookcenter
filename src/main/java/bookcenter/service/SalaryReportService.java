package bookcenter.service;

import bookcenter.service.dto.SalaryReportDTO;

import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
public interface SalaryReportService {

    Set<SalaryReportDTO> getAll(Integer year,
                                Integer month);
}
