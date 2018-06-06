package bookcenter.repository;

import bookcenter.domain.PurchaseOrder;
import bookcenter.domain.Warehouse;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.time.Instant;
import java.util.List;


/**
 * Spring Data JPA repository for the PurchaseOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    List<PurchaseOrder> findAllByWarehouseAndDateAfter(Warehouse warehouse, Instant after);
}
