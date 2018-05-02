package com.wixdom.bookcenter.repository;

import com.wixdom.bookcenter.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry Lin badcop@163.com
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
