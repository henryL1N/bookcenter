package bookcenter.repository;

import bookcenter.domain.Employee;
import bookcenter.domain.User;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;


/**
 * Spring Data JPA repository for the Employee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findFirstByUser(User user);

    List<Employee> findAllByPosition(String position);
}
