package com.wixdom.bookcenter.service.impl;

import com.wixdom.bookcenter.domain.Book;
import com.wixdom.bookcenter.repository.BookRepository;
import com.wixdom.bookcenter.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Henry Lin badcop@163.com
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book findOneById(Long id) {
        return bookRepository.getOne(id);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Boolean delete(Long id) {
        Book delete = bookRepository.getOne(id);
        if (null == delete) {
            return false;
        } else {
            bookRepository.delete(delete);
            return null==bookRepository.getOne(id);
        }
    }

    @Override
    public Boolean isExist(Long id) {
        return null != bookRepository.getOne(id);
    }
}
