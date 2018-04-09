package com.wixdom.bookcenter.endpoint;

import com.wixdom.bookcenter.domain.Book;
import com.wixdom.bookcenter.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Henry Lin badcop@163.com
 */
@RestController
@RequestMapping("book")
@Api(tags = "Book")
public class BookEndpoint {
    @Autowired
    private BookService bookService;

    @ApiOperation(value = "创建书籍", notes = "将Book插入数据库", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean create(@RequestBody Book book) {
        Boolean b = bookService.save(book);
        System.out.println(b);
        return b;
    }
}
