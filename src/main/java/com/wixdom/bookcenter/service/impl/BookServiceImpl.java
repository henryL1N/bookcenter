package com.wixdom.bookcenter.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wixdom.bookcenter.domain.Book;
import com.wixdom.bookcenter.dao.BookMapper;
import com.wixdom.bookcenter.service.BookService;
import org.springframework.stereotype.Service;

/**
 * @author Henry Lin badcop@163.com
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public Book findOneById(Long id) {
        return super.selectById(id);
    }

    @Override
    public Book update(Book book) {
        if (!super.updateById(book)) {
            return null;
        }
        return super.selectById(book.getId());
    }

    @Override
    public Boolean save(Book book) {
//        return super.insert(book);
        return baseMapper.insert(book)>0;
    }

    @Override
    public Boolean delete(Long id) {
        return super.deleteById(id);
    }

    @Override
    public Boolean isExist(Long id) {
        return super.selectCount(Condition.create().eq("id", id)) > 0;
    }
}
