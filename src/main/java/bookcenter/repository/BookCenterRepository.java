package bookcenter.repository;

import bookcenter.domain.BookCenter;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the BookCenter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookCenterRepository extends JpaRepository<BookCenter, Long> {

}
