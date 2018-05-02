package com.wixdom.bookcenter.repository;

import com.wixdom.bookcenter.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry Lin badcop@163.com
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
