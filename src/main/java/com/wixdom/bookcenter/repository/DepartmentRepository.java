package com.wixdom.bookcenter.repository;

import com.wixdom.bookcenter.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry Lin badcop@163.com
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
