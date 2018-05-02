package com.wixdom.bookcenter.repository;

import com.wixdom.bookcenter.domain.BookCenter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry Lin badcop@163.com
 */
public interface BookCenterRepository extends JpaRepository<BookCenter, Long> {
}
