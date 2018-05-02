package com.wixdom.bookcenter.repository;

import com.wixdom.bookcenter.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry Lin badcop@163.com
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
