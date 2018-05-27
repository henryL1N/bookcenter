package bookcenter.service;

import bookcenter.service.dto.SaleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing SalesOrder.
 */
public interface SaleService {

    SaleDTO save(SaleDTO saleDTO);

    Page<SaleDTO> findAll(Pageable pageable);

    SaleDTO findOne(Long id);

    void delete(Long id);
}
