package com.wixdom.bookcenter.service;

import com.baomidou.mybatisplus.service.IService;
import com.wixdom.bookcenter.domain.Book;

/**
 * @author Henry Lin badcop@163.com
 */
public interface BookService extends IService<Book> {
    Book findOneById(Long id);

    Book update(Book book);

    Boolean save(Book book);

    Boolean delete(Long id);

    Boolean isExist(Long id);
}
