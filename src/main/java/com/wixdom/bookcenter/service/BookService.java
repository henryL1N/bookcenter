package com.wixdom.bookcenter.service;

import com.wixdom.bookcenter.domain.Book;

/**
 * @author Henry Lin badcop@163.com
 */
public interface BookService {
    Book findOneById(Long id);

    Book update(Book book);

    Book save(Book book);

    Boolean delete(Long id);

    Boolean isExist(Long id);
}
