package bookcenter.service;

import bookcenter.service.dto.PurchaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Henry Lin badcop@163.com
 */
public interface PurchaseService {

    PurchaseDTO save(PurchaseDTO purchaseDTO);

    Page<PurchaseDTO> findAll(Pageable pageable);

    PurchaseDTO findOne(Long id);

    void delete(Long id);
}
