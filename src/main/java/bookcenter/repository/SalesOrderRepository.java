package bookcenter.repository;

import bookcenter.domain.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;


/**
 * Spring Data JPA repository for the SalesOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

    List<SalesOrder> findAllByDateBetween(Instant from, Instant to);

    SalesOrder findFirstByOrderByDateAsc();

    SalesOrder findFirstByOrderByDateDesc();
}
