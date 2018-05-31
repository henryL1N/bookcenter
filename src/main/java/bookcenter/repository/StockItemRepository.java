package bookcenter.repository;

import bookcenter.domain.Book;
import bookcenter.domain.StockItem;
import bookcenter.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;


/**
 * Spring Data JPA repository for the StockItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {

    StockItem findFirstByBookAndWarehouse(Book book, Warehouse warehouse);

    List<StockItem> findAllByWarehouseAndQuantityGreaterThan(Warehouse warehouse, Long quantityGreaterThan);

    Page<StockItem> findAllByWarehouseIdAndQuantityGreaterThan(Long warehouseId, Long quantityGreaterThan, Pageable pageable);
}
