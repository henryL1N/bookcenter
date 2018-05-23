package bookcenter.repository;

import bookcenter.domain.Book;
import bookcenter.domain.StockItem;
import bookcenter.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the StockItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {

    StockItem findFirstByBookAndWarehouse(Book book, Warehouse warehouse);
}
